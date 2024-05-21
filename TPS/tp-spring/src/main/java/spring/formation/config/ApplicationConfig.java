package spring.formation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;

@Configuration
public class ApplicationConfig {

	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
	@Bean
	public IMusicien guitariste(IInstrument guitare, @Value("Vive le vent ...") String morceau) {
		Guitariste guitariste = new Guitariste();
		guitariste.setInstrument(guitare);
		guitariste.setMorceau(morceau);
		
		return guitariste;
	}
	
}
