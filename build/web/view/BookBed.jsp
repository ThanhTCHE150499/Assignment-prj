<%-- 
    Document   : BookBed
    Created on : Oct 14, 2021, 8:49:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        .left{
            float: left;
            margin-top: 10px;
            margin-bottom: 10px;
            border-radius: 5px 5px 5px 5px;
            width: 40%;
            padding-right: 140px;
            height: 400px;
            text-align: center;
        }
        .right{
            float: right;
            width: 40%;
            margin-top: 10px;
            margin-bottom: 10px;
            height: 400px;
            border-radius: 5px 5px 5px 5px;
        }
    </style>
    <script>
        function myFunction(domid) {
            window.location.href = "bookbed?domid=" + domid;
        }
    </script>
    <body>
        <header>
            <h1>Book Bed</h1>
        </header>
        <div class="containers">
            <ul>
                <li><a>DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="home">Home</a></li>
            </ul>
            <section class = "left">
                <h1>Choose your option</h1><br>
                <form action="bookbed" method="POST">
                    DOM    <select name="domid" onchange="myFunction(this.value)">
                        <c:forEach items="${requestScope.doms}" var="d">
                            <option ${(d.id eq requestScope.domid) ?"selected=\"selected\"":""}  value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                    ROOM    <select name="roomcode">
                        <c:forEach items="${requestScope.rooms}" var="r">
                            <option value="${r.room_code}">${r.room_code}</option>
                        </c:forEach>
                    </select>
                    BED NUMBER    <select name="bednumber">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                    SEMESTER <select name="semesterid">
                        <c:forEach items="${requestScope.semesters}" var="s">
                            <option value="${s.id}">${s.numbersemester} - ${s.year}</option>
                        </c:forEach>
                    </select>
                    
                    <input type="submit" value="Book">
                </form>
            </section>
            <section class="right">
                <h1>Bookbed Request History</h1>
                <table border="1px">
                    <tr>
                        <td>RoomCode</td>
                        <td>Booked Date</td>
                        <td>Checkout Date</td>
                        <td>Bed number</td>
                        <td>Semester</td>
                        <td>Year</td>
                        <td>Status</td>
                    </tr>
                    <c:forEach items="${requestScope.books}" var="b">
                        <tr>
                            <td>${b.room.room_code}</td>
                            <td>${b.booked_date}</td>
                            <td>${b.date_checkout}</td>
                            <td>${b.bed.number}</td>
                            <td>${b.semester.numbersemester}</td>
                            <td>${b.semester.year}</td>
                            <td>
                                <c:if test="${b.status eq -1}">
                                    Rejected
                                </c:if>
                                <c:if test="${b.status eq 0}">
                                    Not approve yet
                                </c:if>
                                <c:if test="${b.status eq 1}">
                                    Approved
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
    </body>
</html>
