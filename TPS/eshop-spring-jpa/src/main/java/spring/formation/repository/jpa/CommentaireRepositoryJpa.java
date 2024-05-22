package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Commentaire;
import spring.formation.repository.ICommentaireRepository;

@Repository
@Transactional(readOnly = true)
public class CommentaireRepositoryJpa implements ICommentaireRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Commentaire> findAll() {
		TypedQuery<Commentaire> query = em.createQuery("select c from Commentaire c", Commentaire.class);

		return query.getResultList();
	}

	@Override
	public Commentaire findById(Long id) {
		TypedQuery<Commentaire> query = em.createQuery("select c from Commentaire c where c.id = ?1",
				Commentaire.class);
		query.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = false)
	public Commentaire save(Commentaire obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from Commentaire c where c.id = ?1");
		query.setParameter(1, id);

		query.executeUpdate();
	}

}
