

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <h1>Result Quiz</h1>
    <style type="text/css">
        *{
            padding: 0px;
            margin: 0px;
            box-sizing: border-box;
        }
        body{
            font-family: Montserrat;    
        }
        section{
            height: 100vh;
            width: 100%;
            background-color: aliceblue;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }
        .container{
            width: 90%;
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0px 0px 20px #00000010;
            background-color: white;
            border-radius: 8px;
            margin-bottom: 20px;


        }
        .form-group{
            width: 100%;
            margin-top: 20px;
            font-size: 20px;
        }
        .form-group input,
        .form-group textarea{
            width: 100%;
            padding: 5px;
            font-size: 18px;
            border : 1px solid rgba(128,128,128,0.199);
        }
        button[type ="submit"]{
            width: 100%;
            border: none;
            outline:  none;
            padding: 20px;
            font-size: 24px;
            border-radius: 8px;
            font-family: 'Montserrat';
            color: rgb(27,166,247);
            text-align: center;
            cursor: pointer;
            margin-top: 10px;
            transition: .3s ease background-color;
        }
        button[type="submit"]:hover{
            background-color: rgb(214,226,236);    
        }
        #status{
            width: 90%;
            max-width: 500px;
            text-align: center;
            padding: 10px;
            margin: 0 auto;
            border-radius: 8px;
        }
        #status.success{
            background-color: white;
            color: red;

        }
        #status.error{

            color: white;
            color: red;
        }
    </style>
</head>
<body>

    <section>
        <div class="container">
            <form action="studentHistory">      
                <div class="col">
                    <div class="form-group">
                        <label for="secondQuestion">User Name : </label>
                        <input type="text" name="userName" class="form-control" value="${userName}" readonly>
                        <input type="hidden" name="userName" value="${userName}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="content">Course Name</label>
                    <input type="text" name="course" id="content" value="${courseName}" readonly/>
                    <input type="hidden" name="course" value="${courseName}" />
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label>Your Correct</label>
                            <input type="text" class="form-control" value="${score}/${totalQuestion}"readonly >         
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label >Total Marks </label>
                            <input type="text" name="score" class="form-control" value="${score}" readonly>
                            <input type="hidden" name="score" value="${count}" />
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="secondQuestion">Student Date</label>
                        <input type="text" name="date" class="form-control" value="${currentDate}" readonly>
                        <input type="hidden" name="date" value="${currentDate}" />
                    </div>
                </div>
                <button type="submit">Home Page</button>
            </form>

        </div> 

    </section>
</body>
</html>
