<%-- 
    Document   : DomDetail
    Created on : Oct 7, 2021, 8:52:20 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DomDetail</title
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <script>
        function myFunction(domid) {
            $("#roomtable tbody").empty();
            $.ajax({
                url: "domdetail",
                type: 'POST',
                data: {
                    did: domid
                },
                success: function (response) {
                    var toObject = JSON.parse(response);
                    var rooms = toObject.rooms;
                    for (let i = 0; i < rooms.length; i++) {
                        $("#roomtable tbody").append("<tr><td>" + rooms[i].room_code + "</td>" + "<td>" + rooms[i].sumbed + "</td>" + 
                                "<td><a href=beddetail?id=" + rooms[i].room_code +">Detail</a>" + "</td>" + "</tr>");
                    }
                }
            });
        }


    </script>
    <style>
        .left{
            float: left;
            background-color: #DDDDDD;
            margin-top: 10px;
            margin-bottom: 10px;
            border: 1px solid black;
            border-radius: 5px 5px 5px 5px;
            width: 20%;
            padding-right: 140px;
            height: 400px;
            text-align: center;
            font-size: 30px;
        }
        .right{
            float: right;
            width: 69%;
            background-color: #DDDDDD;
            margin-top: 10px;
            margin-bottom: 10px;
            height: 400px;
            border: 1px solid black;
            border-radius: 5px 5px 5px 5px;
        }
        .right table{
            width: 100%;
            height: 100%;
            text-align: center;
        }
        .left select{
            width: 50%;
            heigth: 50%;
            margin-top: 50%;
        }
        header{
            background-color: #99FFFF;
            display: block;
            padding-bottom: 5px;
            text-align: center;
            padding-top: 30px;
        }
        footer{
            clear:both;
            text-align: center;
            background-color: #99FFFF;
            padding-top: 100px;
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
    <body>
        <div class="container">
            <header>
                <h1>DOM DETAIL</h1>
            </header>
            <ul>
                <li><a>DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="home">Home</a></li>
            </ul>
            <section class="left">
                <select name="did" onchange="myFunction(this.value)">
                    <c:forEach items="${requestScope.doms}" var="d">
                        <option ${(d.id eq requestScope.did)?"selected=\"selected\"":"" } value="${d.id}">${d.name}</option>
                    </c:forEach>
                </select>
            </section>
            <section class="right">
                <table border="1px" id="roomtable">
                    <thead>
                        <tr>
                            <td>Room Code</td>
                            <td>Sum of bed</td>
                            <td>Bed Detail</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.rooms}" var="r">
                            <tr>
                                <td>${r.room_code}</td>
                                <td>${r.sumbed}</td>
                                <td><a href="beddetail?id=${r.room_code}">Detail</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
            <footer>
                <h1></h1>
            </footer>
        </div>
    </body>
</html>
