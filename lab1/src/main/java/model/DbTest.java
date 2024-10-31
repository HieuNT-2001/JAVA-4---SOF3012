package model;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.UserManager;

public class DbTest {

	public static void main(String[] args) {
		// kết nối csdl
		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("PolyOE");

		// truy vấn csdl
		// EntityManager em = factory.createEntityManager();

		// System.out.println("Kết nối db thành công");

		// chạy code UserManager
		UserManager um = new UserManager();
		Scanner scanner = new Scanner(System.in);
		String choice;
		while (true) {
			System.out.println("-------------------------------------------------------");
			System.out.println("1. Xem danh sách users");
			System.out.println("2. Tìm user theo ID");
			System.out.println("3. Thêm user");
			System.out.println("4. Sửa user");
			System.out.println("5. Xóa user");
			System.out.println("0. Thoát");
			System.out.println("-------------------------------------------------------");
			choice = scanner.nextLine();
			switch (choice) {
			case "1":
				um.finalAll();
				break;
			case "2":
				um.findById();
				break;
			case "3":
				um.create();
				break;
			case "4":
				um.update();
				break;
			case "5":
				um.deleteById();
				break;
			case "0":
				return;
			default:
				break;
			}
		}
	}

}
