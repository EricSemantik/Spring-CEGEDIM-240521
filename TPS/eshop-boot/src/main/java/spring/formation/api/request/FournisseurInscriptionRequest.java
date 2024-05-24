package spring.formation.api.request;

import java.util.ArrayList;
import java.util.List;

public class FournisseurInscriptionRequest {
	private String civilite;
	private String nom;
	private String resposnable;
	private String login;
	private String motDePasse;
	private List<AdresseRequest> adresses = new ArrayList<>();

	public FournisseurInscriptionRequest() {
		super();
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getResposnable() {
		return resposnable;
	}

	public void setResposnable(String resposnable) {
		this.resposnable = resposnable;
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
	
	

	public List<AdresseRequest> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<AdresseRequest> adresses) {
		this.adresses = adresses;
	}



	public static class AdresseRequest {
		private String rue;
		private String codePostal;
		private String ville;

		public AdresseRequest() {
			super();
		}

		public String getRue() {
			return rue;
		}

		public void setRue(String rue) {
			this.rue = rue;
		}

		public String getCodePostal() {
			return codePostal;
		}

		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

	}

}
