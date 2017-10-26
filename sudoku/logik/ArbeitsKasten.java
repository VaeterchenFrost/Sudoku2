package sudoku.logik;

import sudoku.kern.feldmatrix.FeldListe;

class ArbeitsKasten {
	/**
	 * Die Kasten, zu denen die Felder geh�ren: Hier k�nnen die Gruppen-Infos abgefragt werden.
	 */
	Kasten kasten;
	/**
	 * Alle oder eine Auswahl der Felder der Kasten, auch null.
	 */
	FeldListe arbeitsFelder;

	ArbeitsKasten(Kasten kasten) {
		this.kasten = kasten;
		arbeitsFelder = null;
	}


}
