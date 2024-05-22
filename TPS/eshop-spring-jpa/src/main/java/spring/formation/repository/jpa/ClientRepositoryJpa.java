package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.EShopApplication;
import spring.formation.model.Client;
import spring.formation.repository.IClientRepository;

public class ClientRepositoryJpa implements IClientRepository {

	@Override
	public List<Client> findAll() {
		List<Client> liste = new ArrayList<Client>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);
			
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
	public Client findById(Long id) {
		Client obj = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Client> query = em.createQuery("select c from Client c where c.id = ?1", Client.class);
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
	public Client save(Client obj) {
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
		
			Query query = em.createQuery("delete from Client c where c.id = ?1");
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
