package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.EShopApplication;
import spring.formation.model.Commande;
import spring.formation.repository.ICommandeRepository;

public class CommandeRepositoryJpa implements ICommandeRepository {

	@Override
	public List<Commande> findAll() {
		List<Commande> liste = new ArrayList<Commande>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Commande> query = em.createQuery("select c from Commande c", Commande.class);
			
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
	public Commande findById(Long id) {
		Commande obj = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Commande> query = em.createQuery("select c from Commande c where c.id = ?1", Commande.class);
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
	public Commande save(Commande obj) {
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
		
			Query query = em.createQuery("delete from Commande c where c.id = ?1");
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
