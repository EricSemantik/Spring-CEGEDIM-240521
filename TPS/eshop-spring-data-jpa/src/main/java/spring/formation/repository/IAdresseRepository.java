package spring.formation.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Adresse;

public interface IAdresseRepository extends JpaRepository<Adresse, Long> {
	List<Adresse> findByVille(String ville); // select a from Adresse a where a.ville = ?1
	
	List<Adresse> findByCodePostalStartingWith(String codePostal);
	
	List<Adresse> findByOrderByVille(Pageable pageable);
}
