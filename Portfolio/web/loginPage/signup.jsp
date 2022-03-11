<%-- 
    Document   : signup
    Created on : Mar 7, 2022, 9:45:58 AM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <title>Sign Up Portfolio</title>
        <link href="../css/Login/signUp.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="signup">
            <form id="form-signup" action="">
                <h1>Sign Up</h1>
                <input id="name" type="text" placeholder="Enter User Name"  name="username" required><br>
                <input id="pass" type="password" placeholder="Enter Password" name="pass" required><br>
                <input id="re-pass" type="password" placeholder="Re-Enter Password" name="re-pass" required><br>

                <h3 id="sex">Sex: </h3>
                <label for="sex">Male</label>
                <input type="radio" name="sex" value="male">
                <label for="sex" id="female">Female</label>
                <input type="radio" name="sex" value="female"><br>

                <h3 id="dob">Date Of Birth: </h3>  <input type="date" name="dob"><br> 

                <input id="button" type="submit" value="Sign Up">
            </form>

            <div id="back-login">
                <h3>Already have account!</h3>
                <a href="login.jsp">Back To Login</a>
            </div>
        </div>

    </body>
</html>
