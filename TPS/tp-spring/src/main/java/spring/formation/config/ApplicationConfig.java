package spring.formation.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;

@Configuration
@ComponentScan("spring.formation.orchestre") // Activation des annotations : @Component, @Autowired, ... (en précisant le(s) package(s) à scanner)
@PropertySource("classpath:musique.properties")
public class ApplicationConfig {
	
	@Resource(name="${orchestre.guitariste.instrument}")
	private IInstrument instrumentGuitariste;

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
	@Bean
	public IMusicien guitariste(@Value("Vive le vent ...") String morceau) {
		Guitariste guitariste = new Guitariste();
		guitariste.setInstrument(this.instrumentGuitariste);
		guitariste.setMorceau(morceau);
		
		guitariste.commeJeVeux();
		
		return guitariste;
	}
//	
//	@Bean
//	public IMusicien pianiste(@Qualifier("guitare") IInstrument instrument, @Value("La lettre à Elise") String morceau) {
//		return new Pianiste(instrument, morceau);
//	}
	
}
