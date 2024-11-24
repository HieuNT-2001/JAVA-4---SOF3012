package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Video;

public class VideoDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	static EntityManager manager = factory.createEntityManager();

	// Truy van toan bo danh sach video
	public static List<Video> findAll() {
		String jpql = "SELECT v FROM Video v";
		TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
		return query.getResultList();
	}

	// Tim video theo id
	public static Video findById(String id) {
		return manager.find(Video.class, id);
	}

	// Tim video theo tu khoa
	public static List<Video> findByKeyword(String keyword) {
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword OR v.description LIKE :keyword";
		TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
		query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}

	// Tim top 10 video duoc yeu thich nhieu nhat
	public static List<Object[]> findTop10Favorited() {
		String jpql = "SELECT v.id, v.title, v.poster, v.active, COUNT(f.id) " + "FROM Video v JOIN v.favorites f "
				+ "GROUP BY v.id, v.title, v.poster, v.active " + "ORDER BY COUNT(f.id) DESC";
		Query query = manager.createQuery(jpql);
		query.setMaxResults(10); // Giới hạn kết quả là 10 video
		return query.getResultList();
	}

	// Tim danh sach video khong duoc yeu thich
	public static List<Video> findUnFavorited() {
		String jpql = "SELECT v FROM Video v " + "WHERE v NOT IN (SELECT f.video FROM Favorite f)";
		TypedQuery<Video> query = manager.createQuery(jpql, Video.class);
		return query.getResultList();
	}

}
