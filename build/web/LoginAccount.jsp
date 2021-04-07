
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1.0"> <!-- корректное отображение на мобильных устройствах, отмена масштабирования -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Log In</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Custom css -->
    <link href="stylesheets/stylesheet.css" rel="stylesheet">
    <style type="text/css">
        /* line 5, ../../../../../../Library/Ruby/Gems/2.0.0/gems/compass-core-1.0.3/stylesheets/compass/reset/_utilities.scss */
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup,
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font: inherit;
  font-size: 100%;
  vertical-align: baseline;
}

/* line 22, ../../../../../../Library/Ruby/Gems/2.0.0/gems/compass-core-1.0.3/stylesheets/compass/reset/_utilities.scss */



/* line 47, ../sass/stylesheet.scss */
.container .login_section form h1, .container .signup_section form h1 {
  font-weight: bold;
  font-size: 22px;
  margin-bottom: 40px;
}


/* line 56, ../sass/stylesheet.scss */
.container .login_section form .btn-primary,
.container .login_section form .btn-outline-primary, .container .signup_section form .btn-primary,
.container .signup_section form .btn-outline-primary {
  -moz-border-radius: 20px;
  -webkit-border-radius: 20px;
  border-radius: 20px;
  margin-top: 15px;
  font-size: 12px;
}

/* line 64, ../sass/stylesheet.scss */
.container .login_section form .btn-primary, .container .signup_section form .btn-primary {
  background-color: #2cabee;
  border: 1px solid #2cabee;
  padding: 7px 40px;
}


/* line 78, ../sass/stylesheet.scss */
.container .login_section form .btn-outline-primary, .container .signup_section form .btn-outline-primary {
  border: 2px solid #2cabee;
  color: #2cabee;
  margin-left: 4px;
  padding: 7px 43px;
}

/* line 92, ../sass/stylesheet.scss */
.container .login_section form .btn-outline-primary:hover, .container .signup_section form .btn-outline-primary:hover {
  background-color: #2cabee;
  color: #ffffff;
  
}

/* line 98, ../sass/stylesheet.scss */
.container .login_section form .form-row .form-control, .container .signup_section form .form-row .form-control {
  width: 280px;
  font-size: 0.85rem;
  padding: 8px 20px;
  margin-top: 10px;
}


/* line 110, ../sass/stylesheet.scss */
.container .login_section form .form-check, .container .signup_section form .form-check {
  margin-top: 5px;
  line-height: 1.5;
}
/* line 116, ../sass/stylesheet.scss */
.container .login_section form .form-check .form-check-label, .container .signup_section form .form-check .form-check-label, .container .login_section form .form-check a, .container .signup_section form .form-check a {
  font-size: 11px;
}

/* line 122, ../sass/stylesheet.scss */
.container .login_section form .form-check .form-check-label, .container .signup_section form .form-check .form-check-label {
  color: #98a6a9;
  padding-left: 5px;
}

/* line 129, ../sass/stylesheet.scss */


/* line 135, ../sass/stylesheet.scss */
.was-validated-user #user-error img {
  width: 14px;
}

/* line 143, ../sass/stylesheet.scss */
.was-validated-password #pass-error img {
  width: 14px;
}

/* line 149, ../sass/stylesheet.scss */
body {
  background-color: #2cabee;
  font-family: Arial;
}

/* line 153, ../sass/stylesheet.scss */
.container {
  max-height: 620px;
  background-color: #ffffff;
  -moz-box-shadow: rgba(0, 0, 0, 0.4) 0 30px 100px;
  -webkit-box-shadow: rgba(0, 0, 0, 0.4) 0 30px 100px;
  box-shadow: rgba(0, 0, 0, 0.4) 0 30px 100px;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin-top: 60px;
}

/* line 174, ../sass/stylesheet.scss */
.container .bg_section_login,
.container .bg_section_signup {
  max-height: 620px;
  background-size: cover !important;
  position: relative;
}

/* line 192, ../sass/stylesheet.scss */
.container .bg_section_login img,
.container .bg_section_signup img {
  width: 220px;
  position: absolute;
  top: 8%;
  left: -10px;
}
/* line 199, ../sass/stylesheet.scss */
.container .m_bg_section_login {
  display: none;
}

/* line 213, ../sass/stylesheet.scss */
.container .m_bg_section_signup {
  display: none;
}

/* line 227, ../sass/stylesheet.scss */
.container .bg_section_login {
  background: url("bg_login_desk.png") no-repeat;
}
/* line 230, ../sass/stylesheet.scss */

/* line 234, ../sass/stylesheet.scss */
.container .login_section {
  padding-top: 200px;
  padding-bottom: 185px;
  padding-left: 108px;
}


/* line 280, ../sass/stylesheet.scss */

/* line 293, ../sass/stylesheet.scss */
.container .login_section form .form-check a {
  margin-left: 80px;
  color: #2cabee;
  font-weight: bold;
}

.check{
    width: 150px;
}
    </style>
  </head>

  <body>
    <div class="container-fluid">
      <div class="container">
        <div class="row">
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 bg_section_login">
            <img class="logo" src="logo_desk.png" alt=""/>
          </div>
          <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 login_section">
              <form class="needs-validation" novalidate action="login" method="post">
              <h1>Log in</h1>
              <p style="color: red"><c:out value="${errorLogin}" /></p>
              <p style="color: green"><c:out value="${messCreate}" /></p>
              <div class="form-row">
                  <input type="text" class="form-control" name="txtUserEmail" id="username" aria-describedby="emailHelp" placeholder="Username"  value="${userEmail}"required>
              </div>
              <div class="form-row">
                  <input type="password" class="form-control" name="txtUserPassword" id="password" value="${userPassword}" placeholder="Password" required>
              </div>
              <div class="form-check">
                <input type="checkbox" class="form-check-input" id="login_check">
                <label class="form-check-label" for="login_check">Stay signed in</label>
                <a href="#">Forgot password?</a>
              </div>
              <button type="submit" class="btn btn-primary">LOG IN</button>
              <a href="Registration.jsp"><input class="btn btn-outline-primary check" value="Sign Up"/></a>
            </form>              
          </div>
      </div>
    </div>
  </body>
</html>
