package spring.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import spring.formation.model.Fournisseur;
import spring.formation.repository.IFournisseurRepository;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {

	@Autowired
	private IFournisseurRepository repoFournisseur;

	@GetMapping({ "", "/list" }) // ETAPE 1 : Réception de la Request
	public String list(Model model) {
		List<Fournisseur> fournisseurs = repoFournisseur.findAllWithAdresses(); // ETAPE 2 : Récupérer les données

		model.addAttribute("fournisseurs", fournisseurs); // ETAPE 3 : Renseigner le Model

		return "fournisseur/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("fournisseur", new Fournisseur());

		return "fournisseur/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("idFournisseur") Long id, Model model) {
		Optional<Fournisseur> optFournisseur = repoFournisseur.findById(id);

		if (optFournisseur.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		model.addAttribute("fournisseur", optFournisseur.get());

		return "fournisseur/form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("fournisseur") @Valid Fournisseur fournisseur, BindingResult result) {
		if (result.hasErrors()) {
			return "fournisseur/form";
		}

		if (fournisseur.getId() != null && !repoFournisseur.existsById(fournisseur.getId())) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		repoFournisseur.save(fournisseur);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("idFournisseur") Long id) {
		if (!repoFournisseur.existsById(id)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		repoFournisseur.deleteById(id);

		return "redirect:../list";
	}

}
