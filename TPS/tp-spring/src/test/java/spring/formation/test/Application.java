package spring.formation.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.orchestre.IMusicien;

public class Application {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		IMusicien monMusicien = context.getBean("guitariste", IMusicien.class);

		monMusicien.jouer();

		IMusicien monMusicienBis = context.getBean("pianiste", IMusicien.class); // new Pianiste("La lettre à Elise",
																					// piano)

		monMusicienBis.jouer();

		context.close();
	}

}
