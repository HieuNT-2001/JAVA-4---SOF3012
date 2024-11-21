package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class UserDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	static EntityManager manager = factory.createEntityManager();

	// tim danh sach toan bo user
	public static List<User> findAll() {
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		return query.getResultList();
	}

	// tim user theo id
	public static User findById(String id) {
		return manager.find(User.class, id);
	}

	// tim user theo email
	public static User findByEmail(String email) {
		String jpql = "SELECT o FROM User o WHERE o.email LIKE :email";
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

}
