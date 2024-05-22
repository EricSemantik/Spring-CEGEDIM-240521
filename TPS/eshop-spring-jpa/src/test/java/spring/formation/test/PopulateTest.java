package spring.formation.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import spring.formation.config.JPAConfiguration;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Client;
import spring.formation.model.Commande;
import spring.formation.model.CommandeDetail;
import spring.formation.model.Commentaire;
import spring.formation.model.EtatCommande;
import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;

public class PopulateTest {

	private static AnnotationConfigApplicationContext context = null;

	@BeforeClass
	public static void beforeAll() {
		context = new AnnotationConfigApplicationContext(JPAConfiguration.class);
	}

	@AfterClass
	public static void afterAll() {
		context.close();
	}

	@Test
	public void populate() {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = context.getBean(EntityManagerFactory.class).createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley");
			
			em.persist(adrFournisseur);
//			adrFournisseur = em.merge(adrFournisseur);
			
			Fournisseur fournisseur = new Fournisseur();
			fournisseur.setNom("AMAZON");
			fournisseur.setResponsable("BEZOS");
			fournisseur.setEmail("contact@amazon.fr");
			fournisseur.getAdresses().add(adrFournisseur);

			fournisseur = em.merge(fournisseur);

			Produit produit = new Produit();
			produit.setNom("IPhone");
			produit.setPrix(100d);
			produit.setFournisseur(fournisseur);
			produit.setModele("XS 4");
			produit.setReference("IPhone XS 4");

			produit = em.merge(produit);	
			
			Utilisateur utiAdmin = new Utilisateur("admin", "123456", true, Role.ADMIN);
			utiAdmin = em.merge(utiAdmin);
			
			Utilisateur utiClient = new Utilisateur("esultan", "123456", true, Role.CLIENT);
			utiClient = em.merge(utiClient);
			
			Adresse adrClient = new Adresse("1 rue de Toulouse", "33000", "Bordeaux");
			adrClient = em.merge(adrClient);
			
			Client client = new Client();
			client.setCivilite(Civilite.M);
			client.setNom("SULTAN");
			client.setPrenom("Eric");
			client.setEmail("eric@semantik.fr");
			client.setDtNaissance(LocalDate.of(1978, 1, 4));
			client.setAdresse(adrClient);
			client.setUtilisateur(utiClient);
			
			client = em.merge(client);	
						
			Commentaire comment1 = new Commentaire();
			comment1.setClient(client);
			comment1.setProduit(produit);
			comment1.setDtCommentaire(LocalDateTime.now());
			comment1.setNote(18);
			comment1.setDetail("Produit qui correspond à mes attentes (je mens)");
			
			comment1 = em.merge(comment1);	
			
			Commande commande = new Commande(LocalDateTime.now(), EtatCommande.ENCOURS, client);
			
			commande = em.merge(commande);
			
			CommandeDetail commandeIphone = new CommandeDetail(200d, 2, produit, commande);
			
			commandeIphone = em.merge(commandeIphone);
			
			commande.setPrixTotal(200d);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
