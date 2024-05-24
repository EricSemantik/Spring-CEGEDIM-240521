package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("fournisseur")
public class Fournisseur extends Personne {
	@Column(length = 200)
	@JsonView(Views.ViewFournisseur.class)
	private String responsable;

	@OneToMany
	@JoinTable(name = "fournisseur_adresse", joinColumns = @JoinColumn(name = "fournisseur_id"), inverseJoinColumns = @JoinColumn(name = "adresse_id"))
	@JsonView(Views.ViewFournisseurDetail.class)
	private List<Adresse> adresses = new ArrayList<>();

	@OneToMany(mappedBy = "fournisseur")
	@JsonView(Views.ViewFournisseur.class)
	private List<Produit> produits = new ArrayList<>();

	public Fournisseur() {
		super();
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
