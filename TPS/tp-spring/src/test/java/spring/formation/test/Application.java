package spring.formation.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.orchestre.IMusicien;

public class Application {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		IMusicien monMusicien = (IMusicien) context.getBean("guitariste");
		
		monMusicien.jouer();
		
		
		context.close();
	}

}
