<%-- 
    Document   : HistoryDetail
    Created on : Oct 10, 2021, 4:44:43 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail</title>
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
                <td>Date Booked</td>
                <td>${requestScope.detail.date_booked}</td>
            </tr>
            <tr>
                <td>Date Checkout</td>
                <td>${requestScope.detail.date_checkout}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${requestScope.detail.price}</td>
            </tr>
            <tr>
                <td>Bed Number</td>
                <td>${requestScope.detail.bed.number}</td>
            </tr>
            <tr>
                <td>Semester</td>
                <td>${requestScope.detail.semester.numbersemester}</td>
            </tr>
            <tr>
                <td>Year</td>
                <td>${requestScope.detail.semester.year}</td>
            </tr>
        </table>
    </body>
</html>
