package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Client;
import spring.formation.repository.IClientRepository;

@Repository
@Transactional(readOnly = true)
public class ClientRepositoryJpa implements IClientRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Client> findAll() {
		TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);

		return query.getResultList();
	}

	@Override
	public Client findById(Long id) {
		TypedQuery<Client> query = em.createQuery("select c from Client c where c.id = ?1", Client.class);
		query.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = false)
	public Client save(Client obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from Client c where c.id = ?1");
		query.setParameter(1, id);

		query.executeUpdate();
	}

}
