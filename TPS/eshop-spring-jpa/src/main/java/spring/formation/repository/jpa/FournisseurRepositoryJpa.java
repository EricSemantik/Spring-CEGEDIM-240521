package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.EShopApplication;
import spring.formation.model.Fournisseur;
import spring.formation.repository.IFournisseurRepository;

public class FournisseurRepositoryJpa implements IFournisseurRepository {

	@Override
	public List<Fournisseur> findAll() {
		List<Fournisseur> liste = new ArrayList<Fournisseur>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Fournisseur> query = em.createQuery("select f from Fournisseur f", Fournisseur.class);
			
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
	public Fournisseur findById(Long id) {
		Fournisseur obj = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Fournisseur> query = em.createQuery("select f from Fournisseur f where f.id = ?1", Fournisseur.class);
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
	public Fournisseur save(Fournisseur obj) {
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
		
			Query query = em.createQuery("delete from Fournisseur f where f.id = ?1");
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
