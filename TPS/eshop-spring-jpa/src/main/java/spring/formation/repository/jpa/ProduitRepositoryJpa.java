package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Produit;
import spring.formation.repository.IProduitRepository;

@Repository
@Transactional(readOnly = true)
public class ProduitRepositoryJpa implements IProduitRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Produit> findAll() {
		TypedQuery<Produit> query = em.createQuery("select p from Produit p", Produit.class);

		return query.getResultList();
	}

	@Override
	public Produit findById(Long id) {
		TypedQuery<Produit> query = em.createQuery("select p from Produit p where p.id = ?1", Produit.class);
		query.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = false)
	public Produit save(Produit obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from Produit p where p.id = ?1");
		query.setParameter(1, id);

		query.executeUpdate();
	}

}
