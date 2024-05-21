package spring.formation.orchestre;

import org.springframework.stereotype.Component;

@Component
public class Ukulele implements IInstrument{

	@Override
	public String son() {
		return "ULINK ULINK ULINK";
	}

}
