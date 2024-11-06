
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBTest {
	public static void main(String[] args) {
		// kết nối csdl
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
		
		// truy vấn csdl
//		EntityManager em = factory.createEntityManager();
//		
//		System.out.println("kết nối thành công");
		
		UserManager um = new UserManager();
		um.findByRole();
	}

}
