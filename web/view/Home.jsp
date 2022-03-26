<%-- 
    Document   : Home
    Created on : Sep 28, 2021, 9:17:36 PM
    Author     : Admin
--%>

<%@page import="Model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <style>
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
            footer{
                clear:both;
                text-align: center;
                background-color: #99FFFF;
            }
            .left{
                float: left;

                margin-top: 10px;
                margin-bottom: 10px;
                position: absolute;
                top: 8px;
                left: 16px;

                width: 69%;
                padding-right: 140px;
                height: 400px;
                text-align: center;
                font-size: 30px;
            }
            .right{
                float: right;
                width: 20%;

                margin-top: 10px;
                margin-bottom: 10px;
                height: 400px;
                position: absolute;
                top: 8px;
                right: 16px;

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
                background-color: black;
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
            .container {
                position: relative;
                color: white;

            }
            /* Centered text */
            .centered {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);

            }
            img{
                margin-left: auto;
                margin-right: auto;

            }
        </style>
        <script>
            function myFunction() {
                window.location.href = "money";
            }
        </script>
    </head>
    <body>
        <header>
            <h1>Welcome to DOM system</h1>
            <h3>Student</h3>
        </header>
        <div class="container">
            <ul>
                <li><a href="home">DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="home">Home</a></li>
            </ul>
            <img src="/AssignmentPRJ/img/ktx.jpg" style="width:100%;height: 400px"/>
            <section class = "left">
                <h2>FUNCTION</h2>
                <button><a href="placehistory">Place History</a></button><br>
                <button><a href="bookbed">Book a bed</a></button><br>
                <button><a href="domdetail">Dom detail</a></button><br>
                <button><a href="studentrequest">Request</a></button><br>
            </section>


            <section class="right">
                <div class="money">
                    <h2>Your Money</h2>
                    ${requestScope.student.money}
                </div>
                <button onclick="myFunction()">Recharge</button>
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
