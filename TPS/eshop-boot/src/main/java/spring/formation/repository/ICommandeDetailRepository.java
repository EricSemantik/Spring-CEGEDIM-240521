package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.CommandeDetail;

public interface ICommandeDetailRepository extends JpaRepository<CommandeDetail, Long>{

}
