package controller;

import java.io.IOException;

import dao.ProductDAO;
import dao.ProductDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
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
		request.setAttribute("products", ProductDAO.findAll());
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
			ProductDetail productDetail = getProductDetail(request);
			if (productDetail != null) {
				ProductDetailDAO.create(productDetail);
				response.sendRedirect("./FormController");
			}
		}

		// mo Form edit
		if (action.equals("edit")) {
			String id = request.getParameter("productDetailId");
			ProductDetail productDetail = ProductDetailDAO.findById(Integer.parseInt(id));
			request.setAttribute("productDetail", productDetail);
			request.setAttribute("products", ProductDAO.findAll());
			request.getRequestDispatcher("./view/EditForm.jsp").forward(request, response);
		}

		// Update bien the
		if (action.equals("update")) {
			ProductDetail productDetail = getProductDetail(request);
			if (productDetail != null) {
				ProductDetailDAO.update(productDetail);
				response.sendRedirect("./FormController");
			}
		}
	}

	// lay productDetail tu Form
	ProductDetail getProductDetail(HttpServletRequest request) {
		// lay du lieu tu form jsp
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String status = request.getParameter("status");
		Product product = getProduct(request);

		// validate du lieu
		if (isValidate(name, description, price)) {
			ProductDetail productDetail = new ProductDetail();
			productDetail.setId(Integer.parseInt(id));
			productDetail.setProductDetailName(name);
			productDetail.setDescription(description);
			productDetail.setPrice(Double.parseDouble(price));
			productDetail.setStatus(Integer.parseInt(status));
			productDetail.setProduct(product);
			return productDetail;
		}

		return null;
	}

	// lay product tu truong product
	Product getProduct(HttpServletRequest request) {
		String productId = request.getParameter("product");
		return ProductDAO.findById(Integer.parseInt(productId));
	}

	boolean isValidate(String name, String description, String price) {
		return !(name.isBlank() || description.isBlank() || price.isBlank());
	}

}
