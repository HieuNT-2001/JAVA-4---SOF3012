package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Video;

public class VideoDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	static EntityManager em = factory.createEntityManager();

	public static List<Video> findAll() {
		String jpql = "SELECT o FROM Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		List<Video> list = query.getResultList();
		return list;
	}

	public static void main(String[] args) {
		for (Video video : findAll()) {
			System.out.println("Id: " + video.getId());
			System.out.println("Title: " + video.getTitle());
			System.out.println("Poster: " + video.getTitle());
		}
	}

}
