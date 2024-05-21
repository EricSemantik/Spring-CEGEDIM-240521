package spring.formation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;
import spring.formation.orchestre.Pianiste;
import spring.formation.orchestre.Piano;
import spring.formation.orchestre.Ukulele;

@Configuration
public class ApplicationConfig {

	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
	@Bean
	public IInstrument piano() {
		return new Piano();
	}
	
	@Bean
	public IInstrument ukulele() {
		return new Ukulele();
	}
	
	@Bean
	public IMusicien guitariste(@Qualifier("guitare") IInstrument instrument, @Value("Vive le vent ...") String morceau) {
		Guitariste guitariste = new Guitariste();
		guitariste.setInstrument(instrument);
		guitariste.setMorceau(morceau);
		
		return guitariste;
	}
	
	@Bean
	public IMusicien pianiste(@Qualifier("guitare") IInstrument instrument, @Value("La lettre à Elise") String morceau) {
		return new Pianiste(instrument, morceau);
	}
	
}
