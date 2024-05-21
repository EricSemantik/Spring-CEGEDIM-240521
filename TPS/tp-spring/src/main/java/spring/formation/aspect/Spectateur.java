package spring.formation.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import spring.formation.orchestre.IMusicien;

@Aspect
public class Spectateur {

	private IMusicien musicien;

	public Spectateur() {
		super();
	}

	public Spectateur(IMusicien musicien) {
		super();
		this.musicien = musicien;
	}

	@Pointcut("execution(void spring.formation.orchestre.Pianiste.jouer())")
	public void musicienJouer() {
		
	}

	@Before("musicienJouer()")
	public void assoir() {
		System.out.println("Les spectateurs s'assoient");
	}

	@AfterReturning("musicienJouer()")
	public void applaudir() {
		System.out.println("CLAP CLAP CLAP");
		this.musicien.jouer();
	}

	@AfterThrowing(pointcut = "musicienJouer()", throwing = "ex")
	public void rembourser(Exception ex) {
		System.out.println("BOUHHH !!! REMBOURSER !!!");
	}
}
