package spring.formation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import spring.formation.repository.IAdresseRepository;
import spring.formation.repository.IClientRepository;
import spring.formation.repository.ICommandeDetailRepository;
import spring.formation.repository.ICommandeRepository;
import spring.formation.repository.ICommentaireRepository;
import spring.formation.repository.IFournisseurRepository;
import spring.formation.repository.IProduitRepository;
import spring.formation.repository.IUtilisateurRepository;
import spring.formation.repository.jpa.AdresseRepositoryJpa;
import spring.formation.repository.jpa.ClientRepositoryJpa;
import spring.formation.repository.jpa.CommandeDetailRepositoryJpa;
import spring.formation.repository.jpa.CommandeRepositoryJpa;
import spring.formation.repository.jpa.CommentaireRepositoryJpa;
import spring.formation.repository.jpa.FournisseurRepositoryJpa;
import spring.formation.repository.jpa.ProduitRepositoryJpa;
import spring.formation.repository.jpa.UtilisateurRepositoryJpa;

public class EShopApplication {
	private static EShopApplication instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");

	private final IAdresseRepository adresseRepository = new AdresseRepositoryJpa();
	private final IClientRepository clientRepository = new ClientRepositoryJpa();
	private final ICommandeDetailRepository commandeDetailRepository = new CommandeDetailRepositoryJpa();
	private final ICommandeRepository commandeRepository = new CommandeRepositoryJpa();
	private final ICommentaireRepository commentaireRepository = new CommentaireRepositoryJpa();
	private final IFournisseurRepository fournisseurRepository = new FournisseurRepositoryJpa();
	private final IProduitRepository produitRepository = new ProduitRepositoryJpa();
	private final IUtilisateurRepository utilisateurRepository = new UtilisateurRepositoryJpa();

	private EShopApplication() {
		super();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IAdresseRepository getAdresseRepository() {
		return adresseRepository;
	}

	public IClientRepository getClientRepository() {
		return clientRepository;
	}

	public ICommandeDetailRepository getCommandeDetailRepository() {
		return commandeDetailRepository;
	}

	public ICommandeRepository getCommandeRepository() {
		return commandeRepository;
	}

	public ICommentaireRepository getCommentaireRepository() {
		return commentaireRepository;
	}

	public IFournisseurRepository getFournisseurRepository() {
		return fournisseurRepository;
	}

	public IProduitRepository getProduitRepository() {
		return produitRepository;
	}

	public IUtilisateurRepository getUtilisateurRepository() {
		return utilisateurRepository;
	}

	public static EShopApplication getInstance() {
		if (instance == null) {
			instance = new EShopApplication();
		}

		return instance;
	}
}
