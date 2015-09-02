package by.academy.alekhno;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.impl.RoleDAOImpl;
import by.academy.alekhno.dao.impl.UserDAOImpl;
import by.academy.alekhno.dao.impl.UserDaoTest;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.dao.interf.CustomUserDaoTest;
import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        CustomUserDaoTest daoUser = new UserDaoTest();
        daoUser.setSession(HibernateUtil.getInstance().getSession());
        int id = 1;
        try {
			System.out.println(daoUser.getByID(id));
			System.out.println(daoUser.getAllModel());
			System.out.println(daoUser.getRoleByUserId(1));
		} catch (DaoHibernateException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
        
        
        int type_id = 1;
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT o FROM Order o "
        		+ "JOIN o.clother c "
        		+ "JOIN c.model m "
        		+ "JOIN m.type t "
        		+ "WHERE t.id = :type_id";
        Query query = session.createQuery(hql);
        query.setInteger("type_id", type_id);
        List<Order> orders = query.list();
        System.out.println(orders);
//      String login1 = "boris@mail.ru";
//      String hql = Bundle.getQueryResource("user.get.by.login");
//		Query query = session.createQuery(hql);
//		query.setParameter("user_login", login1);
//		System.out.println(query.list());
//		User userF = (User) query.list();
//		System.out.println(userF);
        transaction.commit();
//        session.close();
        
        String login = "boris2@mail.ru";
        String password = "123";
        String firstName = "B";
        String secondName = "A";
        long telephone = 375292766536L;
        
       User user = new User();
       user.setLogin(login);
       user.setPassword(password);
       user.setFirstName(firstName);
       user.setSecondName(secondName);
       user.setTelephone(telephone);
       
//       session = HibernateUtil.getInstance().getSession();
//       transaction = session.beginTransaction();
//       int u_id = (int) session.save(user);
//       User dUser = new User();
//       dUser.setId(u_id);
//       session.delete(dUser);
//       transaction.commit();
//       session.flush();
       
       CustomUserDAO userDao = new UserDAOImpl();
       userDao.setSession(HibernateUtil.getInstance().getSession());
       System.out.println(userDao.getAll());
       
       String loginChange = "Boris";
       long telephoneChange = 123L;
       int idChange = 1;
       User userG = new User();
       userG.setId(idChange);
       userG = userDao.getByID(userG);
       System.out.println("getByID " + userG);
       
       User userN = new User();
       userN.setLogin(loginChange);
       userN.setPassword(userG.getPassword());
       userN.setFirstName(userG.getFirstName());
       userN.setSecondName(userG.getSecondName());
       userN.setTelephone(userG.getTelephone());
       
//       userG.setId(0);
//       userG.setLogin(loginChange);
       
       int idAdd = userDao.add(userN);
       
       
       
//       session.disconnect();
//       session = HibernateUtil.getInstance().getSession();
       userDao.setSession(session);
//       String login1 = "boris@mail.ru";
//       System.out.println(userDao.getByLogin(login1));
       idAdd = userDao.add(userN);
       System.out.println(idAdd);
       userG = new User();
       userG.setId(idAdd);
       userG = userDao.getByID(userG);
       System.out.println("add + getByID " + userG);
       
       
       
       userG.setTelephone(telephoneChange);
       userDao.update(userG);
       userG = userDao.getByID(userG);
       System.out.println("\n update" + userG);
       CustomRoleDAO roleDao = new RoleDAOImpl();
       roleDao.setSession(session);
       Role role = new Role();
       role.setId(1);
       role = roleDao.getByID(role);
       userDao.addRoleForUser(userG, role);
       
       System.out.println(userDao.getByID(userG).getRoles());
       System.out.println("\nStop\n");
       
       userDao.delete(userG);
       System.out.println(userDao.getByID(userG));
       
       session.disconnect();
//       session = HibernateUtil.getInstance().getSession();
//       userDao.setSession(session);
       
       System.out.println(session);
       String login1 = "boris@mail.ru";
       System.out.println(userDao.getByLogin(login1));
       
       
    }
}
