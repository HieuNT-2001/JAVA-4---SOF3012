package servlet;

import java.io.IOException;

import dao.EmployeeDAO;
import entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormController
 */
@WebServlet({ "/nhan-vien/hien-thi", "/nhan-vien/add", "/nhan-vien/remove" })
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
		request.setAttribute("employees", EmployeeDAO.findAll());
		request.getRequestDispatcher("../view/Form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();

		// them nhan vien
		if (url.contains("add")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			boolean gender = request.getParameter("gender").equals("male");

			Employee employee = new Employee(id, name, address, gender);
			EmployeeDAO.create(employee);

			response.sendRedirect("../nhan-vien/hien-thi");
		}

		if (url.contains("remove")) {
			String id = request.getParameter("id");
			EmployeeDAO.remove(id);
			response.sendRedirect("../nhan-vien/hien-thi");
		}

	}

}
