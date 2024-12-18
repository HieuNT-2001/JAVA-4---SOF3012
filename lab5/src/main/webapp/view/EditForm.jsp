<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>

            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>Form thêm biến thể sản phẩm</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                    crossorigin="anonymous">
            </head>
        </head>

        <body>
            <div class="container mt-3">
                <form action="./FormController" method="post">
                    <!-- id biến thể -->
                    <input type="hidden" name="id" value="${productDetail.id}">

                    <!-- tên biến thể -->
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên biến thể</label>
                        <input type="text" class="form-control" id="name" name="name"
                            value="${productDetail.productDetailName}" required>
                    </div>

                    <!-- mô tả -->
                    <div class="mb-3">
                        <label for="description" class="form-label">Mô tả</label>
                        <input type="text" class="form-control" id="description" name="description"
                            value="${productDetail.description}" required>
                    </div>

                    <!-- giá -->
                    <div class="mb-3">
                        <label for="price" class="form-label">Giá</label>
                        <input type="number" class="form-control" id="price" name="price" value="${productDetail.price}"
                            required>
                    </div>

                    <!-- trạng thái -->
                    <label class="form-label">Trạng thái</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" id="status1" value="1"
                            ${productDetail.status==1 ? 'checked' : '' }>
                        <label class="form-check-label" for="status1">1</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" id="status2" value="0"
                            ${productDetail.status !=1 ? 'checked' : '' }>
                        <label class="form-check-label" for="status2">0</label>
                    </div><br>

                    <!-- Sản phẩm -->
                    <select class="form-select" name="product">
                        <c:forEach var="product" items="${products }" varStatus="status">
                            <option value="${product.id}" ${product.id==productDetail.product.id ? 'selected' : '' }>
                                ${product.productName }
                            </option>
                        </c:forEach>
                    </select>

                    <button type="submit" class="btn btn-primary" name="action" value="update">Lưu</button>
                </form>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
        </body>

        </html>