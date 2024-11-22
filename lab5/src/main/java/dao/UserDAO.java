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
		String jpql = "SELECT u FROM User u";
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		return query.getResultList();
	}

	// tim user theo id
	public static User findById(String id) {
		return manager.find(User.class, id);
	}

	// tim user theo id hoac email
	public static User findByIdOrEmail(String account) {
		String jpql = "SELECT u FROM User u WHERE u.id = :id OR u.email LIKE :email";
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		query.setParameter("id", account);
		query.setParameter("email", account);
		return query.getSingleResult();
	}

}
