package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class UserManager {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();

	public void finalAll() {
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
//		list.forEach(user -> {
//			String fullname = user.getFullname();
//			boolean admin = user.getAdmin();
//			System.out.println(fullname + " " + admin);
//		});
		for (User user : list) {
			String fullname = user.getFullname();
			boolean admin = user.getAdmin();
			System.out.println(fullname + " " + admin);
		}
	}

	public void findById() {
		User user = em.find(User.class, "<<user-id>>");
		String fullname = user.getFullname();
		boolean admin = user.getAdmin();
		System.out.println(fullname + " " + admin);
	}

	public void create() {
		
	}

	public void update() {

	}

	public void deleteById() {

	}
}
