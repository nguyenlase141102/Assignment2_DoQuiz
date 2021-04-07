

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style type="text/css">

            .box{
                background-color: #fff;
                border-radius:  10px;
                box-shadow: 0 0 50px 0 rgba(0,0,0,0.2);
                min-height: 350px;
                width: 640px;
                padding: 50px;
            }
            .title{
                border-bottom: 1px solid #464646;
                color : #464646;
                padding-bottom: 10px;
                margin-bottom: 20px;
                font-weight: 600;
                font-size: 24px;
            }
            .header{
                margin-bottom: 30px;
                display: flex;
                justify-content: space-between;
            }
            .timeBox{
                border-radius: 100px;
                padding: 10px 15px;
                border: 1px solid #444;
            }
            .questionBox{
                background-color: #594a76;
                color: #fff;
                border-radius: 10px;
                padding: 10px 15px;
            }
            .optionBox span{
                background-color: #ccc;
                border-radius: 10px;
                color : #444;
                border: 1px solid #444;
                padding: 10px 15px;
            }
            .optionBox{
                display: grid;
                grid-template-colums : 1fr 1fr;
                margin: 30px 0;
                grid-gap : 15px;
            }
            body{
                display: flex;
                justify-content: center;
                align-items: center;

            }
        </style>
    </head>
    <body>
        
        <c:choose >
            <c:when test="${choiceAction eq 'deleteQuestion'}">

                <div class="box">
                    <p style="color: green"><c:out value="${deleteMessage}" /></p>
                    <p style="color: red"><c:out value="${errorDelete}" /></p>
                    <a href="LoadHome">HomePage</a>
                    <div class="title">
                        Question of subject:<p style="color: greenyellow"  ><b><c:out value="${courseName}"/></b></p>
                    </div>

                    <c:forEach var="lqs" items="${listQuestionSubject}" >
                        <form action="checkDelete">
                            <c:set var="status" value="${fn:trim(lqs.status)}" />

                            <c:if test="${status eq 'active'}">
                                <div class="header">
                                    <button><a onclick="return confirm('Are you sure you want to delete this item?');">Delete Question</a></button>
                                </div>
                            </c:if>

                            <div class="questionBox"><c:out value="${lqs.questionContent}" /></div>
                            <c:forEach var="lca" items="${listContentAnswer}">

                                <c:if test="${lqs.idQuestion eq lca.idQuestion}" >
                                    <div class="optionBox">
                                        <span><c:out value="${lca.contentQuestion}"  /></span>
                                    </div>
                                    <input type="hidden" name="idHidden" value="${lqs.idQuestion}" />
                                    <input type="hidden" name="courseName" value="${courseName}"/>
                                </c:if>
                            </c:forEach>
                        </form>
                        <div class="optionBox">
                            Correct Answer:<span style="background-color: greenyellow"><c:out value="${lqs.answerCorrect}"/></span>
                        </div>
                    </c:forEach>
                </div>
            </c:when>


            <c:otherwise>
                <div class="box">
                    <a href="LoadHome">HomePage</a>
                    <p style="color: green"><c:out value="${updateMessage}" /></p>
                    <p style="color: red"><c:out value="${errorUpdate}" /></p>
                    <div class="title">
                        Question of subject:<p style="color: greenyellow"  ><b><c:out value="${courseName}"/></b></p>
                    </div>
                    <c:forEach var="lqs" items="${listQuestionSubject}" >
                        <form action="CheckEdit">
                            <div class="header">
                                <input type="submit" value="Update Question"/>
                            </div>
                            <div class="questionBox"><c:out value="${lqs.questionContent}" /></div>
                            <c:forEach var="lca" items="${listContentAnswer}">

                                <c:if test="${lqs.idQuestion eq lca.idQuestion}" >
                                    <div class="optionBox">
                                        <span><c:out value="${lca.contentQuestion}"  /></span>
                                    </div>
                                    <input type="hidden" name="idHidden" value="${fn:trim(lqs.idQuestion) }" />    
                                    <input type="hidden" name="contentQuestionHidden" value="${fn:trim(lqs.questionContent)  }" /> 
                                    <input type="hidden" name="correctQuestionHidden" value="${fn:trim(lqs.answerCorrect) }" /> 
                                    <input type="hidden" name="statusQuestionHidden" value="${fn:trim(lqs.status) }" />
                                    <input type="hidden" name="courseName" value="${courseName}"/>
                                </c:if>
                            </c:forEach>
                        </form>
                        <div class="optionBox">
                            Correct Answer:<span style="background-color: greenyellow"><c:out value="${lqs.answerCorrect}"/></span>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>



        </c:choose>
    </body>
</html>
