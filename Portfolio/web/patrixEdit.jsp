<%-- 
    Document   : patrixTemplate
    Created on : Mar 12, 2022, 4:22:54 PM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template Patrix</title>
        <link href="css/patrixTemplate.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!--Import font awesome-->
        <script src="https://kit.fontawesome.com/563c930a8f.js" crossorigin="anonymous"></script>
    </head>
    <body>
        
        <%
            String message = (String) session.getAttribute("messsage");
            if (message != null) {
        %>
        <script>
            window.alert(message);
        </script>
        <% } %>

        <header>
            <h2>Portfolio</h2>
            <ul id="nav">
                <li><a href="#about">Home</a></li>
                <li><a href="#skill">Skill</a></li>
                <li><a href="#project">Project</a></li>
                <li><a href="#contact">Contact</a></li>
                <li><a href="HomeServlet">Back to Home Page</a></li>
            </ul>
        </header>

        <% 
            String gender = (String) session.getAttribute("gender");
        %>

        <main>
            <form method="post" action="UpdateCrudServlet?idPD=${idPD}">
                <div id="about">
                    <div class="aleft">
                        <h2>Hey, I'm</h2>
                        <input id="inputName" type="text" value="${name}" name="name" placeholder="Your Name" required> *
                        <br>
                        <p id="gender-label">Gender:</p>
                        <select name="gender" id="gender" required>
                            <option value="0" <% if(gender.equals("Female") ){ %> selected="" <% }; %> >
                                Female</option>
                            <option value="1" <% if(gender.equals("Male") ){ %> selected="" <% }; %> >
                                Male</option>
                        </select>
                        <br>
                        <input id="field" type="text" value="${field}" name="field" placeholder="Your Field (Job)" required> *
                        <br>
                        <input id="inputDes" type="text" value="${des}" name="des" placeholder="Describe about yourself...">
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
                                <li><input type="text" value="${skill1}" name="skill1" placeholder="Skill 1"></li>
                                <li><input type="text" value="${skill2}" name="skill2" placeholder="Skill 2"></li>
                                <li><input type="text" value="${skill3}" name="skill3" placeholder="Skill 3"></li>
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
                            <li><input type="text" value="${project1}" name="project1" placeholder="Project 1"></li>
                            <li><input type="text" value="${project2}" name="project2" placeholder="Project 2"></li>
                            <li><input type="text" value="${project3}" name="project3" placeholder="Project 3"></li>
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
                                <input type="text" value="${address}" name="address" placeholder="Address">
                            </li>

                            <li>
                                <i class="fa-solid fa-phone"></i>
                                <h3>Phone: </h3>
                                <input type="number" value="${phone}" name="phone" placeholder="Phonenumber" required> *
                            </li>

                            <li>
                                <i class="fa-solid fa-envelope"></i>
                                <h3>Email: </h3>
                                <input type="email" value="${email}" name="email" placeholder="Email" required> *
                            </li>

                        </ul>
                    </div>

                    <div class="cright">
                        <img src="img/contact.png" alt="contact">
                    </div>
                </div>

                <input id="name-portf" type="text" value="${namePortf}" name="namePortf" placeholder="Name your Portfolio" required=""> *
                <br><br>
                
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
