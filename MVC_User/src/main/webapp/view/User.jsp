<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>User Manager</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }

                .form-container {
                    display: flex;
                    flex-direction: column;
                    max-width: 500px;
                    margin-bottom: 20px;
                }

                .form-container div {
                    margin-bottom: 10px;
                }

                input[type="text"],
                input[type="password"] {
                    width: calc(100% - 10px);
                    padding: 5px;
                    border: 1px solid orange;
                }

                input[type="radio"] {
                    margin-right: 5px;
                }

                label {
                    font-weight: bold;
                }

                .action-buttons {
                    margin-top: 10px;
                }

                .action-buttons input {
                    margin-right: 10px;
                    padding: 5px 10px;
                    cursor: pointer;
                }

                .table-container {
                    margin-top: 20px;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                th,
                td {
                    padding: 8px;
                    text-align: left;
                    border: 1px solid #ddd;
                }

                th {
                    background-color: #4CAF50;
                    color: white;
                }

                td a {
                    color: blue;
                    text-decoration: underline;
                    cursor: pointer;
                }
            </style>
        </head>

        <body>
            <div class="form-container">
                <div>
                    <label for="id">Id:</label>
                    <input type="text" id="id" name="id">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password">
                </div>
                <div>
                    <label for="fullname">Fullname:</label>
                    <input type="text" id="fullname" name="fullname">
                    <label for="email">Email Address:</label>
                    <input type="text" id="email" name="email">
                </div>
                <div>
                    <label>Role:</label>
                    <input type="radio" id="admin" name="role" value="admin">
                    <label for="admin">Admin</label>
                    <input type="radio" id="user" name="role" value="user">
                    <label for="user">User</label>
                </div>
                <div class="action-buttons">
                    <input type="button" value="Create">
                    <input type="button" value="Update">
                    <input type="button" value="Delete">
                    <input type="reset" value="Reset">
                </div>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Fullname</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Rows will be dynamically added here -->
                        <c:forEach var="entity" items="${list }" varStatus="status">
                            <tr>
                                <td>${entity.id }</td>
                                <td>${entity.fullname }</td>
                                <td>${entity.password }</td>
                                <td>${entity.email }</td>
                                <td>${entity.admin == true ? 'admin' : 'user' }</td>
                                <td><a href="#">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>