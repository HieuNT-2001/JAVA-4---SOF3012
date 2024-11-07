package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class UserDAO {

	// kết nối csdl
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");

	// truy vấn csdl
	EntityManager em = factory.createEntityManager();

	// CRUD
	// Tìm kiếm tất cả bàn ghi trong bảng Users
	public List<User> findAll() {
		// truy vấn jpql, lấy tất cả bản ghi của lớp User
		String jpql = "SELECT o FROM User o";

		// TypedQuery<User>: kiểu trả về khi truy vấn vào class User sẽ là kiểu <User>
		// createQuery(): tạo câu truy vấn
		TypedQuery<User> query = em.createQuery(jpql, User.class);

		// getResultList(): lấy List đối tượng trả về trong class User
		List<User> list = query.getResultList();
		return list;
	}

}
