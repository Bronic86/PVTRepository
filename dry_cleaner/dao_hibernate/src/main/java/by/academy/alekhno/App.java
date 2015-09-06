package by.academy.alekhno;



import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import by.academy.alekhno.dao.impl.RoleDAOImpl;
import by.academy.alekhno.dao.impl.UserDAOImpl;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;


public class App 
{
    public static void main( String[] args ) throws DaoHibernateException
    {
        System.out.println( "Hello World!" );
//        CustomUserDaoTest daoUser = new UserDaoTest();
//        daoUser.setSession(HibernateUtil.getInstance().getSessionFactory());
//        int id = 1;
//        try {
//			System.out.println(daoUser.getByID(id));
//			System.out.println(daoUser.getAllModel());
//			System.out.println(daoUser.getRoleByUserId(1));
//		} catch (DaoHibernateException e) {
//			System.out.println("Exception");
//			e.printStackTrace();
//		}
        
        
        int type_id = 1;
        SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();
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
        transaction.commit();
        session.close();
        
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
       
       
       CustomUserDAO userDao = new UserDAOImpl();
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       List<User> users = new ArrayList<User>();
       users.addAll(userDao.getAll());
       System.out.println(users.size());
       System.out.println(users);
       
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       String loginChange = "Boris";
       long telephoneChange = 123L;
       int idChange = 1;
       User userG = new User();
       userG.setId(idChange);
       userG = userDao.getByID(userG);
       System.out.println("getByID " + userG);
       
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       User userN = new User();
       userN.setLogin(loginChange);
       userN.setPassword(userG.getPassword());
       userN.setFirstName(userG.getFirstName());
       userN.setSecondName(userG.getSecondName());
       userN.setTelephone(userG.getTelephone());
       
       
       int idAdd = userDao.add(userN);
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       
       

       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       System.out.println(idAdd);
       userG = new User();
       userG.setId(idAdd);
       userG = userDao.getByID(userG);
       System.out.println("add + getByID " + userG);
       
       
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       userG.setTelephone(telephoneChange);
       userDao.update(userG);
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       userG = userDao.getByID(userG);
       System.out.println("\n update" + userG);
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       CustomRoleDAO roleDao = new RoleDAOImpl();
       roleDao.setSessionFactory(sessionFactory);
       Role role = new Role();
       role.setId(1);
       role = roleDao.getByID(role);
       userDao.addRoleForUser(userG, role);
       
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       System.out.println(userDao.getByID(userG).getRoles());
       System.out.println("\nStop\n");
       
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       userDao.delete(userG);
       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       System.out.println(userDao.getByID(userG));
       

       userDao.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
       System.out.println(session);
       String login1 = "boris@mail.ru";
       System.out.println(userDao.getByLogin(login1));
       
       
    }
}
