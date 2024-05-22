package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

}
