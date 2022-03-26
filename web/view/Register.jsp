<%-- 
    Document   : Register
    Created on : Sep 29, 2021, 1:55:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            var accounts = [];
            var students = [];
            function loadData() {
                $.ajax(
                        {url: "/AssignmentPRJ/check",
                            type: 'GET',
                            success: function (response) {
                               // alert(response);
                                var j = JSON.parse(response);
                                for (let i = 0; i < j.accounts.length; i++) {
                                    accounts[i] = j.accounts[i];
                                }
                                for (let i = 0; i < j.students.length; i++) {
                                    students[i] = j.students[i];
                                }
                            }
                        }
                );
            }

            function checkStudentID(studentID)
            {
                for (let i = 0; i < students.length; i++) {
                    if (studentID === students[i].id) {
                        return true;
                    }
                }
                return false;
            }

            function checkAccount(username) {
                for (let i = 0; i < accounts.length; i++) {
                    if (username === accounts[i].username) {
                        return true;
                    }
                }
                return false;
            }

            function displayMessStudent(studentID) {
                if (checkStudentID(studentID)) {
                    document.getElementById('idexist').style.display = 'block';
                    document.getElementById('submit').disabled = true;
                } else {
                    document.getElementById('idexist').style.display = 'none';
                    document.getElementById('submit').disabled = false;
                }
            }

            function displayMessAccount(username) {
                if (checkAccount(username)) {
                    document.getElementById('userexist').style.display = 'block';
                    document.getElementById('submit').disabled = true;
                } else {
                    document.getElementById('userexist').style.display = 'none';
                    document.getElementById('submit').disabled = false;
                }
            }

            $(() => {
                $('form').submit(e => {
                    //e.preventDefault();
                    const id = $('#code').val().trim()
                    const name = $('#name').val().trim();
                    const address = $('#address').val().trim();
                    const user = $('#urs').val().trim();
                    const pass = $('#pwd').val().trim();
                    const dob = $('#dob').val().trim();
                    //alert(id.length + " " + name.length + " " + dob.length + " " + address.length + " " + username.length + " " + password.length));
                    if (id.length === 0) {
                        showError('Please enter ID!');
                        e.preventDefault();
                    } else if (name.length === 0) {
                        showError('Please enter name!');
                        e.preventDefault();
                    } else if (dob.length === 0) {
                        showError('Please enter dob!');
                        e.preventDefault();
                    } else if (address.length === 0) {
                        showError('Please enter address');
                        e.preventDefault();
                    } else if (username.length === 0) {
                        showError('Please enter username!');
                        e.preventDefault();
                    } else if (password.length === 0) {
                        showError('Please enter password');
                        e.preventDefault();
                    }
                })
            })
            function showError(message) {
                $('.errorMessage').html(message);
                $('.errorMessage').show();
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
            <form action="register" method="POST">
                ID: <input type="text" id="code" class="form-control" name="id" onkeyup="displayMessStudent(this.value)"><p id="idexist">ID exist</p>
                Name: <input type="text" class="form-control" name="name"  id="name">
                Gender: <input type="radio"  name="gender" value="male" checked="checked">Male <input type="radio" name="gender" value="female">Female <br>
                DOB: <input type="date" class="form-control" name="dob" require id="dob">
                Address: <input type="text" class="form-control" name="address"  id="address">
                Username: <input type="text" class="form-control" name="username"  id="urs" onkeyup="displayMessAccount(this.value)"><p id="userexist">Username exist</p>
                Password: <input type="password" class="form-control" name="password"  id="pwd">
                <div style="display:none; color: red" class="errorMessage my-3"></div>
                <input id="submit" type="submit" value="Register">

            </form>
        </div>
    </body>
</html>
