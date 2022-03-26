<%-- 
    Document   : Login
    Created on : Sep 28, 2021, 11:30:14 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <title>DOM</title>
        <style>
            /* Container holding the image and the text */
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
            .bottom-left {
                position: absolute;
                bottom: 8px;
                left: 50px;
                color: black; 
            }
            select{
                color: black;
            }
        </style>
        <script>
           $(() => {
               $('form').submit(e => {
                   //e.preventDefault();
                   const user = $('#urs').val().trim()
                   const pass = $('#pwd').val().trim()
                   if(user.length === 0){
                       showError('PLease enter username!')
                       e.preventDefault()
                   }
                   else if(pass.length === 0){
                       showError('Please enter password!')
                       e.preventDefault()
                   }
               })
           })
           function showError(message){
               $('.errorMessage').html(message)
               $('.errorMessage').show()
           }
        </script>

    </head>
    <body>
        <div class="container">
            <img src="/AssignmentPRJ/img/img2.jpg" style="width:100%;height: 700px"/>
            <div class="centered">   
                <h2>Login to your account</h2>
                <form id="myform" action="login" method="POST">
                    <div class="form-group">
                        <label for="urs"></label>
                        <input type="text" class="form-control" id="urs" name="username" placeholder="Your Username">
                    </div>
                    <div class="form-group">
                        <label for="pwd"></label>
                        <input type="password" class="form-control" id="pwd" name="password" placeholder="Your Password">
                    </div>
                    <input type="checkbox" id="remember" name="remember" value="1">
                    <label for="remember">Remember me</label><br>
                    <select name="gid">
                        <option value="-1" selected="selected">Select Role</option>
                        <c:forEach items="${requestScope.groups}" var="group"> 
                            <option value="${group.gid}">${group.gname}</option>
                        </c:forEach>
                    </select><br>
                    <div style="display:none; color: red" class="errorMessage my-3"></div>
                    <input type="submit" value="LOGIN" style="color: black"><br>
                </form><br>
                <h4 style="color:black">To register new account --> <a href="register" style="color: black">New User</a></h4>
            </div>
            <footer>
                <div class="bottom-left">
                    <p>Facebook: Trần Công Thành</p>
                    <p>Gmail: ThanhTCHE150499@fpt.edu.vn</p>
                    <p>Phone: 0376728801</p>
                </div>
            </footer>

        </div>
    </body>
</html>
