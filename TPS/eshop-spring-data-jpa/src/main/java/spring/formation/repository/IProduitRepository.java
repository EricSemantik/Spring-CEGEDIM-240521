package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long>{

}
