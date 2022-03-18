<%-- 
    Document   : Account
    Created on : Mar 15, 2022, 10:30:35 AM
    Author     : LTC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link href="css/account.css" rel="stylesheet" type="text/css"/>
        <!-- import boostrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!--Import font awesome-->
        <script src="https://kit.fontawesome.com/563c930a8f.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <header>
            <ul class="nav">
                <li>
                    <h1>Portfolio Website</h1>
                </li>
                <li><a href="HomeServlet">Home</a></li>
                <li><a href="CreateIn">Create Portfolio</a></li>
                <li><a href="viewPortServlet">Your Portfolio</a></li>
                <li><a href="Contact">Contact Us</a></li>
                    <c:if test="${account.isIsAdmin()==true}">
                    <li><a href="AccountServlet">Accounts</a></li>
                    </c:if>
                    <li><a href="ChangePass">Change password</a> </li>
            </ul>

            <ul class="acc">
                <li><img src="img/avatar.png" alt="avatar"></li>
                <li id="acc-name">
                    <h4 class="btn" id="user-name">${account.getUsername()}</h4>
                    <i id="down" class="fa-solid fa-caret-down"></i>
                    <i id="right" class="fa-solid fa-caret-right"></i>
                    <a id="logout" href="login.jsp">Log out</a>
                </li>
            </ul>
        </header>

        <main>

            <p id="error">${error}</p>
            <p id="successU">${successU}</p>

            <form action="ChangePass" method="POST">
                <table id="cTable" border="1">
                    <tr>
                        <td> Username:</td>
                        <td><input type="text" name="username" placeholder="username" value="${account.getUsername()}" readonly=""></td>
                    </tr>

                    <tr>
                        <td>Password: </td>
                        <td><input type="text" name="password" placeholder="password" value="${account.getPassword()}"></td>
                    </tr>
                    
                    <tr>
                        <td>New Password: </td>
                        <td><input type="password" name="newpassword" placeholder="New password" value="${newPass}"></td>
                    </tr>
                    
                    <tr>
                        <td>Re-Enter New Password: </td>
                        <td><input type="password" name="renewpassword" placeholder="Re-enter new password" value="${reNewPass}"></td>
                    </tr>
                    
                    <tr class="crud">
                        <td></td>
                        <td><input type="submit"  value="Change password"></td>
                    </tr>
                </table>
            </form>

        </main>

        <footer>
            <div class="footer">
                <div class="f-left">
                    <h2>PORTFOLIO WEBSITE</h2>
                    <i>"Modern, convenient and good sight"</i>
                </div>

                <div class="f-right">
                    <div id="icon">
                        <i class="fa-brands fa-facebook"></i>
                        <i class="fa-brands fa-instagram-square"></i>
                        <i class="fa-brands fa-youtube"></i>
                        <i class="fa-brands fa-tiktok"></i>
                    </div>
                    <p>Hoa Lac High Tech, Ha Noi, Viet Nam</p>

                </div>
            </div>
        </footer>

        <script src="js/homepage.js" type="text/javascript"></script>

    </body>
</html>
