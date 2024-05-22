package spring.formation.repository;

import java.util.List;

import spring.formation.model.Adresse;

public interface IAdresseRepository extends IRepository<Adresse, Long>{
	public List<Adresse> findAllByVille(String ville);
}
