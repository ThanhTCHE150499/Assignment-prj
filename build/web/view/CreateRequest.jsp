<%-- 
    Document   : CreateRequest
    Created on : Oct 10, 2021, 7:57:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Request</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <form action="../createrequest" method="POST">
                Title <input type="text" class="form-control" name="title"><br>
                Note <input type="text" class="form-control" name="note"><br>
                <input type="submit" value="Create Request">
            </form><br>
            <a href="../studentrequest">Back to request list</a>
        </div>
    </body>
</html>
