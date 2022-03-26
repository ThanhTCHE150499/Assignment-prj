<%-- 
    Document   : AddAdmin
    Created on : Oct 19, 2021, 9:50:08 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Admin</title>
        <style>
            header{
                background-color: #FF7F50;
                display: block;
                padding-bottom: 5px;
                text-align: center;
            }
            .left{
                float: left;
                background-color: white;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                width: 44%;
                padding-right: 140px;
                height: 400px;
                text-align: center;
                font-size: 19px;
            }
            .left h1{
                margin-left: 100px;
                margin-right: auto;
            }
            .left table{
                width: 700px;
                height: 100px;
                text-align: center;
            }
            .right table{
                width: 600px;
                height: 100px;
                text-align: center;
            }
            .right{
                float: right;
                width: 44%;
                background-color: white;
                margin-top: 10px;
                margin-bottom: 10px;
                height: 400px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                text-align: center;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover {
                background-color: #111;
            }
            li{
                float:right;
            }
            li:first-child{
                float:left;
            }
            footer{
                clear:both;
                text-align: center;
                background-color: #FF7F50;
            }
            .add{
                border-color: #00FF7F;
                background-color: #3CB371;
            }
            body{
                background-color: #DDDDDD
            }
            table {
                width: 100%;        
                font-family: arial, sans-serif;
                border-collapse: collapse;
            }
            .left table{
                margin-left: 20px;
            }
            .right table{
                width: 90%;
                margin-left: 30px;
            }
            th, td {
                text-align: center;
                border-top: 1px solid #dee2e6;
            }
            tbody tr:nth-child(odd) {
                background-color: #f2f2f2;
            }
            .del{
                border-color: #B22222;
                background-color: #FF0000;
            }
        </style>
        <script>
            function Add(username) {
                window.location.href = "admin?username=" + username;
            }

            function Delete(username) {
                var c = "Are you sure?";
                if (c) {
                    window.location.href = "deleteadmin?username=" + username;
                }
            }
        </script>
    </head>
    <body>
        <header>
            <h1>Add Admin System</h1>
        </header>

        <div class="container">
            <ul>
                <li><a href="adminhome">DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="adminhome">Home</a></li>
            </ul>
            <section class = "left">
                <h1>List of Students</h1>
                <table class="table1" border="2px">
                    <tr>
                        <th>StudentID</th>
                        <th>StudentName</th>
                        <th>Username</th>
                        <th>Add</th>
                    </tr>
                    <c:forEach items="${requestScope.students}" var="s">
                        <tr>
                            <td>${s.id}</td>
                            <td>${s.name}</td>
                            <td>${s.username}</td>
                            <td>
                                <input class="add" type="button" onclick="Add(this.value);" value="${s.username}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
            <section class="right">
                <h1>List of Admins</h1>
                <table border="2px">
                    <tr>
                        <th>AdminID</th>
                        <th>AdminName</th>
                        <th>Username</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${requestScope.admins}" var="admin">
                        <tr>
                            <td>${admin.id}</td>
                            <td>${admin.name}</td>
                            <td>${admin.username}</td>
                            <td>
                                <input class="del" type="button" onclick="Delete(this.value);" value="${admin.username}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
        <footer>
            <h3>Information Contact</h3>
            <p>Facebook: Trần Công Thành</p>
            <p>Gmail: ThanhTCHE150499@fpt.edu.vn</p>
            <p>Phone: 0376728801</p>
        </footer>
    </body>
</html>
