//package by.academy.alekhno.dao.impl;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Expression;
//
//import by.academy.alekhno.dao.interf.CustomUserDaoTest;
//import by.academy.alekhno.database.pojo.Model;
//import by.academy.alekhno.database.pojo.Role;
//import by.academy.alekhno.database.pojo.Type;
//import by.academy.alekhno.database.pojo.User;
//import by.academy.alekhno.exception.DaoHibernateException;
//
//public class UserDaoTest implements CustomUserDaoTest {
//	private static final Logger logger = Logger.getLogger(UserDaoTest.class);
//	private Session session;
//
//	public User getByID(int id) throws DaoHibernateException {
//		logger.debug("getByID start");
//		User user = null;
//		try {
//			Transaction transaction = session.beginTransaction();
//			user = (User) session.get(User.class, id);
//			transaction.commit();
//		} catch (Exception e) {
//			logger.error("User getByID failed.", e);
//			throw new DaoHibernateException("User getByID failed.", e, 101);
//		}
//		return user;
//	}
//
//	public List<User> getAll() throws DaoHibernateException {
//		logger.debug("getAll start");
//		List<User> users = new ArrayList<User>();
//		try {
//			users = session.createCriteria(User.class).list();
//		} catch (Exception e) {
//			logger.error("User getByID failed.", e);
//			throw new DaoHibernateException("User getByID failed.", e, 102);
//		}
//		return users;
//	}
//
//	public List<Model> getByTypeId(int type_id) throws DaoHibernateException {
//		logger.debug("getByID start");
//		List<Model> models = new ArrayList<Model>();
//		try {
//			Transaction transaction = session.beginTransaction();
//			models = session.createCriteria(Model.class)
//					.add(Expression.like("type_id", type_id))
//					.list();
//			transaction.commit();
//		} catch (Exception e) {
//			logger.error("User getByID failed.", e);
//			throw new DaoHibernateException("User getByID failed.", e, 103);
//		}
//		return models;
//	}
//
//	public List<Model> getAllModel() throws DaoHibernateException {
//		logger.debug("getAllModel start");
//		List<Model> models = new ArrayList<Model>();
//		try {
//			models = session.createCriteria(Model.class).list();
//		} catch (Exception e) {
//			logger.error("User getAllModel failed.", e);
//			throw new DaoHibernateException("User getAllModel failed.", e, 104);
//		}
//		return models;
//	}
//
//	public List<Type> getAllType() throws DaoHibernateException {
//		logger.debug("getAllType start");
//		List<Type> types = new ArrayList<Type>();
//		try {
//			types = session.createCriteria(Type.class).list();
//		} catch (Exception e) {
//			logger.error("User getAllType failed.", e);
//			throw new DaoHibernateException("User getAllType failed.", e, 105);
//		}
//		return types;
//	}
//
//	
//
//	public void setSession(Session session) {
//		this.session = session;
//	}
//	
//	public void closeSession(){
//		session.close();
//	}
//
//	@Override
//	public Collection<Role> getRoleByUserId(int user_id) {
//		logger.debug("getRoleByUserId start");
//		Collection<Role> roles;
//		Transaction transaction = session.beginTransaction();
//		
//		String hql = "select r from Role r "
//				+ "inner join r.users u "
//				+ "where u.id = :user_id";
//		Query query = session.createQuery(hql);
////		" select b " + " from UserPojo b INNER JOIN b.roles role" + " where role.name = :roleName ")
//		
////		String sql = "SELECT DISTINCT r.id, r.name FROM   roles r  INNER JOIN user_roles ur  "
////				+ " ON ur.role_id = r.id INNER JOIN users s  ON s.id = ur.user_id WHERE  ur.user_id=:user_id";
////		Query query = session.createQuery(sql);
//		query.setInteger("user_id", user_id);
//		roles =  query.list();
//		transaction.commit();
//		return roles;
//	}
//	
//}
