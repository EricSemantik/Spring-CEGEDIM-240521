package spring.formation.orchestre;

public class Guitariste implements IMusicien {
	private IInstrument instrument;
	private String morceau;

	public Guitariste() {

	}

	public Guitariste(String morceau) {
		super();
		this.morceau = morceau;
	}

	public Guitariste(IInstrument instrument, String morceau) {
		super();

		System.out.println("Par le cosntructeur");
		this.instrument = instrument;
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le guitariste joue : " + this.morceau + " (" + this.instrument.son() + ")");
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

}
