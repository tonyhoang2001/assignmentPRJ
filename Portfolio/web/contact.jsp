<%-- 
    Document   : newjsp
    Created on : Mar 11, 2022, 8:45:21 AM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us</title>
        <link href="css/contact.css" rel="stylesheet" type="text/css"/>
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
                <li><a href="create.jsp">Create Portfolio</a></li>
                <li><a href="viewPortServlet">Your Portfolio</a></li>
                <li><a href="contact.jsp">Contact Us</a></li>
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
            <div id="contact">
                <h1>CONTACT US</h1>
                <ul id="sub-contact">
                    <li>
                        <h3>Address: </h3> Hoa Lac HighTech, Thach That, Ha Noi, Viet Nam
                    </li>
                    <li>
                        <h3>Phone: </h3> 0123456789
                    </li>
                    <li>
                        <h3>Email:</h3> portfolio.vn@gmai.com
                    </li>
                    <li>
                        <h3>Fanpage:</h3> Portfolio VN
                    </li>
                </ul>
            </div>

            <div id="why-need">
                <h1>Why you need Portfolio</h1>
                <ul id="sub-need">
                    <li>Modern and convenient way to show your information to employers</li>
                    <li>AI tool to create beautiful CV online</li>
                    <li>Be more active to find the job</li>
                </ul>
            </div>

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
