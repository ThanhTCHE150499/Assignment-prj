<%-- 
    Document   : Bed
    Created on : Oct 10, 2021, 5:30:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bed Information</title>
    </head>
    <style>
        table{
            width: 50%;
            height: 500px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }
    </style>
    <body>
        <table border="1px">
            <tr>
                <td>Bed Number</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${requestScope.beds}" var="b">
                <tr>
                    <td>${b.number}</td>
                    <td>${b.status}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
