package spring.formation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EShopApplication {
	private static EShopApplication instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");

	private EShopApplication() {
		super();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public static EShopApplication getInstance() {
		if (instance == null) {
			instance = new EShopApplication();
		}

		return instance;
	}
}
