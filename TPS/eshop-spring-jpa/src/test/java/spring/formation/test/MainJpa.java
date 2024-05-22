package spring.formation.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import spring.formation.EShopApplication;
import spring.formation.model.Adresse;

public class MainJpa {

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		Adresse adrML = new Adresse("1 rue de Bordeaux", "31800", "Toulouse"); // new ou transient
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			em.persist(adrML); // managed
			
			// adrML = em.merge(adrML);
			
			adrML.setCodePostal("31600"); // dirty checking sur les entités managed
			
			// em.flush(); implicite			
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
		
		adrML.setVille("LABEGE"); // detached
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Adresse adrMLCopy = em.merge(adrML); // adrMLCopy : managed - adrML : detached
			
			adrMLCopy.setRue("16 rue de Paris"); // dirty checking sur les entités managed
			
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
		
		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
//			Adresse adrMLCopyCopy = em.merge(adrML); 
			
			Adresse adrFind = em.find(Adresse.class, adrML.getId()); // adrFind : managed
			
			em.remove(adrFind); // adrFind : removed
			
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

		EShopApplication.getInstance().getEmf().close();
	}

}
