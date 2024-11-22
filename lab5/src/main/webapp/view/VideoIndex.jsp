<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<title>Video Index</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
				crossorigin="anonymous">
		</head>

		<body>
			<div class="container mt-3">
				<nav class="navbar bg-body-tertiary">
					<div class="container-fluid">
						<a class="navbar-brand">Navbar</a>
						<form class="d-flex" role="search" action="./VideoController" method="post">
							<input class="form-control me-2" type="search" name="search" aria-label="Search"
								placeholder="Tìm kiếm">
							<button class="btn btn-outline-success" type="submit">Tìm kiếm</button>
						</form>
					</div>
				</nav>

				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Title</th>
							<th scope="col">Poster</th>
							<th scope="col">Description</th>
							<th scope="col">Acitve</th>
							<th scope="col">Views</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${listVideo }" varStatus="status">
							<tr>
								<th scope="row">${status.count }</th>
								<td>${item.title }</td>
								<td>${item.poster }</td>
								<td>${item.description }</td>
								<td>${item.active }</td>
								<td>${item.views }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
				crossorigin="anonymous"></script>
		</body>

		</html>