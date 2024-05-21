package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Ukulele implements IInstrument{

	@Value("${orchestre.instrument.ukulele.son}")
	private String instrumentSon; 
	
	@Override
	public String son() {
		return this.instrumentSon;
	}

}
