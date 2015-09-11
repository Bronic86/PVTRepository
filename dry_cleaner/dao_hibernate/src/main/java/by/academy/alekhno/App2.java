package by.academy.alekhno;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import by.academy.alekhno.database.pojo.StatePojo;

public class App2 {

	public static void main(String[] args) {
		
//		EntityManagerFactory emf = EMFactory.getInstance().getEMFactory();
//		System.out.println(emf);
//		EntityManager em = emf.createEntityManager();
//		System.out.println(em);
//		String query = ;
//		em.createQuery(query);
//		System.out.println(em.find(User.class, 1));

		EntityManager em = Persistence.createEntityManagerFactory("dry_cleaner").createEntityManager();
		em.getTransaction().begin();
		System.out.println(em.find(StatePojo.class, 1));
		em.getTransaction().commit();
		
		
	}

}
