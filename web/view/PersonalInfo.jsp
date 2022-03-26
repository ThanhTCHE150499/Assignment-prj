<%-- 
    Document   : PersonalInfo
    Created on : Oct 4, 2021, 10:27:48 PM
    Author     : Admin
--%>

<%@page import="Model.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function doUpdate(username) {
                window.location.href = "update?username=" + username;
            }
        </script>
    </head>
    <body>
        <div class="container">
        <h1>PERSONAL INFORMATION</h1>
        <table border="1px">
            <tr>
                <td>StudentID</td>
                <td>${requestScope.student.id}</td>
            </tr>
            <tr>
                <td> StudentName</td>
                <td>${requestScope.student.name}</td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <c:if test="${requestScope.student.gender}">
                    Male
                    </c:if>
                    <c:if test="${!requestScope.student.gender}">
                    Female
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>DOB</td>
                <td>${requestScope.student.dob}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>${requestScope.student.address}</td>
            </tr>
            <tr>
                <td>Username</td>
                <td>${requestScope.student.username}</td>
            </tr>
        </table>
               Click to Update <input type="button" onclick="doUpdate(this.value);" value="${requestScope.student.username}">
        </div>
    </body>
</html>
