

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take quiz</title>
        <script type="text/javascript">
            var timer = ${timeQuiz};
            var min = 0;
            var sec = 0;
            function startTimer() {
                min = parseInt(timer / 60);
                sec = parseInt(timer % 60);
                if (timer < 1) {
                    window.location = "ResultQuiz";
                }
                document.getElementById("time").innerHTML = "Time Left: <b>" + min.toString() + ":" + sec.toString();
                timer--;
                var timeTest = timer;
                document.getElementById('myField').value = timeTest;

                setTimeout(function () {
                    startTimer();
                }, 1000);
            }
        </script>

    </head>
    <body onload="startTimer()">

        <div>
            <center><b><span id="time"></span></b></center>
        </div>
        Quiz Subject : ${courseName}
        <form action="${doQuiz}" name="myform">
            <c:forEach var="entry" items="${sessionScope.map}" varStatus="counter">
                <p style="color: blue">Question ${countPage + counter.count} : <c:out value="${entry.key}"/></p>
                <c:set var="lastCount" value="${counter.count}"/>

                <c:forEach var="values" items="${entry.value}"> 

                    <c:if test="${empty sessionScope.mapStudent}">
                        <input type="radio" name="${fn:trim(values.idQuestion) }" value="${fn:trim(values.contentQuestion)}" >
                        <label>${values.contentQuestion}</label> <br>
                    </c:if>

                    <c:if test="${not empty sessionScope.mapStudent}">
                        <c:forEach var="s" items="${sessionScope.mapStudent}">
                            <c:forEach var="m" items="${s.value}"> 


                                <c:if test="${m.idQuestion eq values.idQuestion && m.contentQuestion eq values.contentQuestion}">
                                    <c:choose>                                
                                        <c:when test="${m.status == true }">
                                            <input type="radio" name="${fn:trim(m.idQuestion) }" value="${fn:trim(m.contentQuestion)}" checked >
                                            <label>${m.contentQuestion}</label> <br>  
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="${fn:trim(m.idQuestion) }" value="${fn:trim(m.contentQuestion)}"  >
                                            <label>${m.contentQuestion}</label> <br> 
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                            
                            </c:forEach>
                        </c:forEach>    
                    </c:if>
                </c:forEach>
            </c:forEach>
            <c:if test="${indexPage == endPage}">
                <input type="submit" name="finish" value="Finish Quiz" />
            </c:if>

            <input type="submit" name="btPrevious" value="Previous"/>
            <c:forEach var="s" begin="1" end="${endPage}">
                <input type="submit" name="index"  value="${s}"/>
                <input type="hidden" name="indexTest" value="${s}"/>
                <input type="hidden" name="listNameSubject" value="${courseName}"/>
                <input type="hidden" name="lastCount" value="${lastCount}"/>
                <input type="hidden" name="nameUser" value="${name}"/>
                <input type="hidden" name="action" value="on"/>
                <input type="hidden" name="random" value="on"/>
            </c:forEach> 
               
            <input type="submit" name="btNext" value="Next"/>
            <input type="hidden" name="index2" value="${indexPage}"/>
            
            <input type="hidden" name="myField" value="" id="myField"/>  
        </form>





    </body>
</html>
