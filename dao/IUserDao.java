package dao;

import java.time.LocalDate;
import java.util.List;

import pojos.Role;
import pojos.User;

public interface IUserDao {

	//method for user registration using open session
	String registerUser(User user);
	
	//method for user registration using getCurrentSession
	String registerUserWithGetCurrentSession(User user);
	
	//method to get user details
	User getUserDetails(int userId);
	
	//method to get fetch all users
	List<User> fetchAllUSers();

}
