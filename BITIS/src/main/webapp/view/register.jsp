<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link rel="stylesheet" href="http://localhost:8080/BITIS/view/static/css/register.css">
</head>
<body>
    <div class="register-container">
        <div class="register-header">
            <h2 class="active"><a href="login">ĐĂNG NHẬP</a></h2>
            <h2 class="inactive"><a href="register">ĐĂNG KÝ</a></h2>
        </div>
        <form class="register-form" action="register" method="post">
            <label for="last-name">Họ:</label>
            <input type="text" name="Ho" id="last-name" placeholder="Họ">

            <label for="first-name">Tên:</label>
            <input type="text" name="Ten" id="first-name" placeholder="Tên">

            <div class="gender-selection">
                <label>Giới tính:</label>
                <input type="radio" id="female" name="gender" value="female">
                <label for="female">Nữ</label>
                <input type="radio" id="male" name="gender" value="male">
                <label for="male">Nam</label>
            </div>

            <label for="dob">Ngày sinh:</label>
            <input type="date" name="NgaySinh" id="dob">

            <label for="email">Email:</label>
            <input type="email" name="Email" id="email" placeholder="Email">

            <label for="phone">Số điện thoại:</label>
            <input type="text" name="SDT" id="phone" placeholder="Số điện thoại">

            <label for="password">Mật khẩu:</label>
            <input type="password" name="MatKhau" id="password" placeholder="Mật khẩu">

            <p class="recaptcha-notice">
                This site is protected by reCAPTCHA and the Google 
                <a href="#">Privacy Policy</a> and 
                <a href="#">Terms of Service</a> apply.
            </p>

            <button type="submit" name="action" value="register" class="register-btn">ĐĂNG KÝ</button>
        </form>
        <div class="register-links">
            <p>Bạn đã có tài khoản? <a href="#">Đăng nhập ngay</a></p>
        </div>
    </div>
</body>
</html>
