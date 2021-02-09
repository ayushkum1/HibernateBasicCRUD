package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.*;

import dao.UserDaoImpl;

public class TestGetAllUserDetails {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
			
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			
			System.out.println("All user details");
			
			userDaoImpl.fetchAllUSers().forEach(System.out::println);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
