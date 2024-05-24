package spring.formation.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.formation.api.request.InscriptionRequest;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Client;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IAdresseRepository;
import spring.formation.repository.IClientRepository;
import spring.formation.repository.IUtilisateurRepository;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

	@Autowired
	private IAdresseRepository repoAdresse;
	
	@Autowired
	private IClientRepository repoClient;
	
	@Autowired
	private IUtilisateurRepository repoUtilisateur;

	@GetMapping("")
	public List<Client> findAll() {
		return this.repoClient.findAll();
	}

	@PostMapping("/inscription")
	public Client inscription(@RequestBody InscriptionRequest inscriptionRequest) {
		Utilisateur utilisateur = new Utilisateur(inscriptionRequest.getLogin(), inscriptionRequest.getMotDePasse(), true, Role.CLIENT);
		utilisateur = repoUtilisateur.save(utilisateur);
		
		Adresse adresse = new Adresse();
		BeanUtils.copyProperties(inscriptionRequest, adresse);
		adresse = repoAdresse.save(adresse);
		
		Client client = new Client();
		BeanUtils.copyProperties(inscriptionRequest, client);
		
		if(inscriptionRequest.getCivilite().equals("Monsieur")) {
			client.setCivilite(Civilite.M);
		} else if(inscriptionRequest.getCivilite().equals("Madame")) {
			client.setCivilite(Civilite.MME);
		} else if(inscriptionRequest.getCivilite().equals("Mademoiselle")) {
			client.setCivilite(Civilite.MLLE);
		}
		
		client.setDtNaissance(LocalDate.parse(inscriptionRequest.getNaissance(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		client.setAdresse(adresse);
		client.setUtilisateur(utilisateur);
		
		client = repoClient.save(client);
		
		return client;
	}
}