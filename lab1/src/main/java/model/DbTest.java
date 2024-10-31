package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.UserManager;

public class DbTest {

	public static void main(String[] args) {
		// kết nối csdl
		// EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
		
		// truy vấn csdl
		// EntityManager em = factory.createEntityManager();
		
		// System.out.println("Kết nối db thành công");
		
		// chạy code UserManager
		UserManager um = new UserManager();
		um.finalAll();
	}
	
}
