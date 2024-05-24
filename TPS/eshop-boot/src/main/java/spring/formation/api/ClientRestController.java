package spring.formation.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
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
	public Client inscription(@RequestBody @Valid InscriptionRequest inscriptionRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.toString());
		}

		Utilisateur utilisateur = new Utilisateur(inscriptionRequest.getLogin(), inscriptionRequest.getMotDePasse(),
				true, Role.CLIENT);
		utilisateur = repoUtilisateur.save(utilisateur);

		Client client = new Client();
		BeanUtils.copyProperties(inscriptionRequest, client);

		if (inscriptionRequest.getCivilite().equals("Monsieur")) {
			client.setCivilite(Civilite.M);
		} else if (inscriptionRequest.getCivilite().equals("Madame")) {
			client.setCivilite(Civilite.MME);
		} else if (inscriptionRequest.getCivilite().equals("Mademoiselle")) {
			client.setCivilite(Civilite.MLLE);
		}

		client.setDtNaissance(
				LocalDate.parse(inscriptionRequest.getNaissance(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		if ((inscriptionRequest.getRue() != null && !inscriptionRequest.getRue().isEmpty()) || (inscriptionRequest.getCodePostal() != null && !inscriptionRequest.getCodePostal().isEmpty())
				|| (inscriptionRequest.getVille() != null && !inscriptionRequest.getVille().isEmpty())) {
			Adresse adresse = new Adresse();
			BeanUtils.copyProperties(inscriptionRequest, adresse);
			adresse = repoAdresse.save(adresse);
			client.setAdresse(adresse);
		}

		client.setUtilisateur(utilisateur);

		client = repoClient.save(client);

		return client;
	}
}
