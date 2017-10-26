package sudoku.neu.pool;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

import sudoku.logik.Schwierigkeit;

public class PoolInfoEntnommene {
	/**
	 * @param maxTage Der R�ckgabewert soll nicht gr��er sein als maxTage
	 * @return alleTagesEntnahmen komprimiert auf maxTage 
	 * 			Das Array kann kleiner sein als maxTage!
	 * 			Wenn mehr als maxTage Tage entnommen wurde, so enth�lt das Array auf Index 0 alle �ltesten Entnahmen
	 * 			und als Datumsangabe die j�ngste dieser Entnahmen. 
	 */
	static public TagesEntnahme[] gibTageweise(TagesEntnahme[] alleTagesEntnahmen, int maxTage) {
		if (alleTagesEntnahmen.length <= maxTage) {
			return alleTagesEntnahmen;
		}

		// Es liegen mehr als maxTage Entnahmen vor:
		TagesEntnahme[] komprimierteEntnahmen = new TagesEntnahme[maxTage];
		int quellStartIndex = alleTagesEntnahmen.length - maxTage;
		TagesEntnahme tagesEntnahme0 = alleTagesEntnahmen[quellStartIndex];

		// Die �ltesten Entnahmen zusammenfassen
		for (int iQuelle = 0; iQuelle < quellStartIndex; iQuelle++) {
			tagesEntnahme0.add(alleTagesEntnahmen[iQuelle]);
		}
		komprimierteEntnahmen[0] = tagesEntnahme0;

		// Die restlichen Entnahmen 1:1 anh�ngen
		int iZiel = 0;
		for (int iQuelle = quellStartIndex + 1; iQuelle < alleTagesEntnahmen.length; iQuelle++) {
			komprimierteEntnahmen[++iZiel] = alleTagesEntnahmen[iQuelle];
		}

		return komprimierteEntnahmen;
	}

	// ====================================================
	/**
	 * @author heroe
	 * Die Entnahme eines Tages
	 */
	public class TagesEntnahme {
		public final LocalDateTime datum;
		public final EnumMap<Schwierigkeit, InfoEntnommene> entnahme;

		/**
		 * @param datum
		 * @param entnahme
		 */
		TagesEntnahme(LocalDateTime datum, EnumMap<Schwierigkeit, InfoEntnommene> entnahme) {
			super();
			this.datum = datum;
			this.entnahme = entnahme;
		}

		/**
		 * @param info
		 * F�gt die info hinzu
		 */
		void add(InfoEntnommene info) {
			InfoEntnommene tagesInfo = this.entnahme.get(info.schwierigkeit);

			if (tagesInfo == null) {
				// Neue SchwierigkeitsInfo
				this.entnahme.put(info.schwierigkeit, info);
			} else {
				// entnommen in bestehende SchwierigkeitsInfo einf�gen
				tagesInfo.add(info);
			}
		}

		/**
		 * @param tagesEntnahme
		 * F�gt die Entnahmen der tagesEntnahme hinzu. Mein datum bleibt unver�ndert.
		 */
		void add(TagesEntnahme tagesEntnahme) {
			Collection<InfoEntnommene> entnahmen = tagesEntnahme.entnahme.values();

			for (InfoEntnommene info : entnahmen) {
				this.add(info);
			}
		}
	}

	// ===================================================
	public final InfoEntnommene[] entnommene;

	public PoolInfoEntnommene(final InfoEntnommene[] entnommene) {
		super();
		this.entnommene = entnommene;
	}

	/**
	 * @return Die Entnahme aus dem Sudoku-Pool tageweise
	 */
	public TagesEntnahme[] gibTageweise() {
		ArrayList<TagesEntnahme> tagesEntnahmen = new ArrayList<>();

		for (int iEntnommene = 0; iEntnommene < entnommene.length; iEntnommene++) {
			InfoEntnommene entnommen = entnommene[iEntnommene];
			LocalDateTime diesDatum = entnommen.gibJuengstes().truncatedTo(ChronoUnit.DAYS);

			boolean istNeueEntnahme = true;
			if (!tagesEntnahmen.isEmpty()) {
				LocalDateTime letztesDatum = tagesEntnahmen.get(tagesEntnahmen.size() - 1).datum;
				if (letztesDatum.isEqual(diesDatum)) {
					istNeueEntnahme = false;
				}
			}

			if (istNeueEntnahme) {
				// entnommen als neue TagesEntnahme anh�ngen
				EnumMap<Schwierigkeit, InfoEntnommene> entnahme = new EnumMap<>(Schwierigkeit.class);
				entnahme.put(entnommen.schwierigkeit, entnommen);
				TagesEntnahme tagesEntnahme = new TagesEntnahme(diesDatum, entnahme);
				tagesEntnahmen.add(tagesEntnahme);
			} else {
				// entnommen in letzte TagesEntnahme einf�gen
				TagesEntnahme tagesEntnahme = tagesEntnahmen.get(tagesEntnahmen.size() - 1);
				tagesEntnahme.add(entnommen);
			}
		}

		TagesEntnahme[] tagesArray = new TagesEntnahme[tagesEntnahmen.size()];
		tagesEntnahmen.toArray(tagesArray);

		return tagesArray;
	}

}
