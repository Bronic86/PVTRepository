package by.academy.alekhno;

import java.util.Date;
import java.util.List;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.impl.OrderImpl;
import by.academy.alekhno.dao.impl.TypeImpl;
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.ClotherServiceImpl;
import by.academy.alekhno.service.impl.OrderServiceImpl;
import by.academy.alekhno.service.impl.TypeService;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.vo.User;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DaoException, ServiceException
    {
        UserService userServ = new UserServiceImpl();
        int id = 1;
    	String login = "boris1";
    	String password = "boris";
    	String firstName = "Boris";
    	String secondName = "Alekhno";
    	long telephone = 375291234567l;
    	User user = new User();
    	user.setLogin(login);
    	user.setPassword(password);
    	user.setFirstName(firstName);
    	user.setSecondName(secondName);
    	user.setTelephone(telephone);
    	
//    	userServ.registration(user);
    	User addUser = userServ.authorization(login, password);
    	System.out.println(addUser);
    	List<Role> roles = userServ.getRoleByUserId(addUser.getId());
    	System.out.println(roles);
    	
    	ClotherService clotherService = new ClotherServiceImpl();
    	int type_id = 1;
    	System.out.println(clotherService.getModelsByTypeId(type_id));
    	
    	int model_id = 1;
    	System.out.println(clotherService.getClothesByModelId(model_id));
    	
    	OrderService orderService = new OrderServiceImpl();
    	
    	Order orderAdd = new Order();
    	addUser.setId(id);
    	orderAdd.setUser(addUser);
    	
    	Clother clother = new Clother();
    	clother.setId(1);
    	orderAdd.setClother(clother);
    	
    	orderAdd.setQuantity(3);
    	orderAdd.setOrdering_day(new Date());
    	
    	System.out.println(orderService.add(orderAdd));
    }
}
