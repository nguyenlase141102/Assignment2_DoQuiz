

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

            <form action="updateQuestion" style="width: 500px; margin: auto " method="post"> 
                <div class="form-group">
                    <label>Course Name</label>
                    <select name="listNameSubject">
                        <c:forEach var="subject" items="${listSubject}">
                            <option><c:out value="${subject.subjectName}" /></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Question Content</b></label>
                            <input type="text" name="txtContentUpdate" class="form-control" value="${contentQuestionUpdate}" >
                            <input type="hidden" name="idUpdate" class="form-control" value="${idUpdate}" >   
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Option 1</b></label>
                            <input type="text" name="option1" class="form-control" value="${option1}" >
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Option 2</b></label>
                            <input type="text" name="option2" class="form-control" value="${option2}" >
                        </div>
                    </div>    
                </div>  
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Option 3</b></label>
                            <input type="text" name="option3" class="form-control" value="${option3}" >
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Option 4</b></label>
                            <input type="text" name="option4" class="form-control" value="${option4}" >
                        </div>
                    </div>    
                </div>  

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Answer Correct</b></label>
                            <input type="text" name="txtCorrectUpdate" class="form-control"value="${correctQuestionUpdate}" >
                        </div>
                    </div>                       
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Status</b></label>
                            <select name="status">
                                <c:choose >
                                    <c:when test="${statusQuestionUpdate eq 'active'}">
                                        <option>${statusQuestionUpdate }</option>
                                        <option>deactive</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${statusQuestionUpdate }</option>
                                        <option>active</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>   

                </div>  
                <input type="submit" class="btn btn-primary btn-block" value="Update Question" >
                <input type="hidden" name="courseName" value="${courseName}"/>
                <p style="color: red"><c:out value="${errorQuestionUpdate}" /></p>
            </form>           
        </div>
    </body>
</html>
