<%-- 
    Document   : crudAdmin
    Created on : Mar 11, 2022, 6:02:14 PM
    Author     : LTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD ADMIN</title>
        <link href="css/CRUD/crudAdmin.css" rel="stylesheet" type="text/css"/>
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
                <li><a href="../HomePage/home.jsp">Home</a></li>
                <li><a href="../HomePage/create.jsp">Create Portfolio</a></li>
                <li><a href="">Your Portfolio</a></li>
                <li><a href="../HomePage/contact.jsp">Contact Us</a></li>
            </ul>

            <ul class="acc">
                <li><img src="../img/homepage/avatar.jpg" alt="avatar"></li>
                <li id="acc-name">
                    <h4 class="btn" id="user-name" href="">tuanhd</h4>
                    <i id="down" class="fa-solid fa-caret-down"></i>
                    <i id="right" class="fa-solid fa-caret-right"></i>
                    <a id="logout" href="../loginPage/login.jsp">Log out</a>
                </li>
            </ul>
        </header>

        <main>
            <!-- Show portfolio by paging -->
            <div class="paging">

                <div class="paging-container">
                    <div class="paging-two">
                        <div class="paging-portfolio row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="../img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>

                            <div class="crud-admin">
                                <a class="update" href="">Edit</a>
                                <a class="delete" href="">Delete</a>
                            </div>

                        </div>

                        <div class="paging-portfolio row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="../img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>

                            <div class="crud-admin">
                                <a class="update" href="">Edit</a>
                                <a class="delete" href="">Delete</a>
                            </div>
                        </div>

                    </div>


                    <div class="paging-two">
                        <div class="paging-portfolio row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="../img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>

                            <div class="crud-admin">
                                <a class="update" href="">Edit</a>
                                <a class="delete" href="">Delete</a>
                            </div>
                        </div>

                        <div class="paging-portfolio row" title="Click to see more">
                            <!-- avatar -->
                            <div class="paging-avatar col-5">
                                <img src="../img/homepage/avatar.jpg" alt="avatar">
                            </div>
                            <!-- infor -->
                            <div class="paging-infor col-7">
                                <h3>Hoang Danh Tuan</h3>
                                <p>Lap trinh vien Java</p>
                                <p>Ha Noi</p>
                            </div>

                            <div class="crud-admin">
                                <a class="update" href="">Edit</a>
                                <a class="delete" href="">Delete</a>
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
        <a href="../HomePage/contact.jsp"></a>
        <script>
            const updates = document.querySelectorAll('.update')
            for (updateT of updates) {
                updateT.addEventListener("click", function () {
                    window.open("../HomePage/contact.jsp")
                })
            }
            
            const deletes = document.querySelectorAll('.delete')
            for (deleteT of deletes) {
                deleteT.addEventListener("click", function () {
                    window.open("../HomePage/home.jsp")
                })
            }
        </script>

        <script src="../js/HomePage/homepage.js" type="text/javascript"></script>

    </body>
</html>
