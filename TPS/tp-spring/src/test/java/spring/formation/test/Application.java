package spring.formation.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.orchestre.IMusicien;

public class Application {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		IMusicien monMusicien = (IMusicien) context.getBean("guitariste");
		
		monMusicien.jouer();
		
		IMusicien monMusicienBis = (IMusicien) context.getBean("pianiste"); // new Pianiste("La lettre à Elise", piano)
		
		monMusicienBis.jouer();
		
		context.close();
	}

}
