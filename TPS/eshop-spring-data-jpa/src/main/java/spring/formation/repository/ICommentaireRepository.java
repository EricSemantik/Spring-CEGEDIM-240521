package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Commentaire;

public interface ICommentaireRepository extends JpaRepository<Commentaire, Long> {

}
