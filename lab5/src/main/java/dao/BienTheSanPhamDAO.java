package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.BienTheSanPham;

public class BienTheSanPhamDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("de2");
	static EntityManager manager = factory.createEntityManager();

	public static List<BienTheSanPham> findAll() {
		String jpql = "SELECT o FROM BienTheSanPham o";
		TypedQuery<BienTheSanPham> query = manager.createNamedQuery("BienTheSanPham.findAll", BienTheSanPham.class);
		return query.getResultList();
	}

	public static BienTheSanPham findById(int id) {
		return manager.find(BienTheSanPham.class, id);
	}

	public static List<Object[]> getListBienThe() {
		String jpql = "SELECT bt.maBienThe, bt.tenBienThe, bt.moTa, bt.gia, bt.trangThai, sp.maSanPham, sp.tenSanPham "
				+ "FROM BienTheSanPham bt LEFT JOIN bt.listSanPham sp";
		Query query = manager.createQuery(jpql);
		return query.getResultList();
	}

	public static void create(BienTheSanPham entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			manager.getTransaction().rollback();
		}
	}

	public static void update(BienTheSanPham entity) {
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
