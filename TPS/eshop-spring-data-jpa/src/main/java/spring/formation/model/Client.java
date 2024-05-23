package spring.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("client")
@NamedQuery(name = "Client.findByIdWithCommandes", query = "select distinct c from Client c left join fetch c.commandes where c.id = ?1")
public class Client extends Personne {
	@Column(length = 255)
	private String prenom;

	@Column(name = "dt_naissance")
	private LocalDate dtNaissance;

	@OneToOne
	@JoinColumn(name = "adresse_id")
	private Adresse adresse;

	@OneToMany(mappedBy = "client")
	private List<Commande> commandes = new ArrayList<>();

	public Client() {
		super();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(LocalDate dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

}
