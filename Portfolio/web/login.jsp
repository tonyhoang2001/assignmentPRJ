<%-- 
    Document   : login
    Created on : Mar 7, 2022, 9:45:49 AM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Portfolio</title>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>

        <!--text in right-->
        <div id="login-header-left">
            <div id="text-des">
                <h1>Portfolio Website</h1>
                <i>"Create your Portfolio"</i>
            </div>

            <div id="image">  
                <img src="img/avatar.jpg" alt="avatar"/>
            </div>
        </div>

        <!--login div-->
        <div id="login-container">

            <form action="LoginServlet" method="POST" class="login">
                <h1 style="color: blueviolet;font-weight: bold">Log In</h1>
                <input id="name" type="text" placeholder="username" name="username" required><br>
                <input id="pass" type="password" placeholder="password" name="password" required><br>
                <input type="checkbox" name="remember"> Remember me
                <input id="button" type="submit" value="Login">

            </form>

            <div class="signup">
                <h4>Don't have account?</h4>
                <a href="signup.jsp">Sign up here</a>
            </div>
            <p style="color: red;margin-top:15px ">${message}</p>
        </div>

    </body>
</html>
