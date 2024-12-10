package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestDB {
	public static void main(String[] args) {
		// kết nối csdl
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BIITS");
		// truy vấn csdl
		EntityManager em = factory.createEntityManager();

		System.out.println("Kết nối thành công");
	}
}
