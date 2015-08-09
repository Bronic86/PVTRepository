package by.academy.alekhno;

import java.util.List;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Role;
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
//        int id = 1;
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
    	
    	
    }
}
