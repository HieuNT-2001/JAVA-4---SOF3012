package controller;

import java.io.IOException;

import dao.ProductDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BienTheSanPhamController
 */
@WebServlet("/FormController")
public class FormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("productDetails", ProductDetailDAO.findAll());
		request.getRequestDispatcher("./view/AddForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lay du lieu tu jsp
//		String name = request.getParameter("name");
//		String description = request.getParameter("description");
//		String inputPrice = request.getParameter("price"); // Kiem gia rong input chuoi gia ban
//		int status = Integer.parseInt(request.getParameter("status"));
		String action = request.getParameter("action");

//		// Kiem tra trong Form
//		if (name.isBlank() || description.isBlank() || inputPrice.isBlank()) {
//			return;
//		}
//
//		// chuyen chuoi gia ban sang so
//		double price = Double.parseDouble(inputPrice);

		// them bien the
//		if (action.equals("create")) {
//			// them vao db
//			ProductDetail entity = new ProductDetail(0, name, description, price, status, ProductDAO.findById(1));
//			ProductDetailDAO.create(entity);
//
//			// reload lai form
//			response.sendRedirect("./FormController");
//		}

		// do du lieu tu bang len form
		if (action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("productDetail", ProductDetailDAO.findById(id));
			request.getRequestDispatcher("./view/EditForm.jsp").forward(request, response);
		}

		// Update ban ghi trong db
//		if (action.equals("update")) {
//			// Lay id tu jsp
//			int id = Integer.parseInt(request.getParameter("id"));
//			int productId = Integer.parseInt(request.getParameter("productId"));
//			Product product = ProductDAO.findById(productId);
//
//			// sua trong db
//			ProductDetail entity = new ProductDetail(id, name, description, price, status, product);
//			ProductDetailDAO.update(entity);
//
//			// reload lai form
//			response.sendRedirect("./FormController");
//		}

	}

}
