package by.academy.alekhno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import by.academy.alekhno.database.pojo.State;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.database.util.EMFactory;

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
		System.out.println(em.find(State.class, 1));
		em.getTransaction().commit();
		
		
	}

}
