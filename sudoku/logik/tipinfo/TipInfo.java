package sudoku.logik.tipinfo;

import sudoku.kern.feldmatrix.FeldNummerListe;
import sudoku.kern.feldmatrix.FeldNummerMitZahl;
import sudoku.kern.feldmatrix.ZahlenListe;
import sudoku.kern.info.InfoSudoku;
import sudoku.logik.Logik_ID;

public interface TipInfo {
	/**
	 * @return true wenn diese Info eine zu setzende Zahl benennt.
	 */
	public boolean istZahl();

	/**
	 * @param tipNummer
	 * @return �berschrift, die auch die tipNummer beinhaltet
	 */
	public String gibUeberschrift(int tipNummer);

	/**
	 * @return Der Identifikator der Logik, die zu diesem Tip gef�hrt hat
	 */
	public Logik_ID gibLogik();

	/**
	 * @return null oder die konkrete Aktion
	 */
	public EinTipText[] gibTip();

	/**
	 * @return null oder die Mitspieler (alle Felder), auf deren Basis die Logik lief
	 */
	public FeldNummerListe gibMitSpieler();

	/**
	 * @return null oder FeldNummer mit der Zahl, die den empfohlenen Eintrag darstellt
	 */
	public FeldNummerMitZahl gibZahlFeld();

	/**
	 * @return null oder aktive Felder: Das Feld mit dem zu setzenden Eintrag oder 
	 * 					die Felder, in denen m�gliche Zahlen als zu l�schen benannt werden.
	 */
	public FeldNummerListe gibAktiveFelder();

	/**
	 * @return null oder die durch die Logik empfohlenen zu l�schenden m�glichen Zahlen
	 */
	public ZahlenListe gibLoeschZahlen();

	/**
	 * @return null oder Sudoku
	 */
	public void setzeSudoku(InfoSudoku infoSudoku);

	/**
	 * @return null oder Sudoku
	 */
	public InfoSudoku gibSudoku();
}
