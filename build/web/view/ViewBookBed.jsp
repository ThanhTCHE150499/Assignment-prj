<%-- 
    Document   : ViewBookBed
    Created on : Oct 28, 2021, 9:39:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookBed Request</title>
        <style>
            header{
                background-color: #FF7F50;
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
            footer{
                clear:both;
                text-align: center;
                background-color: #FF7F50;
            }
            .left{
                float: left;
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                width: 69%;
                padding-right: 140px;
                height: 400px;
                text-align: center;
                font-size: 30px;
            }
            .right{
                float: right;
                width: 20%;
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                height: 400px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                text-align: center;
            }
            button{
                width: 200px;
                margin-bottom: 10px;
            }
            a{
                text-decoration: none;
            }
            button{
                width: 500px;
                height: 50px;
            }
            .right .money{
                border: 1px solid black;
                width: 70%;
                margin-left: auto;
                margin-right: auto;
                margin-top: 100px;
                text-align: center;
                padding-bottom: 10px;
            }
            .right button{
                width: 50%;
                margin-top: 10px;
            }
            table{
                width: 100%;
                text-align: center;
            }
            .submit{
                margin-top: 10px;
                font-size: 100%;
            }
            .pagger a{
                border-color: blue;
                background-color: blue;
                color:white;
                border-style: solid;
                margin-left: 2px;
                padding: 2px;
            }
            .pagger span{
                font-weight: bold;
            }
            .tablerequest{
                margin-bottom: 10px;
            }
            form{
                margin: 10px;
            }
        </style>
        <script>
            function createPagger(div, pageindex, gap, totalpage, totalsearchrequest) {
                var container = document.getElementById(div);
                var q = document.getElementById("q").value;

                //container.innerHTML = pageindex + ' ' + gap + ' ' + totalpage
                if (pageindex - gap > 1)
                    container.innerHTML += '<a href="viewbookbed?page=1&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">First</a>';

                for (var i = pageindex - gap; i < pageindex; i++) {
                    if (i > 0)
                        container.innerHTML += '<a href="viewbookbed?page=' + i + '&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">' + i + '</a>';
                }
                if (totalpage != 0) {
                    container.innerHTML += '<span>' + pageindex + '</span>'
                }
                for (var i = pageindex + 1; i <= pageindex + gap; i++) {
                    if (i <= totalpage)
                        container.innerHTML += '<a href="viewbookbed?page=' + i + '&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">' + i + '</a>';
                }
                if (pageindex + gap < totalpage)
                    container.innerHTML += '<a href="viewbookbed?page=' + totalpage + '$totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">Last</a>';
            }
        </script>
    </head>
    <body>
        <header>
            <h1>Book Bed Requests</h1>
        </header>
        <div class="container">
            <ul>
                <li><a href="adminhome">DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="adminhome">Home</a></li>
            </ul>

            <form action="viewbookbed" method="POST">
                StudentID <input id="q" type="text" name="search" value="${requestScope.q}">
                <input type="submit" value="Search">
            </form>
            <form action="updatebookbed" method="POST">
                <table class="tablerequest" border="1px">
                    <tr>
                        <th>StudentID</th>
                        <th>RoomCode</th>
                        <th>Booked Date</th>
                        <th>Booked_Checkout</th>
                        <th>Bednumber</th> 
                        <th>Number of Semester</th>
                        <th>Year</th>
                        <th>Status</th>
                    </tr>
                    <c:set var="rid" value=""></c:set>
                    <c:forEach items="${requestScope.requests}" var="r">
                       
                            <tr>
                                <td>${r.student.id}</td>
                            <td>${r.room.room_code}</td>
                            <td>${r.booked_date}</td>
                            <td>${r.date_checkout}</td>
                            <td>${r.bed.number}</td>
                            <td>${r.semester.numbersemester}</td>
                            <td>${r.semester.year}</td>
                            <td>
                                <c:if test="${r.status == 0}">
                                     <c:set var="rid" value="${rid.concat(r.student.id).concat(',').concat(r.semester.id).concat(',')}"></c:set>
                                    <div class="custom-control custom-radio">
                                        <input ${r.status == 1 ? "checked='checked'" : "" } type="radio" value="1" id="${('').concat(r.student.id).concat('.').concat(r.semester.id)}" name="${('').concat(r.student.id).concat(',').concat(r.semester.id)}" class="custom-control-input">
                                        <label class="custom-control-label" for="${('').concat(r.student.id).concat('.').concat(r.semester.id)}">Approve</label>
                                    </div>
            
                                    <div class="custom-control custom-radio">
                                        <input ${r.status == -1 ? "checked='checked'" : "" } type="radio" value="-1" id="${('').concat(r.student.id).concat('.').concat(r.semester.id)}" name="${('').concat(r.student.id).concat(',').concat(r.semester.id)}"  class="custom-control-input">
                                        <label class="custom-control-label" for="${('').concat(r.student.id).concat('.').concat(r.semester.id)}">Reject</label>
                                    </div>
                                </c:if>
                                    <c:if test="${r.status == 1}">
                                        Approve
                                    </c:if>
                                        <c:if test="${r.status == -1}">
                                        Reject
                                    </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <input type="hidden" name="totalrequest" value="${rid}">
                </table>
                <div id="pagger" class="pagger"></div>
                <script>
                    createPagger('pagger',${requestScope.pageid}, 2,${requestScope.totalpage},${requestScope.totalsearchrequest})
                </script>
                <input class="submit" type="submit" value="Submit">
            </form>
        </div>
        <footer>
            <h3>Information Contact</h3>
            <p>Facebook: Thái Thế Nguyễn</p>
            <p>Gmail: thaithenguyen2001@gmail.com</p>
            <p>Phone: 0964040501</p>
        </footer>
    </body>
</html>
