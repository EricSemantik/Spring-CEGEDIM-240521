package spring.formation.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Commentaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt_commentaire", nullable = false)
	private LocalDateTime dtCommentaire = LocalDateTime.now();

	@Column(nullable = false)
	private int note = 0;

	private String detail;

	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDtCommentaire() {
		return dtCommentaire;
	}

	public void setDtCommentaire(LocalDateTime dtCommentaire) {
		this.dtCommentaire = dtCommentaire;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
