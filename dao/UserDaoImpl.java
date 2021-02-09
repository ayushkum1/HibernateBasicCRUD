package dao;

import pojos.Role;
import pojos.User;
import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.*;

public class UserDaoImpl implements IUserDao {

	@Override
	public String registerUser(User user) {

		String message = "user registration failed";

		// get the session from session factory(SF)
		Session session = getSf().openSession();// L1 cache is opened : Empty
		Session session1 = getSf().openSession();
		// begin transaction
		Transaction tx = session.beginTransaction();// DB connection is pooled out and attached to hibernate session
		System.out.println("is open " + session.isOpen() + " is connected  " + session.isConnected());
		System.out.println("Is session same? " + (session == session1));
		try {

			// user registration
			Integer id = (Integer) session.save(user);
			tx.commit();// in case of successful transaction
			message = "User Successfully registered with ID " + id;
			System.out.println("is open " + session.isOpen() + " is connected  " + session.isConnected());
		} catch (RuntimeException e) {
			// roll back if transaction wasnt completed and re throw error to the caller
			if (tx != null) {
				tx.rollback();
				System.out
						.println("Rollback : is open " + session.isOpen() + " is connected  " + session.isConnected());
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return message;
	}

	@Override
	public String registerUserWithGetCurrentSession(User user) {

		String message = "user registration failed";

		// get the session from session factory(SF)
		Session session = getSf().getCurrentSession();// L1 cache is opened : Empty
		Session session1 = getSf().getCurrentSession();
		// begin transaction
		Transaction tx = session.beginTransaction();// DB connection is pooled out and attached to hibernate session
		System.out.println("is open " + session.isOpen() + " is connected  " + session.isConnected());
		System.out.println("Is session same? " + (session == session1));
		try {

			// user registration
			Integer id = (Integer) session.save(user);
			tx.commit();// in case of successful transaction
			message = "User Successfully registered with ID " + id;
			System.out.println("is open " + session.isOpen() + " is connected  " + session.isConnected());
		} catch (RuntimeException e) {
			// roll back if transaction wasnt completed and re throw error to the caller
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return message;

	}

	@Override
	public User getUserDetails(int userId) {

		User user = null;

		// get session
		Session session = getSf().getCurrentSession();

		// transaction
		Transaction tx = session.beginTransaction();

		try {

			user = session.get(User.class, userId);

			tx.commit();
		} catch (RuntimeException e) {
			// roll back if transaction wasnt completed and re throw error to the caller
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return user;

	}

	@Override
	public List<User> fetchAllUSers() {

		String jpql = "select u from User u";
		List<User> users = null;

		// get session
		Session session = getSf().getCurrentSession();

		// transaction
		Transaction tx = session.beginTransaction();

		try {

			users = session.createQuery(jpql, User.class).getResultList();

			tx.commit();
		} catch (RuntimeException e) {
			// roll back if transaction wasnt completed and re throw error to the caller
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return users;

	}

}
