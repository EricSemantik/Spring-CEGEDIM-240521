package spring.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import spring.formation.model.Produit;
import spring.formation.repository.IProduitRepository;
import spring.formation.web.validator.ProduitValidator;

@Controller
@RequestMapping("/produit")
public class ProduitController {

	@Autowired
	private IProduitRepository repoProduit;

	@GetMapping({ "", "/list" }) // ETAPE 1 : Réception de la Request
	public String list(Model model) {
		List<Produit> produits = repoProduit.findAll(); // ETAPE 2 : Récupérer les données

		model.addAttribute("mesProduits", produits); // ETAPE 3 : Renseigner le Model

		return "produit/list"; // ETAPE 4 : Appel de la View
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("produit", new Produit());

		return "produit/form";
	}

	@GetMapping("/{idProduit}/edit")
	public String edit(@PathVariable("idProduit") Long id, Model model) {
		Optional<Produit> optProduit = repoProduit.findById(id);

		if (optProduit.isEmpty()) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		model.addAttribute("produit", optProduit.get());

		return "produit/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "id", required = false) Long id, @RequestParam("nom") String nom,
			@RequestParam(name = "prix", required = false) Double prix, @RequestParam("reference") String reference,
			@RequestParam("modele") String modele) {
		Produit produit = null;

		if (id != null) {
			Optional<Produit> optProduit = repoProduit.findById(id);

			if (optProduit.isEmpty()) {
				throw new ResponseStatusException(HttpStatusCode.valueOf(404));
			}

			produit = optProduit.get();
		} else {
			produit = new Produit();
		}

		produit.setNom(nom);
		produit.setPrix(prix);
		produit.setReference(reference);
		produit.setModele(modele);

		repoProduit.save(produit);

		return "redirect:list";
	}

	@PostMapping("/saveBis")
	public String saveBis(@ModelAttribute("produit") @Valid Produit produit, BindingResult result) {
		new ProduitValidator().validate(produit, result);

		if (result.hasErrors()) {
			return "produit/form";
		}

		if (produit.getId() != null && !repoProduit.existsById(produit.getId())) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		repoProduit.save(produit);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/{idProduit}/delete")
	public String delete(@PathVariable("idProduit") Long id) {
		if (!repoProduit.existsById(id)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		repoProduit.deleteById(id);

		return "redirect:../list";
	}

}
