package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Employee;

public class EmployeeDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("QuanLyNhanVien");
	static EntityManager manager = factory.createEntityManager();

	public static List<Employee> findAll() {
		TypedQuery<Employee> query = manager.createNamedQuery("Employee.findAll", Employee.class);
		return query.getResultList();
	}

	public static Employee findById(String id) {
		return manager.find(Employee.class, id);
	}

	public static void create(Employee entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			manager.getTransaction().rollback();
		}
	}

	public static void remove(String id) {
		Employee entity = findById(id);
		try {
			manager.getTransaction().begin();
			manager.remove(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			manager.getTransaction().rollback();
		}
	}

//	public static void main(String[] args) {
//		System.out.println(findAll());
//	}

}
