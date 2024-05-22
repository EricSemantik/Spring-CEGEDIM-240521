package spring.formation.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
import spring.formation.repository.IAdresseRepository;
import spring.formation.repository.IClientRepository;
import spring.formation.repository.ICommandeDetailRepository;
import spring.formation.repository.ICommandeRepository;
import spring.formation.repository.ICommentaireRepository;
import spring.formation.repository.IFournisseurRepository;
import spring.formation.repository.IProduitRepository;
import spring.formation.repository.IUtilisateurRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
public class PopulateWithRepositoriesTest {

	@Autowired
	private IAdresseRepository adresseRepository;
	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private ICommandeDetailRepository commandeDetailRepository;
	@Autowired
	private ICommandeRepository commandeRepository;
	@Autowired
	private ICommentaireRepository commentaireRepository;
	@Autowired
	private IFournisseurRepository fournisseurRepository;
	@Autowired
	private IProduitRepository produitRepository;
	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	
	@Test
	public void populate() {

		Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley");

		adrFournisseur = adresseRepository.save(adrFournisseur);

		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom("AMAZON");
		fournisseur.setResponsable("BEZOS");
		fournisseur.setEmail("contact@amazon.fr");
		fournisseur.getAdresses().add(adrFournisseur);

		fournisseur = fournisseurRepository.save(fournisseur);

		Produit produit = new Produit();
		produit.setNom("IPhone");
		produit.setPrix(100d);
		produit.setFournisseur(fournisseur);
		produit.setModele("XS 4");
		produit.setReference("IPhone XS 4");

		produit = produitRepository.save(produit);

		Utilisateur utiAdmin = new Utilisateur("admin", "123456", true, Role.ADMIN);
		utiAdmin = utilisateurRepository.save(utiAdmin);

		Utilisateur utiClient = new Utilisateur("esultan", "123456", true, Role.CLIENT);
		utiClient = utilisateurRepository.save(utiClient);

		Adresse adrClient = new Adresse("1 rue de Toulouse", "33000", "Bordeaux");
		adrClient = adresseRepository.save(adrClient);

		Client client = new Client();
		client.setCivilite(Civilite.M);
		client.setNom("SULTAN");
		client.setPrenom("Eric");
		client.setEmail("eric@semantik.fr");
		client.setDtNaissance(LocalDate.of(1978, 1, 4));
		client.setAdresse(adrClient);
		client.setUtilisateur(utiClient);

		client = clientRepository.save(client);

		Commentaire comment1 = new Commentaire();
		comment1.setClient(client);
		comment1.setProduit(produit);
		comment1.setDtCommentaire(LocalDateTime.now());
		comment1.setNote(18);
		comment1.setDetail("Produit qui correspond à mes attentes (je mens)");

		comment1 = commentaireRepository.save(comment1);

		Commande commande = new Commande(LocalDateTime.now(), EtatCommande.ENCOURS, client);

		commande = commandeRepository.save(commande);

		CommandeDetail commandeIphone = new CommandeDetail(200d, 2, produit, commande);

		commandeIphone = commandeDetailRepository.save(commandeIphone);

		commande.setPrixTotal(200d);
		
		commande = commandeRepository.save(commande);

	}

}
