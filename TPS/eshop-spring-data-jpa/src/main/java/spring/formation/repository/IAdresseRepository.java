package spring.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Adresse;

public interface IAdresseRepository extends JpaRepository<Adresse, Long> {
	public List<Adresse> findByVille(String ville); // select a from Adresse a where a.ville = ?1
}
