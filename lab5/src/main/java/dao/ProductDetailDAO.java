package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ProductDetail;

public class ProductDetailDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("de2");
	static EntityManager manager = factory.createEntityManager();

	public static List<ProductDetail> findAll() {
		TypedQuery<ProductDetail> query = manager.createNamedQuery("ProductDetail.findAll", ProductDetail.class);
		return query.getResultList();
	}

	public static ProductDetail findById(int id) {
		return manager.find(ProductDetail.class, id);
	}

//	public static List<Object[]> getListBienThe() {
//		String jpql = "SELECT bt.maBienThe, bt.tenBienThe, bt.moTa, bt.gia, bt.trangThai, sp.maSanPham, sp.tenSanPham "
//				+ "FROM BienTheSanPham bt LEFT JOIN bt.listSanPham sp";
//		Query query = manager.createQuery(jpql);
//		return query.getResultList();
//	}

	public static void create(ProductDetail entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			manager.getTransaction().rollback();
		}
	}

	public static void update(ProductDetail entity) {
		try {
			manager.getTransaction().begin();
			manager.merge(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			manager.getTransaction().rollback();
		}
	}

}
