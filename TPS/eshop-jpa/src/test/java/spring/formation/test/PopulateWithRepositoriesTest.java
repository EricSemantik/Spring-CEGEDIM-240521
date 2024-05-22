package spring.formation.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jakarta.persistence.EntityManagerFactory;
import spring.formation.EShopApplication;
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

public class PopulateWithRepositoriesTest {

	private static EntityManagerFactory emf;

	@BeforeClass
	public static void beforeAll() {
		emf = EShopApplication.getInstance().getEmf();
	}

	@AfterClass
	public static void afterAll() {
		emf.close();
	}

	@Test
	public void populate() {

		Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley");

		adrFournisseur = EShopApplication.getInstance().getAdresseRepository().save(adrFournisseur);

		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom("AMAZON");
		fournisseur.setResponsable("BEZOS");
		fournisseur.setEmail("contact@amazon.fr");
		fournisseur.getAdresses().add(adrFournisseur);

		fournisseur = EShopApplication.getInstance().getFournisseurRepository().save(fournisseur);

		Produit produit = new Produit();
		produit.setNom("IPhone");
		produit.setPrix(100d);
		produit.setFournisseur(fournisseur);
		produit.setModele("XS 4");
		produit.setReference("IPhone XS 4");

		produit = EShopApplication.getInstance().getProduitRepository().save(produit);

		Utilisateur utiAdmin = new Utilisateur("admin", "123456", true, Role.ADMIN);
		utiAdmin = EShopApplication.getInstance().getUtilisateurRepository().save(utiAdmin);

		Utilisateur utiClient = new Utilisateur("esultan", "123456", true, Role.CLIENT);
		utiClient = EShopApplication.getInstance().getUtilisateurRepository().save(utiClient);

		Adresse adrClient = new Adresse("1 rue de Toulouse", "33000", "Bordeaux");
		adrClient = EShopApplication.getInstance().getAdresseRepository().save(adrClient);

		Client client = new Client();
		client.setCivilite(Civilite.M);
		client.setNom("SULTAN");
		client.setPrenom("Eric");
		client.setEmail("eric@semantik.fr");
		client.setDtNaissance(LocalDate.of(1978, 1, 4));
		client.setAdresse(adrClient);
		client.setUtilisateur(utiClient);

		client = EShopApplication.getInstance().getClientRepository().save(client);

		Commentaire comment1 = new Commentaire();
		comment1.setClient(client);
		comment1.setProduit(produit);
		comment1.setDtCommentaire(LocalDateTime.now());
		comment1.setNote(18);
		comment1.setDetail("Produit qui correspond à mes attentes (je mens)");

		comment1 = EShopApplication.getInstance().getCommentaireRepository().save(comment1);

		Commande commande = new Commande(LocalDateTime.now(), EtatCommande.ENCOURS, client);

		commande = EShopApplication.getInstance().getCommandeRepository().save(commande);

		CommandeDetail commandeIphone = new CommandeDetail(200d, 2, produit, commande);

		commandeIphone = EShopApplication.getInstance().getCommandeDetailRepository().save(commandeIphone);

		commande.setPrixTotal(200d);
		
		commande = EShopApplication.getInstance().getCommandeRepository().save(commande);

	}

}
