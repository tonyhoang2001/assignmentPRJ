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
        <link href="../css/Login/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <!--text in right-->
        <div id="login-header-left">
            <div id="text-des">
                <h1>Portfolio Website</h1>
                <i>"The website for your own information of working"</i>
            </div>

            <div id="image">  
                <img src="../img/login/avatar.jpg" alt="avatar image">
            </div>
        </div>

        <!--login div-->
        <div id="login-container">

            <form action="" class="login">
                <h1 style="color: blueviolet;">Log In</h1>
                <input id="name" type="text" placeholder="username" name="username" required><br>
                <input id="pass" type="password" placeholder="password" name="password" required><br>
                <input id="button" type="submit" value="Login">
            </form>

            <div class="signup">
                <h4>Don't have account?</h4>
                <a href="signup.jsp">Sign up here</a>
            </div>

        </div>

    </body>
</html>
