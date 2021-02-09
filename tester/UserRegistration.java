package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.*;

import dao.UserDaoImpl;
import pojos.Role;
import pojos.User;

public class UserRegistration {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
			
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			
			System.out.println("Enter user details : name, email, password, user role, confirm password, reg amount, reg date");
			
			User user = new User(sc.next(), sc.next(), sc.next(), Role.valueOf(sc.next().toUpperCase()), sc.next(), sc.nextDouble(), LocalDate.parse(sc.next()));
			
			System.out.println("registration status " + userDaoImpl.registerUser(user));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
