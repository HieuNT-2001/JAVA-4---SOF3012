package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.List;

import dao.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean isEnable = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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
		request.setAttribute("list", UserDAO.findAll());
		request.setAttribute("isEnable", isEnable);
		request.setAttribute("entity", new User("", "", "", "", true));
		request.getRequestDispatcher("./view/User.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		// them user
		if (action.equals("create")) {
			String id = request.getParameter("id");
			String fullname = request.getParameter("fullname");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			boolean isAdmin = request.getParameter("role").equals("admin");
			User entity = new User(id, password, fullname, email, isAdmin);
			UserDAO.create(entity);
			response.sendRedirect("./UserController");
		}

		// Do user len form de edit
		if (action.equals("edit")) {
			isEnable = false;
			String id = request.getParameter("id");
			request.setAttribute("list", UserDAO.findAll());
			request.setAttribute("entity", UserDAO.findById(id));
			request.setAttribute("isEnable", isEnable);
			request.getRequestDispatcher("./view/User.jsp").forward(request, response);
		}

		// update user voi thong tin trong form
		if (action.equals("update")) {
			isEnable = true;
			String id = request.getParameter("id");
			String fullname = request.getParameter("fullname");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			boolean isAdmin = request.getParameter("role").equals("admin");
			User entity = new User(id, password, fullname, email, isAdmin);
			UserDAO.update(entity);
			response.sendRedirect("./UserController");
		}

		// xoa user
		if (action.equals("remove")) {
			String id = request.getParameter("id");
			UserDAO.deleteById(id);
			response.sendRedirect("./UserController");
		}
	}

}
