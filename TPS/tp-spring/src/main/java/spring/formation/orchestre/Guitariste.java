package spring.formation.orchestre;

//@Component
public class Guitariste implements IMusicien {
	private IInstrument instrument;
	private String morceau;

	public Guitariste() {
		System.out.println("Par le constructeur");
	}

	public Guitariste(String morceau) {
		super();
		this.morceau = morceau;
	}

	public Guitariste(IInstrument instrument, String morceau) {
		super();

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

//	@Autowired
//	@Qualifier("${orchestre.guitariste.instrument}")
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

//	@Value("Vive le vent ...")
	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

//	@PostConstruct
	public void commeJeVeux() {
		this.morceau = this.morceau.toUpperCase();
	}

}
