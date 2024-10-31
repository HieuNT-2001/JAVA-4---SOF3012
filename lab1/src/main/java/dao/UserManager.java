package dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class UserManager {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	Scanner scanner = new Scanner(System.in);

	public void finalAll() {
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
		for (User user : list) {
			String fullname = user.getFullname();
			boolean admin = user.getAdmin();
			System.out.println(fullname + " " + admin);
		}
	}

	public void findById() {
		try {
			System.out.println("Nhập ID: ");
			String id = scanner.nextLine(); // Nhập ID từ bàn phím

			User user = em.find(User.class, id);

			String fullname = user.getFullname();
			boolean admin = user.getAdmin();
			System.out.println(fullname + " " + admin);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void create() {
		try {
			System.out.println("Nhập ID: ");
			String id = scanner.nextLine(); // Nhập ID từ bàn phím

			System.out.println("Nhập Password: ");
			String password = scanner.nextLine(); // Nhập password từ bàn phím

			System.out.println("Nhập Fullname: ");
			String fullname = scanner.nextLine(); // Nhập fullname từ bàn phím

			System.out.println("Nhập Email: ");
			String email = scanner.nextLine(); // Nhập email từ bàn phím

			System.out.println("Nhập Admin (1. true - 0. false): ");
			String admin = scanner.nextLine(); // Nhập admin từ bàn phím

			User user = new User(id, password, fullname, email, admin.equals("1"));
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			System.out.println("Thêm thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e);
		}
	}

	public void update() {
		try {
			System.out.println("Nhập ID: ");
			String id = scanner.nextLine(); // Nhập ID từ bàn phím
			User user = em.find(User.class, id);

			System.out.println("Nhập Password mới: ");
			String password = scanner.nextLine(); // Nhập password từ bàn phím
			user.setPassword(password);

			System.out.println("Nhập Fullname mới: ");
			String fullname = scanner.nextLine(); // Nhập fullname từ bàn phím
			user.setFullname(fullname);

			System.out.println("Nhập Email mới: ");
			String email = scanner.nextLine(); // Nhập email từ bàn phím
			user.setEmail(email);

			System.out.println("Nhập Admin (1. true - 0. false): ");
			String admin = scanner.nextLine(); // Nhập admin từ bàn phím
			user.setAdmin(admin.equals("1"));

			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
			System.out.println("Sửa thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e);
		}
	}

	public void deleteById() {
		try {
			System.out.println("Nhập ID: ");
			String id = scanner.nextLine(); // Nhập ID từ bàn phím

			User user = em.find(User.class, id);

			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
			System.out.println("Xóa thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e);
		}
	}
}
