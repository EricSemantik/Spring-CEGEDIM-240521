package spring.formation.orchestre;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {
	@Resource(name="${orchestre.pianiste.instrument}")
	private IInstrument instrument;
	@Value("La lettre à Elise") 
	private String morceau;

	public Pianiste() {

	}

	public Pianiste(String morceau) {
		super();
		this.morceau = morceau;
	}

	public Pianiste(IInstrument instrument, String morceau) {
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
