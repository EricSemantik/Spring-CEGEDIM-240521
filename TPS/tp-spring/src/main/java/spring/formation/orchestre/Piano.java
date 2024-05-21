package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Piano implements IInstrument{

	@Value("${orchestre.instrument.piano.son}")
	private String instrumentSon; 
	
	@Override
	public String son() {
		return this.instrumentSon;
	}

}
