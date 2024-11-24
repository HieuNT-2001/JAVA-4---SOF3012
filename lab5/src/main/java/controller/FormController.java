package controller;

import java.io.IOException;

import dao.ProductDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductDetail;

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
		String action = request.getParameter("action");

		// them bien the
		if (action.equals("create")) {
			// lay du lieu tu jsp
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int status = Integer.parseInt(request.getParameter("status"));

			// them vao db
			ProductDetail entity = new ProductDetail(0, name, description, price, status, null);
			ProductDetailDAO.create(entity);

			response.sendRedirect("./FormController");
		}

		// do du lieu tu bang len form
		if (action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("productDetails", ProductDetailDAO.findAll());
			request.setAttribute("productDetail", ProductDetailDAO.findById(id));
			request.getRequestDispatcher("./view/EditForm.jsp").forward(request, response);
		}

		// Update ban ghi trong db
		if (action.equals("update")) {
			// lay du lieu tu jsp
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int status = Integer.parseInt(request.getParameter("status"));

			// sua trong db
			ProductDetail entity = new ProductDetail(id, name, description, price, status, null);
			ProductDetailDAO.update(entity);

			// reload lai form
			response.sendRedirect("./FormController");
		}

	}

}
