
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
            .pagination{
                display: inline-block;
            }
            .pagination a{
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                border: 1px solid;
            }
            .pagination a:hover{
                background-color: #ddd;
            }
            .pagination a:first-child {
                border-top-left-radius: 5px;
                border-bottom-left-radius: 5px;                
            }
            .pagination a:first-child {
                border-top-left-radius: 5px;
                border-bottom-left-radius: 5px;                
            }
        </style>
    </head>
    <body>
        <div class="container ">
            <div class="header">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="LoadHome"><i class="fa fa-home"></i> Home <span class="sr-only">(current)</span></a>
                            </li>
                            <c:set var="role" value="${userRole}"/>

                            <li class="nav-item">
                                <a class="nav-link" href="#"> <i class="fa fa-user"></i> ${nameUser} </a>  
                            </li>

                            <c:choose>
                                <c:when test="${role eq 'admin' }">
                                    <li class="nav-item">
                                        <a class="nav-link" href="AddQuestion"><i style="font-size:20px" class="fa">&#xf067;</i> Create Question</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="logout"><i class="fa fa-sign-out"></i> Log out</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="nav-item">
                                        <a class="nav-link" href="GetUserHistory"><i class="fa fa-history"></i>User History</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="logout"><i class="fa fa-sign-out"></i> Log out</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>   
                        </ul>
                        <c:if test="${role eq 'admin'}">
                            <form class="form-inline my-2 my-lg-0" action="searchQuestion">
                                <select name="statusQuestion" >
                                    <option>active</option>
                                    <option>deactive</option>
                                </select>
                                &nbsp;                        
                                <div>
                                    <select name="listNameSubject">
                                        <option>None</option>   
                                        <c:forEach var="s" items="${listSubject}">                                
                                            <option><c:out value="${s.subjectName}" /></option>                              
                                        </c:forEach>
                                    </select>
                                </div>
                                <br>
                                &nbsp;
                                <input class="form-control mr-sm-2" type="search" placeholder="Question name" aria-label="Search" name="txtSearch">
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="questionName" value="search"/>
                            </form>
                        </c:if>
                    </div>
            </div> 
            <div class="body">
                <p style="color: green"><c:out value="Welcome to : ${nameUser}" /></p>
                <div id="status" style="color: green"> <c:out value="${successCreate}"/></div>
                <br>
                <p style="color: red">${doQuizAlready}</p>
                <c:choose>
                    <c:when test="${role eq 'admin'}">
                        <form action="questionSubject">              
                            <div class="form-group">
                                <label for="id">Course Name</label>
                                <select name="listNameSubject">
                                    <option>None</option>  
                                    <c:forEach var="s" items="${listSubject}">                                
                                        <option><c:out value="${s.subjectName}" /></option>                              
                                    </c:forEach>
                                </select>
                                <input type="submit" name="btAction" value="Delete Question"/>
                                <input type="submit" name="btAction" value="Update Question"/>
                            </div>
                        </form>
                    </c:when>

                    
                    
                    <c:otherwise>
                        <form action="checkDoQuiz">              
                            <div class="form-group">
                                <label for="id">Course Name</label>
                                <select name="listNameSubject">
                                    <c:forEach var="s" items="${listSubject}">                                
                                        <option><c:out value="${s.subjectName}" /></option>                              
                                    </c:forEach>
                                </select>
                                <input type="submit" value="Take Quiz"/>
                                <input type="hidden" value="${nameUser}" name="nameUser"/>
                            </div>
                        </form> 
                    </c:otherwise>
                </c:choose>

                <c:if test="${not empty clickSearch}">
                    <div class="box">
                        <div class="title">
                            Result search 
                        </div>
                        <c:forEach var="entry" items="${sessionScope.map}" varStatus="counter" >

                            <div class="questionBox">Question ${counter.count}: <c:out value="${entry.key.questionContent}" /></div>
                            <c:forEach var="value" items="${entry.value}">
                                <div class="optionBox">
                                    <span><c:out value="${value.contentQuestion}"/></span>
                                </div>

                            </c:forEach>

                            <div class="optionBox">
                                Correct Answer:<span style="background-color: greenyellow"><c:out value="${entry.key.answerCorrect}"/></span>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="pagination">
                        <form action="searchQuestion">
                            <c:forEach var="i" begin="1" end="${endPage}">
                                <input type="submit" name="index"  value="${i}"/>
                            </c:forEach>
                            <input type="hidden" name="statusQuestion" value="${statusQuestion}"/>
                            <input type="hidden" name="listNameSubject" value="${nameSubject}"/>
                            <input type="hidden" name="txtSearch" value="${questionSearch}"/>
                        </form>
                    </div>
                </c:if>
            </div>

            <!-- footer -->
            <div class="footer">
                <!-- Footer -->
                <footer class="page-footer font-small mdb-color lighten-3 pt-4">

                    <!-- Footer Links -->
                    <div class="container text-center text-md-left">

                        <!-- Grid row -->
                        <div class="row">

                            <!-- Grid column -->
                            <div class="col-md-4 col-lg-3 mr-auto my-md-4 my-0 mt-4 mb-1">

                                <!-- Content -->
                                <h5 class="font-weight-bold text-uppercase mb-4">More Information</h5>
                                <p><i class="fa fa-github-alt"></i> HN Company.</p>
                                <p>HN is a brand used by Asus since 2006, encompassing a range of computer hardware, personal computers, peripherals, and accessories oriented primarily toward PC gaming.</p>

                            </div>
                            <!-- Grid column -->

                            <hr class="clearfix w-100 d-md-none">

                            <!-- Grid column -->
                            <div class="col-md-2 col-lg-2 mx-auto my-md-4 my-0 mt-4 mb-1">

                                <!-- Links -->
                                <h5 class="font-weight-bold text-uppercase mb-4">About</h5>

                                <ul class="list-unstyled">
                                    <li>
                                        <p>
                                            <a href="#!">PROJECTS</a>
                                        </p>
                                    </li>
                                    <li>
                                        <p>
                                            <a href="#!">ABOUT US</a>
                                        </p>
                                    </li>
                                    <li>
                                        <p>
                                            <a href="#!">BLOG</a>
                                        </p>
                                    </li>
                                    <li>
                                        <p>
                                            <a href="#!">AWARDS</a>
                                        </p>
                                    </li>
                                </ul>

                            </div>
                            <!-- Grid column -->

                            <hr class="clearfix w-100 d-md-none">

                            <!-- Grid column -->
                            <div class="col-md-4 col-lg-3 mx-auto my-md-4 my-0 mt-4 mb-1">

                                <!-- Contact details -->
                                <h5 class="font-weight-bold text-uppercase mb-4">Address</h5>

                                <ul class="list-unstyled">
                                    <li>
                                        <p>
                                            <i class="fa fa-home"></i> Sai gon,Ho chi minh,2020-VN</p>
                                    </li>
                                    <li>
                                        <p>
                                            <i class="fa fa-envelope-o"></i>   info@example.com</p>
                                    </li>
                                    <li>
                                        <p>
                                            <i class="fa fa-phone-square"></i> + 01 234 567 88</p>
                                    </li>
                                    <li>
                                        <p>
                                            <i class="fa fa-phone-square"></i> + 01 234 567 89</p>
                                    </li>
                                </ul>

                            </div>
                            <!-- Grid column -->

                            <hr class="clearfix w-100 d-md-none">

                            <!-- Grid column -->
                            <div class="col-md-2 col-lg-2 text-center mx-auto my-4">

                                <!-- Social buttons -->
                                <h5 class="font-weight-bold text-uppercase mb-4">Follow Us</h5>

                                <!-- Facebook -->
                                <a type="button" class="btn-floating btn-fb">
                                    <i class="fa fa-facebook-official"></i>
                                </a>
                                <!-- Twitter -->
                                <a type="button" class="btn-floating btn-tw">
                                    <i class="fa fa-twitter"></i>
                                </a>
                                <!-- Google +-->
                                <a type="button" class="btn-floating btn-gplus">
                                    <i class="fa fa-google"></i>
                                </a>
                                <!-- Dribbble -->
                                <a type="button" class="btn-floating btn-dribbble">
                                    <i class="fa fa-dribbble"></i>
                                </a>

                            </div>
                            <!-- Grid column -->

                        </div>
                        <!-- Grid row -->

                    </div>
                    <!-- Footer Links -->

                    <!-- Copyright -->

                </footer>
                <!-- Footer -->
            </div>  

        </div>
    </body>
</html>
