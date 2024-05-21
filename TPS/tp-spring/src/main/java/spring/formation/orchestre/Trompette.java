package spring.formation.orchestre;

import org.springframework.stereotype.Component;

@Component
public class Trompette implements IInstrument{

	@Override
	public String son() {
		return "TLINK TLINK TLINK";
	}

}
