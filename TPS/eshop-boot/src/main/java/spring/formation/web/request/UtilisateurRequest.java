package spring.formation.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UtilisateurRequest {
	@NotBlank(message = "Le nom est obligatoire")
	private String nom;
	@NotBlank(message = "Le prénom est obligatoire")
	private String prenom;
	@Size(min=4, message = "Le login > 4 caractères")
	private String login;
	@Pattern(regexp = "^[a-zA-Z0-9]{8,}.*$", message = "Le mot de passe doit avoir au moins 8 caractères alphanumériques")
	private String motDePasse;
	private String motDePasseConfirm;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getMotDePasseConfirm() {
		return motDePasseConfirm;
	}

	public void setMotDePasseConfirm(String motDePasseConfirm) {
		this.motDePasseConfirm = motDePasseConfirm;
	}

}
