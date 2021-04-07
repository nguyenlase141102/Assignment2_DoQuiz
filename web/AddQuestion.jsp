

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                <a href="LoadHome">Home Page</a>
                <form action="createQuestion">              
                    <div class="form-group">
                        <label>Course Name</label>
                        <select name="listNameSubject">
                            <c:forEach var="subject" items="${listSubject}">
                                <option><c:out value="${subject.subjectName}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Question Content</label>
                        <input type="text" name="txtContent" id="content" value="${content}" required="true"/>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label>Option 1:</label>
                                <input type="text" name="option1" class="form-control" value="${option1}" required="true" >                          
                            </div>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label>Option 2:</label>
                                <input type="text" name="option2" class="form-control" value="${option2}"  required="true">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label>Option 3:</label>
                                <input type="text" name="option3" class="form-control" value="${option3}" required="true">                          
                            </div>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label>Option 4:</label>
                                <input type="text" name="option4" class="form-control" value="${option4}" required="true" >
                            </div>
                        </div>
                    </div>      

                    <div class="row">
                         <div class="col">
                            <div class="form-group">
                                <label>Answer correct</label>
                                <input type="text" name="txtAnswerCorrect" id="answer_correct" value="${correct}" required="true"/>                       
                            </div>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label>Id Question</label>
                                <input type="text" name="idQuestion" class="form-control" value="${idQuestion}"  required="true">
                            </div>
                        </div>
                    </div> 

                    <button type="submit">Create Question</button>
                    <div id="status" class="error"> <c:out  value=" ${errorCreate}"/></div>
                    <p style="color: green"><c:out value="${successCreate}"/></p>
                </form>
            </div> 
        </section>
    </body>
</html>
