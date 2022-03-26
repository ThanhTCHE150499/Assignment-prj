<%-- 
    Document   : StudentRequest
    Created on : Oct 10, 2021, 7:41:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Request</title>
    </head>
    <style>
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
        a{
            text-decoration: none;
        }
        button{
            margin: 5px;
        }
    </style>
    <body>
        <header>
            <h1>Place History</h1>
        </header>
        <ul>
            <li><a>DOM INFORMATION</a></li>
            <li><a href="logout">Logout</a></li>
            <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
            <li><a href="about">About</a></li>    
            <li><a class="active" href="home">Home</a></li>
        </ul>
        <table border="1px">
            <tr>
                <th>Title</th>
                <th>Note</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${requestScope.requests}" var="r">
                <tr>
                    <td>${r.title}</td>
                    <td>${r.note}</td>
                    <td>
                        <c:if test="${r.status eq 0}">
                            Not approved yet
                        </c:if>
                        <c:if test="${r.status eq 1}">
                            Approved
                        </c:if>
                        <c:if test="${r.status eq -1}">
                            Rejected
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button><a href="createrequest">Create new request</a></button><br>
        <button><a href="home">Back to home page</a></button>
    </body>
</html>
