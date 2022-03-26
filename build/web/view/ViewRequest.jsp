<%-- 
    Document   : ViewRequest
    Created on : Oct 21, 2021, 10:48:33 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Requests</title>
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
                    container.innerHTML += '<a href="viewrequest?page=1&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">First</a>';

                for (var i = pageindex - gap; i < pageindex; i++) {
                    if (i > 0)
                        container.innerHTML += '<a href="viewrequest?page=' + i + '&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">' + i + '</a>';
                }
                if (totalpage != 0) {
                    container.innerHTML += '<span>' + pageindex + '</span>'
                }
                for (var i = pageindex + 1; i <= pageindex + gap; i++) {
                    if (i <= totalpage)
                        container.innerHTML += '<a href="viewrequest?page=' + i + '&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">' + i + '</a>';
                }
                if (pageindex + gap < totalpage)
                    container.innerHTML += '<a href="viewrequest?page=' + totalpage + '&totalsearchrequest=' + totalsearchrequest + '&search=' + q + '">Last</a>';
            }
        </script>
    </head>
    <body>
        <header>
            <h1>Student Requests</h1>
        </header>
        <div class="container">
            <ul>
                <li><a href="adminhome">DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="adminhome">Home</a></li>
            </ul>

            <form action="viewrequest" method="POST">
                StudentID <input id="q" type="text" name="search" value="${requestScope.q}">
                <input type="submit" value="Search">
            </form>
            <form action="updaterequest" method="POST">
                <table class="tablerequest" border="1px">
                    <tr>
                        <th>StudentID</th>
                        <th>Title</th>
                        <th>Note</th>
                        <th>Status</th>
                    </tr>
                    <c:set var="rid" value=""></c:set>
                    <c:forEach items="${requestScope.requests}" var="r">
                        <c:set var="rid" value="${rid.concat(r.id).concat(',')}"></c:set>
                            <tr>
                                <td>${r.student.id}</td>
                            <td>${r.title}</td>
                            <td>${r.note}</td>
                            <td>
                                <div class="custom-control custom-radio">
                                    <input ${r.status == 1 ? "checked='checked'" : "" } type="radio" value="1" id="${r.id}" name="${r.id}" class="custom-control-input">
                                    <label class="custom-control-label" for="${r.id}">Approve</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input ${r.status == -1 ? "checked='checked'" : "" } type="radio" value="-1" id="${r.id}" name="${r.id}"  class="custom-control-input">
                                    <label class="custom-control-label" for="${r.id}">Reject</label>
                                </div>
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
            <p>Facebook: Trần Công Thành</p>
            <p>Gmail: ThanhTCHE150499@fpt.edu.vn</p>
            <p>Phone: 0376728801</p>
        </footer>
    </body>
</html>
