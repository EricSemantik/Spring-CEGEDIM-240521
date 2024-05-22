package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Fournisseur;
import spring.formation.repository.IFournisseurRepository;

@Repository
@Transactional(readOnly = true)
public class FournisseurRepositoryJpa implements IFournisseurRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Fournisseur> findAll() {
		TypedQuery<Fournisseur> query = em.createQuery("select f from Fournisseur f", Fournisseur.class);

		return query.getResultList();
	}

	@Override
	public Fournisseur findById(Long id) {
		TypedQuery<Fournisseur> query = em.createQuery("select f from Fournisseur f where f.id = ?1",
				Fournisseur.class);
		query.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = false)
	public Fournisseur save(Fournisseur obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from Fournisseur f where f.id = ?1");
		query.setParameter(1, id);

		query.executeUpdate();
	}

}
