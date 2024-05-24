package spring.formation.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.api.request.ConnexionRequest;
import spring.formation.api.response.ConnexionResponse;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IClientRepository;
import spring.formation.repository.IFournisseurRepository;
import spring.formation.repository.IUtilisateurRepository;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {

	@Autowired
	private IClientRepository repoClient;
	
	@Autowired
	private IFournisseurRepository repoFournisseur;
	
	@Autowired
	private IUtilisateurRepository repoUtilisateur;

	@GetMapping("")
	public List<Utilisateur> findAll() {
		return this.repoUtilisateur.findAll();
	}

	@GetMapping(path = "/{id}")
	public Utilisateur findById(@PathVariable Long id) {
		return this.repoUtilisateur.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	public Utilisateur create(@RequestBody Utilisateur utilisateur) {
		utilisateur = this.repoUtilisateur.save(utilisateur);

		return utilisateur;
	}

	@PutMapping("/{id}")
	public Utilisateur update(@RequestBody Utilisateur utilisateur, @PathVariable Long id) {
		if (id != utilisateur.getId() || !this.repoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return this.repoUtilisateur.save(utilisateur);
	}

	@PatchMapping("/{id}")
	public Utilisateur partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		if (id.intValue() != (Integer)fields.get("id") || !this.repoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		Utilisateur utilisateur = this.repoUtilisateur.findById(id).get();
		
		if(fields.containsKey("identifiant")) {
			utilisateur.setIdentifiant((String)fields.get("identifiant"));
		}
		if(fields.containsKey("motDePasse")) {
			utilisateur.setMotDePasse((String)fields.get("motDePasse"));
		}
		if(fields.containsKey("active")) {
			utilisateur.setActive((boolean)fields.get("active"));
		}
		
		return this.repoUtilisateur.save(utilisateur);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!this.repoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		this.repoUtilisateur.deleteById(id);
	}
	
	@PostMapping("/connexion")
	public ConnexionResponse connexion(@RequestBody ConnexionRequest connexionRequest) {
		ConnexionResponse connexionResponse = new ConnexionResponse();
		
		Optional<Utilisateur> optUtilisateur = this.repoUtilisateur.findByUsernameAndPassword(connexionRequest.getUsername(), connexionRequest.getPassword());
		
		Utilisateur utilisateur = optUtilisateur.orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));
		
		connexionResponse.setUtilisateurId(utilisateur.getId());
		connexionResponse.setLogin(utilisateur.getIdentifiant());
		connexionResponse.setActive(utilisateur.isActive());
		
		if(utilisateur.getRoles().contains(Role.CLIENT)) {
			connexionResponse.setRole("CLIENT");
			this.repoClient.findByUtilisateurId(utilisateur.getId()).ifPresent(obj -> {
				connexionResponse.setPersonneId(obj.getId());
				connexionResponse.setNom(obj.getNom());
				connexionResponse.setEmail(obj.getEmail());
				connexionResponse.setPrenom(obj.getPrenom());
			});
		} else if(utilisateur.getRoles().contains(Role.FOURNISSEUR)) {
			connexionResponse.setRole("FOURNISSEUR");
			this.repoFournisseur.findByUtilisateurId(utilisateur.getId()).ifPresent(obj -> {
				connexionResponse.setPersonneId(obj.getId());
				connexionResponse.setNom(obj.getNom());
				connexionResponse.setEmail(obj.getEmail());
				connexionResponse.setResponsable(obj.getResponsable());
			});
		}
		
		return connexionResponse;
	}
	
}

