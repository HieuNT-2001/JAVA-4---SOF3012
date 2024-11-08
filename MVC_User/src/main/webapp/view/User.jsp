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
                <form action="../UserController/add" method="post">
                    <div>
                        <label for="id">Id:</label>
                        <input type="text" id="id" name="id" required ${isEnable ? '' : 'disabled' }
                            value="${entity.id }">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required value="${entity.password }">
                    </div>
                    <div>
                        <label for="fullname">Fullname:</label>
                        <input type="text" id="fullname" name="fullname" required value="${entity.fullname }">
                        <label for="email">Email Address:</label>
                        <input type="text" id="email" name="email" required value="${entity.email }">
                    </div>
                    <div>
                        <label>Role:</label>
                        <input type="radio" id="admin" name="role" value="admin" ${entity.admin ? 'checked' : '' }>
                        <label for="admin">Admin</label>
                        <input type="radio" id="user" name="role" value="user" ${entity.admin ? '' : 'checked' }>
                        <label for="user">User</label>
                    </div>
                    <div class="action-buttons">
                        <form method="post">
                            <input type="hidden" name="id" value="${entity.id }">
                            <button class="btn btn-primary" type="submit" formaction="../UserController/add" ${isEnable
                                ? '' : 'disabled' }>Create</button>
                            <button class="btn btn-primary" type="submit" formaction="../UserController/update"
                                ${isEnable ? 'disabled' : '' }>Update</button>
                            <input type="reset" value="Reset">
                        </form>

                    </div>
                </form>
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
                                <td>
                                    <form method="post">
                                        <input type="hidden" name="id" value="${entity.id }">
                                        <button class="btn btn-warning" type="submit"
                                            formaction="../UserController/edit">Edit</button>
                                        <button class="btn btn-danger" type="submit"
                                            formaction="../UserController/remove">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>