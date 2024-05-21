package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {
	private IInstrument instrument;
	private String morceau;

	public Pianiste() {

	}

	public Pianiste(String morceau) {
		super();
		this.morceau = morceau;
	}

	@Autowired
	public Pianiste(@Qualifier("piano") IInstrument instrument, @Value("La lettre à Elise") String morceau) {
		super();
		this.instrument = instrument;
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le pianiste joue : " + this.morceau + " (" + this.instrument.son() + ")");
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

//	public void setMorceau(String morceau) {
//		this.morceau = morceau;
//	}

}
