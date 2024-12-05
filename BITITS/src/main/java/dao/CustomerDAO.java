package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Customer;

public class CustomerDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("BITITS");
	static EntityManager manager = factory.createEntityManager();

	public static List<Customer> findAll() {
		String jpql = "SELECT c FROM Customer c";
		return manager.createQuery(jpql, Customer.class).getResultList();
	}

	public static Customer findById(int id) {
		return manager.find(Customer.class, id);
	}

	public static void create(Customer entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			manager.getTransaction().rollback();
		}
	}

}
