package spring.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.formation.model.Produit;
import spring.formation.repository.IProduitRepository;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {

	@Autowired
	private IProduitRepository repoProduit;

	@GetMapping("")
	public List<Produit> findAll() {
		return this.repoProduit.findAll();
	}

	
}
