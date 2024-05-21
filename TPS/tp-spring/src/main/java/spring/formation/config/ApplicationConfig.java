package spring.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.IInstrument;

@Configuration
@ComponentScan("spring.formation.orchestre") // Activation des annotations : @Component, @Autowired, ... (en précisant le(s) package(s) à scanner)
public class ApplicationConfig {

	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
//	@Bean
//	public IInstrument piano() {
//		return new Piano();
//	}
//	
//	@Bean
//	public IInstrument ukulele() {
//		return new Ukulele();
//	}
//	
//	@Bean
//	public IMusicien guitariste(@Qualifier("guitare") IInstrument instrument, @Value("Vive le vent ...") String morceau) {
//		Guitariste guitariste = new Guitariste();
//		guitariste.setInstrument(instrument);
//		guitariste.setMorceau(morceau);
//		
//		return guitariste;
//	}
//	
//	@Bean
//	public IMusicien pianiste(@Qualifier("guitare") IInstrument instrument, @Value("La lettre à Elise") String morceau) {
//		return new Pianiste(instrument, morceau);
//	}
	
}
