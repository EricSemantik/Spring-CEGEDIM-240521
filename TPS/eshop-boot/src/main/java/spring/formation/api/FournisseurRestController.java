package spring.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.api.request.FournisseurInscriptionRequest;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Fournisseur;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.model.Views;
import spring.formation.repository.IAdresseRepository;
import spring.formation.repository.IFournisseurRepository;
import spring.formation.repository.IUtilisateurRepository;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurRestController {

	@Autowired
	private IAdresseRepository repoAdresse;

	@Autowired
	private IUtilisateurRepository repoUtilisateur;

	@Autowired
	private IFournisseurRepository repoFournisseur;

	@GetMapping("")
	@JsonView(Views.ViewFournisseur.class)
	public List<Fournisseur> findAll() {
		return this.repoFournisseur.findAllWithAdresses();
	}

	@GetMapping(path = "/{id}")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur findById(@PathVariable Long id) {
		return this.repoFournisseur.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/{id}/detail")
	@JsonView(Views.ViewFournisseurDetail.class)
	public Fournisseur findDetailById(@PathVariable Long id) {
		return this.repoFournisseur.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur create(@RequestBody Fournisseur fournisseur) {
		fournisseur = this.repoFournisseur.save(fournisseur);

		return fournisseur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur update(@RequestBody Fournisseur fournisseur, @PathVariable Long id) {
		if (id != fournisseur.getId() || !this.repoFournisseur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return this.repoFournisseur.save(fournisseur);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!this.repoFournisseur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		this.repoFournisseur.deleteById(id);
	}

	@PostMapping("/inscription")
	public Fournisseur inscription(@RequestBody FournisseurInscriptionRequest fournisseurInscriptionRequest) {
		Utilisateur utilisateur = new Utilisateur(fournisseurInscriptionRequest.getLogin(),
				fournisseurInscriptionRequest.getMotDePasse(), true, Role.FOURNISSEUR);
		utilisateur = repoUtilisateur.save(utilisateur);

		Fournisseur fournisseur = new Fournisseur();
		BeanUtils.copyProperties(fournisseurInscriptionRequest, fournisseur);

		switch (fournisseurInscriptionRequest.getCivilite()) {
		case "Monsieur" -> fournisseur.setCivilite(Civilite.M);
		case "Madame" -> fournisseur.setCivilite(Civilite.MME);
		case "Mademoiselle" -> fournisseur.setCivilite(Civilite.MLLE);
		}

		fournisseur.setUtilisateur(utilisateur);

		List<Adresse> adresses = fournisseurInscriptionRequest.getAdresses().stream()
				.map(adr -> new Adresse(adr.getRue(), adr.getCodePostal(), adr.getVille())).toList();

		adresses = repoAdresse.saveAll(adresses);

		fournisseur.setAdresses(adresses);

		fournisseur = repoFournisseur.save(fournisseur);

		return fournisseur;
	}
}
