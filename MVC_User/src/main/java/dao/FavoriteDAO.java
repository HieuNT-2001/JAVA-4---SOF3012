package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Favorite;

public class FavoriteDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	static EntityManager em = factory.createEntityManager();

	public static List<Favorite> findAll() {
		String jpql = "SELECT o From Favorite o";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		List<Favorite> list = query.getResultList();
		return list;
	}
	
	public static void main(String[] args) {
		for (Favorite favorite : findAll()) {
			System.out.println("Id: " + favorite.getId());
			System.out.println("VideoId: " + favorite.getVideo().getTitle());
			System.out.println("UserId: " + favorite.getUser().getFullname());
			System.out.println("Date: " + favorite.getLikeDate());
		}
	}

}
