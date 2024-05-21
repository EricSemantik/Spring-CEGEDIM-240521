package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
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

	@Autowired
	@Qualifier("ukulele")
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

	@Value("Vive le vent ...")
	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

}
