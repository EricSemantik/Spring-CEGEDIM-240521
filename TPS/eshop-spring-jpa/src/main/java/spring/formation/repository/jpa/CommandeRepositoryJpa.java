package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Commande;
import spring.formation.repository.ICommandeRepository;

@Repository
@Transactional(readOnly = true)
public class CommandeRepositoryJpa implements ICommandeRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Commande> findAll() {
		TypedQuery<Commande> query = em.createQuery("select c from Commande c", Commande.class);

		return query.getResultList();
	}

	@Override
	public Commande findById(Long id) {
		TypedQuery<Commande> query = em.createQuery("select c from Commande c where c.id = ?1", Commande.class);
		query.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = false)
	public Commande save(Commande obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from Commande c where c.id = ?1");
		query.setParameter(1, id);

		query.executeUpdate();
	}

}
