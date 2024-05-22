package spring.formation.test;

import org.junit.BeforeClass;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import spring.formation.EShopApplication;

public class PopulateTest {

	private static EntityManagerFactory emf;

	@BeforeClass
	public static void beforeAll() {
		emf = EShopApplication.getInstance().getEmf();
	}

	@BeforeClass
	public static void afterAll() {
		emf.close();
	}

	@Test
	public void populate() {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = EShopApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			
			
			
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
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
