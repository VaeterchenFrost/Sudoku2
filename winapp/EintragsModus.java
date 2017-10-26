package winapp;

public interface EintragsModus {
	public enum Modus {
		// Es werden die Vorgaben eingetragen
		Vorgabe,
		// Es werden die Einträge eingetragen
		Eintrag
	};

	public EintragsModus.Modus gibEintragsModus();

	public String gibEintragsModusString(Modus modus);

	public void setzeEintragsModus(EintragsModus.Modus modus);
}
