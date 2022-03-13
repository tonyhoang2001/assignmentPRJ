<%-- 
    Document   : home
    Created on : Mar 11, 2022, 3:53:09 PM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/HomePage/home.css" rel="stylesheet" type="text/css"/>
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
                <li><a href="home.jsp">Home</a></li>
                <li><a href="create.jsp">Create Portfolio</a></li>
                <li><a href="">Your Portfolio</a></li>
                <li><a href="contact.jsp">Contact Us</a></li>
            </ul>
            
            <ul class="acc">
                <li><img src="img/homepage/avatar.jpg" alt="avatar"></li>
                <li id="acc-name">
                    <h4 class="btn" id="user-name" href="">tuanhd</h4>
                    <i id="down" class="fa-solid fa-caret-down"></i>
                    <i id="right" class="fa-solid fa-caret-right"></i>
                    <a id="logout" href="login.jsp">Log out</a>
                </li>
            </ul>
        </header>

        <main>
            <!-- Search form -->
            <div class="search">

                <form id="search-form" action="">
                    <div class="search-job">
                        <h3 class="search-field">Job:</h3>
                        <!-- search by job -->
                        <select name="job" id="sjob-list">
                            <option value="0">All</option>
                            <option value="1">KTPM</option>
                            <option value="2">Marketing</option>
                        </select>
                    </div>

                    <div class="search-sex">
                        <h3 class="search-field">Sex:</h3>
                        <!-- search by sex -->
                        <input type="radio" value="all" name="sex"> All
                        <input type="radio" value="male" name="sex"> Male
                        <input type="radio" value="female" name="sex"> Female
                    </div>

                    <button class="search-btn" type="submit">Search</button>
                </form>

            </div>

            <!-- Show portfolio by paging -->
            <div class="paging">
                <h2><i class="fa-solid fa-star paging-star"></i> People</h2>

                <!-- CRUD ADMIN -->
                <div class="crud">
                    <i class="fa-solid fa-gear"></i>
                    <a href="crudAdmin.jsp">Edit-Delete All</a>
                </div>

                <div class="paging-container">
                    <div class="paging-two">
                        <div class="paging-portfolio row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>
                        </div>

                        <div class="paging-portfolio row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>
                        </div>

                    </div>


                    <div class="paging-two">
                        <div class="paging-portfolio  row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>
                        </div>

                        <div class="paging-portfolio row"  title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="page-index">
                    <a href="">1</a>
                    <a href="">2</a>
                    <a href="">3</a>
                    <a href="">4</a>
                </div>

            </div>

            <!-- Text description -->
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
        
        <script>
        const seePortfs = document.querySelectorAll('.paging-portfolio')
        for (see of seePortfs) {
            see.addEventListener("click", function () {
                window.open("create.jsp")
            })
        }
    </script>

        <script src="js/HomePage/homepage.js" type="text/javascript"></script>

    </body>
</html>
