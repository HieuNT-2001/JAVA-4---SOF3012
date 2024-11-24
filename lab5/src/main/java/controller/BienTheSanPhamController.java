package controller;

import java.io.IOException;

import dao.BienTheSanPhamDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BienTheSanPham;

/**
 * Servlet implementation class BienTheSanPhamController
 */
@WebServlet("/BienTheSanPhamController")
public class BienTheSanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BienTheSanPhamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("list", BienTheSanPhamDAO.getListBienThe());
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
			BienTheSanPham entity = new BienTheSanPham(0, name, description, price, status, null);
			BienTheSanPhamDAO.create(entity);

			response.sendRedirect("./BienTheSanPhamController");
		}

		// hien thi form edit bien the
		if (action.equals("edit")) {
			// lay du lieu tu jsp
			int id = Integer.parseInt(request.getParameter("id"));
			BienTheSanPham entity = BienTheSanPhamDAO.findById(id);
			request.setAttribute("entity", entity);
			request.getRequestDispatcher("./view/EditForm.jsp").forward(request, response);
		}

		if (action.equals("update")) {
			// lay du lieu tu jsp
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int status = Integer.parseInt(request.getParameter("status"));

			// sua trong db
			BienTheSanPham entity = new BienTheSanPham(id, name, description, price, status, null);
			BienTheSanPhamDAO.update(entity);

			response.sendRedirect("./BienTheSanPhamController");
		}

	}

}
