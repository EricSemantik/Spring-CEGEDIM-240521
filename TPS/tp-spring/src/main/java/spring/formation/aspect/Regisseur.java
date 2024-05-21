package spring.formation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Regisseur {
	
	@Before("execution(* spring.formation.orchestre.IInstrument+.son())")
	public void eteindreLumiere(JoinPoint joinPoint) {
		
		System.out.println("Le régisseur éteint les lumières");
	}
	
	@Around("execution(* spring.formation.orchestre.*.son())")
	public String gestionRideaux(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Ouvrir les rideaux");
		
		String retour = (String) proceedingJoinPoint.proceed();		
		
		System.out.println("Fermer les rideaux");
		
		return retour;
	}
}
