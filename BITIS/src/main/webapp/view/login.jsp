
<!-- 1. base authentication: 
	username / password(mã hóa sha256) 
	
2. 	session base authentication:
	username / password(mã hóa sha256) / lưu session(lưu phiên đăng nhập, lưu js)
	
3. json web token
	username / password / -> trả về token(chìa khóa để giữ đăng nhập, lưu database)
	
4. oauth2
	đăng nhập bằng tài khoản google / facebook -->




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="http://localhost:8080/BITIS/view/static/css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h2 class="active"><a href="login">ĐĂNG NHẬP</a></h2>
            <h2 class="inactive"><a href="register">ĐĂNG KÝ</a></h2>
        </div>
        <form class="login-form" action="login" method="post">
        	<c:if test="${not empty notification}">
		        <p style="color: red;">${notification}</p>
		    </c:if>
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" placeholder="Vui lòng nhập email của bạn">
            
            <label for="password">Mật khẩu:</label>
            <input type="password" name="password" id="password" placeholder="Vui lòng nhập mật khẩu">
            
            <button type="submit" name="action" value="login" class="login-btn">ĐĂNG NHẬP</button>
        </form>
        <div class="login-links">
            <p>Bạn chưa có tài khoản? <a href="register">Đăng ký ngay</a></p>
            <p>Bạn quên mật khẩu? <a href="#">Khôi phục mật khẩu</a></p>
        </div>
        <div class="social-login">
            <p>Hoặc đăng nhập nhanh:</p>
            <button class="social-btn facebook">Đăng nhập với Facebook</button>
            <button class="social-btn google">Đăng nhập với Google</button>
        </div>
    </div>
</body>
</html>
