package spring.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.formation.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long>{
	@Query("select p from Produit p join p.commentaires c where c.note <= ?1")
	List<Produit> findAllByNoteCommentaire(int note);
}
