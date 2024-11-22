package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Video;

import java.io.IOException;
import java.util.List;

import dao.VideoDAO;

/**
 * Servlet implementation class VideoController
 */
@WebServlet("/VideoController")
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// truy van video theo tu khoa
		request.setAttribute("listVideo", VideoDAO.findByKeyword(""));
		request.getRequestDispatcher("view/VideoIndex.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lay tu khoa tu thanh tim kiem trong VideoIndex.jsp
		String keyword = request.getParameter("search");
		request.setAttribute("listVideo", VideoDAO.findByKeyword(keyword));
		request.getRequestDispatcher("view/VideoIndex.jsp").forward(request, response);
	}

}
