<%-- 
    Document   : Update
    Created on : Oct 5, 2021, 3:41:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Personal Information</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(() => {
                $('form').submit(e => {
                    //e.preventDefault();

                    const name = $('#name').val().trim()
                    const address = $('#address').val().trim()

                    const pass = $('#pwd').val().trim()
                    const dob = $('#dob').val().trim()
                    //alert(id.length + " " + name.length + " " + dob.length + " " + address.length + " " + username.length + " " + password.length));

                    if (name.length === 0) {
                        showError('Please enter name!')
                        e.preventDefault()
                    } else if (dob.length === 0) {
                        showError('Please enter dob!')
                        e.preventDefault()
                    } else if (address.length === 0) {
                        showError('Please enter address')
                        e.preventDefault()
                    } else if (pass.length === 0) {
                        showError('Please enter password')
                        e.preventDefault()
                    }
                })
            })
            function showError(message) {
                $('.errorMessage').html(message)
                $('.errorMessage').show()
            }

        </script>
        <style>
            p{
                display: none;
                color: red;
            }
        </style>
    </head>
    <body onload="loadData()">
        <div class="container">
            <form action="update" method="POST">
                ID: <input type="text" id="code" class="form-control" name="id" onkeyup="displayMessStudent(this.value)" value="${requestScope.student.id}" disabled><p id="idexist">ID exist</p><br>
                Name: <input type="text" class="form-control" name="name"  id="name" value="${requestScope.student.name}">
                Gender: <input type="radio" ${(requestScope.student.gender) ?"checked=\"checked\"":""} name="gender" value="male" >Male
                <input type="radio" ${(!requestScope.student.gender) ?"checked=\"checked\"":""} name="gender" value="female">Female<br>
                DOB: <input type="date" name="dob" value="${requestScope.student.dob}" class="form-control" id="dob"><br>
                Address: <input type="text" class="form-control" name="address" value="${requestScope.student.address}" id="address">
                Username: <input type="text" class="form-control" name="username"  id="urs"  value="${requestScope.student.username}" disabled>
                Password: <input type="password" class="form-control" name="password"  id="pwd">
                <div style="display:none; color: red" class="errorMessage my-3"></div>
                <input id="submit" type="submit" value="Update">

            </form>
        </div>
    </body>
</html>
