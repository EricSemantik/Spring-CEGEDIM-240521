package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.EShopApplication;
import spring.formation.model.Adresse;
import spring.formation.repository.IAdresseRepository;

public class AdresseRepositoryJpa implements IAdresseRepository {

	@Override
	public List<Adresse> findAll() {
		List<Adresse> liste = new ArrayList<Adresse>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Adresse> query = em.createQuery("select adr from Adresse adr", Adresse.class);
			
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
	public Adresse findById(Long id) {
		Adresse obj = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
//			TypedQuery<Adresse> query = em.createQuery("select adr from Adresse adr where adr.id = ?1", Adresse.class);
//			query.setParameter(1, id);
//			
//			obj = query.getSingleResult();
			
			obj = em.find(Adresse.class, id);
			
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
	public Adresse save(Adresse obj) {
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
		
			Query query = em.createQuery("delete from Adresse adr where adr.id = :monId");
			query.setParameter("monId", id);
			
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
