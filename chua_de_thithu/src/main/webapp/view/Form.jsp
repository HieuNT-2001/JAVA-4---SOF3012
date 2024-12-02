<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!doctype html>
		<html lang="en">

		<head>
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<title>Bootstrap demo</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
				crossorigin="anonymous">
			<link rel="stylesheet" type="text/css"
				href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" />
			<link href="https://cdn.datatables.net/2.1.8/css/dataTables.bootstrap5.css">
		</head>

		<body>
			<div class="container mt-3">
				<form class="row g-3" action="../nhan-vien/add" method="post">
					<h1 class="text-center">Quản lý nhân viên</h1>
					<div class="col-6">
						<label for="id" class="form-label">Mã</label>
						<input type="text" name="id" class="form-control" id="id" name="id" required>
					</div>
					<div class="col-6">
						<label for="address" class="form-label">Địa chỉ</label>
						<input type="text" name="address" class="form-control" id="address" required>
					</div>
					<div class="col-6">
						<label for="name" class="form-label">Tên</label>
						<input type="text" name="name" class="form-control" id="name" required>
					</div>
					<div class="col-6">
						<label class="form-label">Giới tính</label>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender" id="gender1" value="male"
								checked>
							<label class="form-check-label" for="gender1"> Nam </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender" id="gender2" value="female">
							<label class="form-check-label" for="gender2"> Nữ </label>
						</div>
					</div>

					<div class="col-auto">
						<button type="submit" class="btn btn-primary mb-3">Thêm</button>
					</div>
				</form>

				<table class="table" id="myTable">
					<thead>
						<tr>
							<th scope="col">STT</th>
							<th scope="col">Mã bạn</th>
							<th scope="col">Tên bạn</th>
							<th scope="col">Địa chỉ</th>
							<th scope="col">Giới tính</th>
							<th scope="col">Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${employees}" varStatus="status">
							<tr>
								<th scope="row">${status.count}</th>
								<td>${employee.id}</td>
								<td>${employee.name}</td>
								<td>${employee.address}</td>
								<td>${employee.gender ? "Nam" : "Nữ"}</td>
								<td>
									<!-- <a class="btn btn-danger" href="../nhan-vien/remove?id=${employee.id}">Remove</a> -->
									<form action="../nhan-vien/remove" method="post">
										<input type="hidden" name="id" value="${employee.id}">
										<button type="submit" class="btn btn-danger">Xóa</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
				crossorigin="anonymous"></script>

			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

			<!-- DataTables JS -->
			<script type="text/javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
			<script type="text/javascript">
				$(document).ready(function () {
					$("#myTable").DataTable({
						paging: true, // Bật phân trang
						pageLength: 5, // Số dòng trên mỗi trang
						lengthMenu: [5, 10, 25, 50], // Tùy chọn số dòng trên mỗi trang
						pagingType: "full_numbers", // Kiểu phân trang đầy đủ
					});
				});
			</script>
		</body>

		</html>
		​