<%-- 
    Document   : patrix
    Created on : Mar 12, 2022, 4:22:42 PM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portfolio</title>
        <link href="css/patrix.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!--Import font awesome-->
        <script src="https://kit.fontawesome.com/563c930a8f.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <header>
            <h2>Portfolio</h2>
            <ul id="nav">
                <li><a href="#about">Home</a></li>
                <li><a href="#skill">Skill</a></li>
                <li><a href="#project">Project</a></li>
                <li><a href="#contact">Contact</a></li>
                <li><a href="viewPortServlet">Back to Your List</a></li>
            </ul>
        </header>

        <main>

            <div id="about">
                <div class="aleft">
                    <h2>Hey, I'm</h2>
                    <h2>${name}</h2>
                    <h4>${gender}</h4>
                    <h4>${field}</h4>
                    <p>${des}</p>
                </div>

                <div class="aright">
                    <img src="img/default.png" alt="">
                </div>

            </div>

            <div id="skill">
                <h2>About Me</h2>
                <div class="scontainer">
                    <div class="sleft">
                        <i class="fa-solid fa-book"></i>
                        <h2>Skill</h2>
                        <ul id="skill-list">
                            <li>${skill1}</li>
                            <li>${skill2}</li>
                            <li>${skill3}</li>
                        </ul>
                    </div>

                    <div class="sright">
                        <img src="img/skill.png" alt="skill">
                    </div>
                </div>
            </div>

            <div id="project">
                <div class="pleft">
                    <img src="img/project.jpg" alt="">
                </div>

                <div class="pright">
                    <i class="fa-solid fa-briefcase"></i>
                    <h2>Project</h2>
                    <ul id="project-list">
                        <li>${project1}</li>
                        <li>${project2}</li>
                        <li>${project3}</li>
                    </ul>
                </div>

            </div>

            <div id="contact">
                <div class="cleft">
                    <i class="fa-solid fa-address-book"></i>
                    <h2>Contact</h2>
                    <ul id="contact-list">
                        <li>
                            <i class="fa-solid fa-location-dot"></i>
                            <h3>Address: </h3>
                            <p>${address}</p>
                        </li>

                        <li>
                            <i class="fa-solid fa-phone"></i>
                            <h3>Phone: </h3>
                            <p>${phone}</p>
                        </li>

                        <li>
                            <i class="fa-solid fa-envelope"></i>
                            <h3>Email: </h3>
                            <p>${email}</p>
                        </li>

                    </ul>
                </div>

                <div class="cright">
                    <img src="img/contact.png" alt="contact">
                </div>
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
