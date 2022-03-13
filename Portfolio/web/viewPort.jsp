<%-- 
    Document   : crudAdmin
    Created on : Mar 11, 2022, 6:02:14 PM
    Author     : LTC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD ADMIN</title>
        <link href="css/viewPort.css" rel="stylesheet" type="text/css"/>
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
                    <h4 class="btn" id="user-name" href="">${account.getUsername()}</h4>
                    <i id="down" class="fa-solid fa-caret-down"></i>
                    <i id="right" class="fa-solid fa-caret-right"></i>
                    <a id="logout" href="login.jsp">Log out</a>
                </li>
            </ul>
        </header>

        <main>
            <!-- Show portfolio by paging -->
            <div class="paging">

                <div class="paging-container">

                    <c:if test="${listPaging.size() == 0}">
                        <h3 style="position: absolute;">There is no Portfolio to see</h3>
                    </c:if>

                    <div class="paging-two">
                        <c:forEach items="${listPaging}" var="portfolio" begin="0" end="1">
                            <div class="paging-portfolio row" title="Click to see more">
                                <!-- avatar -->
                                <div class="paging-avatar col-5">
                                    <img src="img/default.png" alt="avatar">
                                </div>
                                <!-- infor -->
                                <div class="paging-infor col-7">
                                    <h3>${portfolio.getNameUser()}</h3>
                                    <p>${portfolio.getField()}</p>
                                    <p>${portfolio.getAddress()}</p>
                                    <p>${portfolio.getPortfolio().getNamePortf()}</p>
                                </div>

                                <div class="crud-admin">
                                    <a class="view" href="ViewCrudServlet?id=${portfolio.getIDDetail()}">View</a>
                                    <a class="update" href="UpdateCrudServlet?id=${portfolio.getIDDetail()}">Edit</a>
                                    <a class="delete" href="">Delete</a>
                                </div>

                            </div>
                        </c:forEach>
                    </div>

                    <div class="paging-two">
                        <c:forEach items="${listPaging}" var="portfolio" begin="2" end="3">
                            <div class="paging-portfolio row" title="Click to see more">
                                <!-- avatar -->
                                <div class="paging-avatar col-5">
                                    <img src="img/default.png" alt="avatar">
                                </div>
                                <!-- infor -->
                                <div class="paging-infor col-7">
                                    <h3>${portfolio.getNameUser()}</h3>
                                    <p>${portfolio.getField()}</p>
                                    <p>${portfolio.getAddress()}</p>
                                    <p>${portfolio.getPortfolio().getNamePortf()}</p>
                                </div>

                                <div class="crud-admin">
                                    <a class="view" href="ViewCrudServlet?id=${portfolio.getIDDetail()}">View</a>
                                    <a class="update" href="UpdateCrudServlet?id=${portfolio.getIDDetail()}">Edit</a>
                                    <a class="delete" href="">Delete</a>
                                </div>

                            </div>
                        </c:forEach>
                    </div>

                </div>

                <div class="page-index">
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <a class="${activeIndex == i ? "active" : ""}" href="viewPortServlet?index=${i}">${i}</a>
                    </c:forEach>
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

            const deletes = document.querySelectorAll('.delete')
            for (deleteT of deletes) {
                deleteT.addEventListener("click", function () {
                    window.open("../HomePage/home.jsp")
                })
            }
        </script>

        <script src="js/homepage.js" type="text/javascript"></script>

    </body>
</html>
