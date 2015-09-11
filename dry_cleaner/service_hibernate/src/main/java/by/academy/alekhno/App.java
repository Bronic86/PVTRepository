//package by.academy.alekhno;
//
//import java.util.Date;
//import java.util.List;
//
//import by.academy.alekhno.dao.impl.ClotherImpl;
//import by.academy.alekhno.dao.impl.ModelImpl;
//import by.academy.alekhno.dao.impl.OrderImpl;
//import by.academy.alekhno.dao.impl.RoleImpl;
//import by.academy.alekhno.dao.impl.TypeImpl;
//import by.academy.alekhno.dao.impl.UserImpl;
//import by.academy.alekhno.dao.impl.UserRoleImpl;
//import by.academy.alekhno.exception.DaoException;
//import by.academy.alekhno.exception.ServiceException;
//import by.academy.alekhno.service.impl.ClotherServiceImpl;
//import by.academy.alekhno.service.impl.OrderServiceImpl;
//import by.academy.alekhno.service.impl.UserServiceImpl;
//import by.academy.alekhno.service.interf.ClotherService;
//import by.academy.alekhno.service.interf.OrderService;
//import by.academy.alekhno.service.interf.UserService;
//import by.academy.alekhno.vo.Clother;
//import by.academy.alekhno.vo.Order;
//import by.academy.alekhno.vo.Role;
//import by.academy.alekhno.vo.User;
//
//
///**
// * Hello world!
// *
// */
//public class App 
//{
//    public static void main( String[] args ) throws  ServiceException
//    {
//        UserService userServ = new UserServiceImpl();
//        userServ.setDaoUser(new UserImpl());
//        userServ.setDaoUserRole(new UserRoleImpl());
//        userServ.setDaoOrder(new OrderImpl());
//        userServ.setDaoRole(new RoleImpl());
//        int id = 1;
//    	String login = "boris1@mail.ru";
//    	String password = "boris";
//    	String firstName = "Boris";
//    	String secondName = "Alekhno";
//    	long telephone = 375291234567l;
//    	User user = new User();
//    	user.setLogin(login);
//    	user.setPassword(password);
//    	user.setFirstName(firstName);
//    	user.setSecondName(secondName);
//    	user.setTelephone(telephone);
//    	
////    	userServ.registration(user);
//    	User addUser = userServ.authorization(login, password);
//    	System.out.println(addUser);
//    	List<Role> roles = userServ.getRoleByUserId(addUser.getId());
//    	System.out.println(roles);
//    	
//    	ClotherService clotherService = new ClotherServiceImpl();
//    	clotherService.setDaoClother(new ClotherImpl());
//    	clotherService.setDaoModel(new ModelImpl());
//    	clotherService.setDaoType(new TypeImpl());
//    	int type_id = 1;
//    	System.out.println(clotherService.getModelsByTypeId(type_id));
//    	
//    	int model_id = 1;
//    	System.out.println(clotherService.getClothesByModelId(model_id));
//    	
//    	UserService userService = new UserServiceImpl();
//    	userService.setDaoOrder(new OrderImpl());
//    	
//    	Order orderAdd = new Order();
//    	
//    	addUser.setId(id);
//    	orderAdd.setUser(addUser);
//    	
//    	Clother clother = new Clother();
//    	clother.setId(1);
//    	orderAdd.setClother(clother);
//    	
//    	orderAdd.setQuantity(3);
////    	orderAdd.setOrdering_day(new Date());
//    	
////    	userService.addOrder(orderAdd);
//    	int order_id = 30;
//    	userService.deleteOrderById(order_id);
//    }
//}
