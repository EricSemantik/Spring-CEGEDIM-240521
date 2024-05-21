package spring.formation.orchestre;

import org.springframework.stereotype.Component;

@Component
public class Synthe implements IInstrument{

	@Override
	public String son() {
		return "SLINK SLINK SLINK";
	}

}
