package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Java4_QuanLy");
	static EntityManager manager = factory.createEntityManager();

	public static void main(String[] args) {
		System.out.println(manager);
	}
}
