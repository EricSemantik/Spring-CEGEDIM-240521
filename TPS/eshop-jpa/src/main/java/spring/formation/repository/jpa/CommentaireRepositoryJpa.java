package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.EShopApplication;
import spring.formation.model.Commentaire;
import spring.formation.repository.ICommentaireRepository;

public class CommentaireRepositoryJpa implements ICommentaireRepository {

	@Override
	public List<Commentaire> findAll() {
		List<Commentaire> liste = new ArrayList<Commentaire>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Commentaire> query = em.createQuery("select c from Commentaire c", Commentaire.class);
			
			liste = query.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return liste;
	}

	@Override
	public Commentaire findById(Long id) {
		Commentaire obj = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Commentaire> query = em.createQuery("select c from Commentaire c where c.id = ?1", Commentaire.class);
			query.setParameter(1, id);
			
			obj = query.getSingleResult();
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return obj;
	}

	@Override
	public Commentaire save(Commentaire obj) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			obj = em.merge(obj);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return obj;
	}

	@Override
	public void deleteById(Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			Query query = em.createQuery("delete from Commentaire c where c.id = ?1");
			query.setParameter(1, id);
			
			query.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}		
	}

}
