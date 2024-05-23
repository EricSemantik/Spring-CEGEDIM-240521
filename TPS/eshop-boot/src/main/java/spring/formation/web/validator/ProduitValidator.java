package spring.formation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.formation.model.Produit;

public class ProduitValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Produit.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Produit produit = (Produit) target;

		if (produit.getReference().isBlank() ^ produit.getModele().isBlank()) {
			errors.rejectValue("reference", "required", "La référence doit être renseigné avec le modèle");
		}

	}

}
