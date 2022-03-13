<%-- 
    Document   : Elen
    Created on : Mar 12, 2022, 10:03:23 AM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portf</title>
        <link href="css/Elen.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!--Import font awesome-->
        <script src="https://kit.fontawesome.com/563c930a8f.js" crossorigin="anonymous"></script>
    </head>
    <body>
        
        <header>
            <h2 id="text-header">Portfolio</h2>

            <ul id="nav">
                <li><a href="#home">Home</a></li>
                <li><a href="#skill">Skill</a></li>
                <li><a href="#project">Project</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <a id="back-homepage" href="../HomePage/home.jsp">
                <--- Back To Home Page</a>
        </header>

        <main>
            <!-- home -->
            <div id="home">
                <img src="../img/Elen/default.png" alt="">
                <h3>Hello, I'm</h3>
                <h1>Hoang Danh Tuan</h1>
                <h4>Male</h4>
            <h4>Ky thuat Phan Mem</h4>
                <p>I am a Developer in Java and FullStack</p>
            </div>

            <!-- skill -->
            <div id="skill">
                <h2>Skills In My Career</h2>
                <ul id="skill-list">
                    <li>Framework Spring Boot</li>
                    <li>Photoshop</li>
                    <li>Visual Studio Code</li>
                    <li>Adope Premiere Pro</li>
                </ul>
            </div>

            <!-- project -->
            <div id="project">
                <h2>Projects</h2>
                <ul id="project-list">
                    <li>Techmely</li>
                    <li>SomeThing I Wrote</li>
                    <li>Smart House</li>
                </ul>
            </div>

            <!-- contact -->
            <div id="contact">
                <h2>Contact Me</h2>
                <ul id="contact-list">
                    <li>
                        <i class="fa-solid fa-location-dot"></i>
                        <h3>Address:</h3>
                        <p>Bac Giang</p>
                    </li>
                    <li>
                        <i class="fa-solid fa-phone"></i>
                        <h3>Phone: </h3>
                        <p>037974837</p>
                    </li>
                    <li>
                        <i class="fa-solid fa-envelope"></i>
                        <h3>Email:</h3>
                        <p>tuandh@gmail.com</p>
                    </li>
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

    </body>
</html>
