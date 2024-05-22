package spring.formation.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Adresse;
import spring.formation.repository.IAdresseRepository;

@Repository
@Transactional(readOnly = true)
public class AdresseRepositoryJpa implements IAdresseRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Adresse> findAll() {
		TypedQuery<Adresse> query = em.createQuery("select adr from Adresse adr", Adresse.class);

		return query.getResultList();
	}

	@Override
	public Adresse findById(Long id) {
		return em.find(Adresse.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public Adresse save(Adresse obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		Query query = em.createQuery("delete from Adresse adr where adr.id = :monId");
		query.setParameter("monId", id);

		query.executeUpdate();
	}

	@Override
	public List<Adresse> findAllByVille(String ville) {
		TypedQuery<Adresse> query = em.createQuery("select adr from Adresse adr where adr.ville = ?1", Adresse.class);
		query.setParameter(1, ville);

		return query.getResultList();
	}

}
