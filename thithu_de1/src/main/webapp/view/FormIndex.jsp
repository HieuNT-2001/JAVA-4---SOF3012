<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Employee</title>
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
                rel="stylesheet">
            <!-- CSS của DataTables -->
            <link rel="stylesheet" type="text/css"
                href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" />
        </head>

        <body>
            <div class="container mt-3">
                <h1>Quản Lý Nhân Viên</h1>
                <form action="../nhan-vien/add" method="post">
                    <div class="row">
                        <div class="col-md-6">
                            <!-- id -->
                            <div class="mb-3">
                                <label for="id" class="form-label">Mã</label>
                                <input type="text" class="form-control" id="id" name="id" required>
                            </div>

                            <!-- Fullname -->
                            <div class="mb-3">
                                <label for="name" class="form-label">Tên:</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <!-- Address -->
                            <div class="mb-3">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="address" name="address" required>
                            </div>

                            <!-- Gender -->
                            <div class="mb-3">
                                <label for="" class="form-label">Giới tính:</label><br>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender" id="gender1" value="male"
                                        checked>
                                    <label class="form-check-label" for="gender1">
                                        Nam
                                    </label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender" value="female"
                                        id="gender2">
                                    <label class="form-check-label" for="gender2">
                                        Nữ
                                    </label>
                                </div>
                            </div>
                        </div>

                        <!-- button -->
                        <div class="mb-3 mt-3 text-center">
                            <button type="submit" class="btn btn-success">Add</button>
                        </div>
                    </div>
                </form>

                <!-- table -->
                <table class="table table-bordered myTable" id="myTable">
                    <thead class="table-light">
                        <tr>
                            <th>STT</th>
                            <th>Mã</th>
                            <th>Tên</th>
                            <th>Địa chỉ</th>
                            <th>Giới tính</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${employees }" varStatus="status">
                            <tr>
                                <td>${status.count }</td>
                                <td>${employee.id }</td>
                                <td>${employee.fullname }</td>
                                <td>${employee.address }</td>
                                <td>${employee.gender ? 'Nam' : 'Nữ' }</td>
                                <td>
                                    <form action="../nhan-vien/remove" method="post">
                                        <input type="hidden" name="id" value="${employee.id }">
                                        <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Bootstrap JS và các thư viện kèm theo -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

            <!-- jQuery -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

            <!-- JS của DataTables -->
            <script type="text/javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

            <script>
                $(document).ready(function () {
                    $(".myTable").DataTable({
                        paging: true, // Bật phân trang
                        pageLength: 5, // Số dòng trên mỗi trang
                        lengthMenu: [5, 10, 25, 50], // Tùy chọn số dòng trên mỗi trang
                        pagingType: "full_numbers", // Kiểu phân trang đầy đủ
                    });
                });
            </script>
        </body>

        </html>