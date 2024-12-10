package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DAO.KhachHangDAO;
import Model.KhachHang;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet({"/register", "/login"})
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
			case "/register":
				request.getRequestDispatcher("view/register.jsp").forward(request, response);
			case "/login":
				request.getRequestDispatcher("view/login.jsp").forward(request, response);
			default:
				break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String action = request.getParameter("action");
		
		switch (path) {
//		    đăng ký
			case "/register":
				if (action.equals("register")) {
					try {
						register(request, response);
					} catch (ServletException | IOException | ParseException e) {
						e.printStackTrace();
					}
				}
//			đăng nhập
			case "/login":
				if(action.equals("login")) {
					try {
						login(request, response);
					} catch (ServletException | IOException | ParseException e) {
						e.printStackTrace();
					}
				}
			default:
				break;
		}
		
		
	}
	
//	hàm đăng ký
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String MaKHRandom = generateRandomString(4);
		String Ho = request.getParameter("Ho");
		String Ten = request.getParameter("Ten");
		boolean GioiTinh = request.getParameter("gender").equals("male");
		
		String NgaySinhStr = request.getParameter("NgaySinh");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date NgaySinh = dateFormat.parse(NgaySinhStr);
		
		String Email = request.getParameter("Email");
		String SDT = request.getParameter("SDT");
		String MatKhau = request.getParameter("MatKhau");
		
		KhachHangDAO khd = new KhachHangDAO();
		KhachHang kh = new KhachHang(MaKHRandom, Ho, Ten, GioiTinh, NgaySinh, Email, SDT, MatKhau);
		System.out.println(kh.getHo());
		khd.create(kh);
		request.getRequestDispatcher("view/login.jsp").forward(request, response);
	}
	
//	hàm tạo 1 mã khách hàng ngẫu nhiên
	public static String generateRandomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        return IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(alphabet.charAt((int) (Math.random() * alphabet.length()))))
                .collect(Collectors.joining());
    }

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String email = request.getParameter("email");
		String matKhau = request.getParameter("password");
		
		KhachHangDAO khd = new KhachHangDAO();
		boolean result = khd.login(email, matKhau);
//		nếu đăng nhập thành công sẽ lưu phiên đăng nhập, thât bại sẽ hiển thị thông báo
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("userEmail", email);
			request.getRequestDispatcher("view/index.jsp").forward(request, response);
		} else {
			String message = "Đăng nhập thất bại!";
		    request.setAttribute("notification", message);
			request.getRequestDispatcher("view/login.jsp").forward(request, response);
		}
	}
}
