package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.CommandeDetail;
import spring.formation.repository.ICommandeDetailRepository;

@Repository
@Transactional(readOnly = true)
public class CommandeDetailRepositoryJpa implements ICommandeDetailRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CommandeDetail> findAll() {
		TypedQuery<CommandeDetail> query = em.createQuery("select c from CommandeDetail c", CommandeDetail.class);

		return query.getResultList();
	}

	@Override
	public CommandeDetail findById(Long id) {
		TypedQuery<CommandeDetail> query = em.createQuery("select c from CommandeDetail c where c.id = ?1",
				CommandeDetail.class);
		query.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = false)
	public CommandeDetail save(CommandeDetail obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from CommandeDetail c where c.id = ?1");
		query.setParameter(1, id);

		query.executeUpdate();
	}

}
