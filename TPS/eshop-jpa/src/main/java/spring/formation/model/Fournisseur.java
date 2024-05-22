package spring.formation.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("fournisseur")
public class Fournisseur extends Personne {
	@Column(length = 200)
	private String responsable;

	public Fournisseur() {
		super();
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

}
