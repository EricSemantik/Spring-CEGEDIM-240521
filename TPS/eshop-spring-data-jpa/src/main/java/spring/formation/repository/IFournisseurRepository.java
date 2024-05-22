package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Fournisseur;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long>{

}
