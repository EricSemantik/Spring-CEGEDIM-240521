package spring.formation.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import spring.formation.model.Client;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IClientRepository;
import spring.formation.repository.IUtilisateurRepository;
import spring.formation.web.request.UtilisateurRequest;

@Controller
public class UtilisateurController {
	@Autowired
	private IClientRepository repoClient;
	
	@Autowired
	private IUtilisateurRepository repoUtilisateur;

	@GetMapping("/inscription")
	public String inscription(Model model) {
		model.addAttribute("utilisateur", new UtilisateurRequest());

		return "utilisateur/inscription";
	}

	@PostMapping("/inscription")
	public String confirmInscription(@ModelAttribute("utilisateur") @Valid UtilisateurRequest utilisateurRequest,
			BindingResult result) {
		if (result.getFieldErrorCount("motDePasse") == 0) {
			if (!utilisateurRequest.getMotDePasse().equals(utilisateurRequest.getMotDePasseConfirm())) {
				result.rejectValue("motDePasseConfirm", "confirm", "La confirmation du mot de passe est incorrecte");
			}
		}

		if (result.hasErrors()) {
			return "utilisateur/inscription";
		}

		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(utilisateurRequest, utilisateur);
		utilisateur.setIdentifiant(utilisateurRequest.getLogin());
		utilisateur.getRoles().add(Role.CLIENT);
		
		utilisateur = repoUtilisateur.save(utilisateur);
		
		Client client = new Client();
		BeanUtils.copyProperties(utilisateurRequest, client);
		client.setUtilisateur(utilisateur);

		repoClient.save(client);

		return "redirect:connexion";
	}

	@GetMapping("/connexion")
	public String connexion() {
		return "utilisateur/connexion";
	}
}
