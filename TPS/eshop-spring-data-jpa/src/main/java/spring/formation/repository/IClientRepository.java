package spring.formation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

	@Query("select c from Client c join c.adresse adr where adr.ville = :maVille")
	List<Client> findAllByVille(@Param("maVille") String ville); // par @Query
	
	@Query("select c from Client c join c.commandes com where YEAR(com.dtCommande) = ?1")
	List<Client> findAllByAnneeCommande(String annee);
	
	Optional<Client> findByIdWithCommandes(Long id); 
}
