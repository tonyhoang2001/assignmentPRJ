<%-- 
    Document   : ElenTemplate
    Created on : Mar 12, 2022, 11:32:36 AM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template Elen</title>
        <link href="css/elenTemplate.css" rel="stylesheet" type="text/css"/>
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
            <form id="form" method="POST">
                <!-- home -->
                <div id="home">
                    <img src="../img/Elen/default.png" alt="">
                    <h3>Hello, I'm</h3>
                    <input id="inputName" type="text" name="name" placeholder="Your Name" required> *<br><br>

                    <p id="gender-label">Gender:</p>
                    <select name="gender" id="gender" required>
                        <option value="0">Female</option>
                        <option value="1">Male</option>
                    </select> *<br><br>

                    <input id="field" type="text" name="field" placeholder="Your Field (Job)" required> * 
                    <br>
                    <br>
                    <input id="inputDes" type="text" name="des" placeholder="Describe about yourself...">
                </div>

                <!-- skill -->
                <div id="skill">
                    <h2>Skills In Your Career (Optional)</h2>
                    <ul id="skill-list">
                        <li><input type="text" name="skill1" placeholder="Skill 1"></li>
                        <li><input type="text" name="skill2" placeholder="Skill 2"></li>
                        <li><input type="text" name="skill3" placeholder="Skill 3"></li>
                        <li><input type="text" name="skill4" placeholder="Skill 4"></li>
                    </ul>
                </div>

                <!-- project -->
                <div id="project">
                    <h2>Projects (Optional)</h2>
                    <ul id="project-list">
                        <li><input type="text" name="project1" placeholder="Project 1"></li>
                        <li><input type="text" name="project2" placeholder="Project 2"></li>
                        <li><input type="text" name="project3" placeholder="Project 3"></li>
                    </ul>
                </div>

                <!-- contact -->
                <div id="contact">
                    <h2>Your Infor To Contact</h2>
                    <ul id="contact-list">
                        <li>
                            <i class="fa-solid fa-location-dot"></i>
                            <h3>Address:</h3>
                            <input type="text" name="address" placeholder="Address">
                        </li>
                        <li>
                            <i class="fa-solid fa-phone"></i>
                            <h3>Phone: </h3>
                            <input type="number" name="phone" placeholder="Phonenumber" required> *
                        </li>
                        <li>
                            <i class="fa-solid fa-envelope"></i>
                            <h3>Email:</h3>
                            <input type="email" name="email" placeholder="Email" required> *
                        </li>
                    </ul>
                </div>
                <input id="name-portf" type="text" name="namePortf" placeholder="Name your Portfolio" required=""> * <br><br>
                <!--<input id="btn-form" type="submit" value="Create">-->
                
                <input id="save-form" type="submit" value="Save">
                <input id="undo-form" type="reset" value="Undo">
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

    </body>
</html>
