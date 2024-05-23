package spring.formation.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.formation.config.JPAConfiguration;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
public class TestQueries {
	@Autowired
	private IUtilisateurRepository utilisateurRepository;
	
	@Test
	public void testFindByUsernameAndPassword() {
		// ARRANGE
		Utilisateur ml = new Utilisateur("ml", "123456", true, Role.ADMIN);
		Utilisateur angel = new Utilisateur("angel", "123456", true, Role.FOURNISSEUR);
		Utilisateur eric = new Utilisateur("eric", "123456", true, Role.CLIENT);
		Utilisateur manu = new Utilisateur("manu", "123456", false, Role.ADMIN);
		utilisateurRepository.save(ml);
		utilisateurRepository.save(angel);
		utilisateurRepository.save(eric);
		utilisateurRepository.save(manu);
		
		// ACT
		Optional<Utilisateur> optAngel = utilisateurRepository.findByUsernameAndPassword("angel", "123456");
		Optional<Utilisateur> optEric = utilisateurRepository.findByUsernameAndPassword("eric", "12345");	
		
		// ASSERT
		Assert.assertTrue(optAngel.isPresent());
		Assert.assertFalse(optEric.isPresent());
	}
}
