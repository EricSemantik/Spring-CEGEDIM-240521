package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Trompetiste implements IMusicien {
	@Autowired
	@Qualifier("trompette")
	private IInstrument instrument;
	@Value("Pouet pouet pouet")
	private String morceau;

	public Trompetiste() {

	}

	public Trompetiste(String morceau) {
		super();
		this.morceau = morceau;
	}

	public Trompetiste(IInstrument instrument, String morceau) {
		super();
		this.instrument = instrument;
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le trompétiste joue : " + this.morceau + " (" + this.instrument.son() + ")");
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
