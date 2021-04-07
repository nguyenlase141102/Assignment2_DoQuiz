

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style type="text/css">
            .bg{
                background: url('quizRegis.jpg') no-repeat;
                width: 100%;
                height: 100vh;
                background-size: 100%;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid bg">
            <form action="registration" style="width: 500px; margin: auto " method="post">            
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Email Student</b></label>
                            <input type="text" name="emailUser" class="form-control" value="${email}" >                          
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Name Student</b></label>
                            <input type="text" name="nameUser" class="form-control" value="${name}" >
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Password</b></label>
                            <input type="password" name="password" class="form-control" value="${password}" >
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Role</b></label>
                            <input type="text" name="role" class="form-control" value="Student" readonly>
                        </div>
                    </div>

                </div>

                <div class="row">


                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Status</b></label>
                            <input type="text" name="status" class="form-control"value="New" readonly >
                        </div>
                    </div>                       
                </div>

                <p style="color: red"><b><c:out value="${errorRegis}" /></b></p>
                <input type="submit" class="btn btn-primary btn-block" value="Create Account" >
            </form>
        </div>
    </body>
</html>
