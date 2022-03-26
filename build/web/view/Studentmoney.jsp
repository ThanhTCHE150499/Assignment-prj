<%-- 
    Document   : Studentmoney
    Created on : Oct 18, 2021, 8:04:29 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <form action="money" method="POST">
            <h1>Amount you want to deposit?</h1>
            <input class="form-control" type="text" name="money" placeholder="Enter your money you want">
            <input type="submit" value="Save">
        </form>
    </body>
</html>
