package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Product;

public class ProductDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("de2");
	static EntityManager manager = factory.createEntityManager();

	public static List<Product> findAll() {
		TypedQuery<Product> query = manager.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	public static Product findById(int id) {
		return manager.find(Product.class, id);
	}
}
