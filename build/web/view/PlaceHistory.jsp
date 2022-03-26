<%-- 
    Document   : PlaceHistory
    Created on : Oct 8, 2021, 12:05:07 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table{
                width: 100%;
                height:100%;
                margin-left: auto;
                margin-right: auto;
            }
            header{
                background-color: #99FFFF;
                display: block;
                padding-bottom: 5px;
                text-align: center;
            }
            h1{
                padding-top: 50px;
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
        </style>
    </head>
    <body>
        <header>
            <h1>Place History</h1>
        </header>
        <div class="container">
            <ul>
                <li><a href="home">DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="home">Home</a></li>
            </ul>
            <table border="1px">
                <tr>
                    <td>Student ID</td>
                    <td>Room</td>
                    <td>Dom</td>
                    
                </tr>
                <c:forEach items="${requestScope.details}" var="d">
                    <tr>
                        <td>${d.student.id}</td>
                        <td>${d.room.room_code}</td>
                        <td><a href="historydetail?id=${d.id}">Detail</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
