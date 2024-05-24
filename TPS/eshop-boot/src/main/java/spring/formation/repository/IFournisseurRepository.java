package spring.formation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.formation.model.Fournisseur;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long> {
	@Query("select distinct f from Fournisseur f left join fetch f.adresses")
	List<Fournisseur> findAllWithAdresses();

	@Query("select f from Fournisseur f where f.utilisateur.id = ?1")
	Optional<Fournisseur> findByUtilisateurId(Long id);
}
