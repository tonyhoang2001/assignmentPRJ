<%-- 
    Document   : home
    Created on : Mar 11, 2022, 3:53:09 PM
    Author     : LTC
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Field"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <!-- import boostrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!--Import font awesome-->
        <script src="https://kit.fontawesome.com/563c930a8f.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <% int fieldID = 0; %>

        <header>
            <ul class="nav">
                <li>
                    <h1>Portfolio Website</h1>
                </li>
                <li><a href="HomeServlet">Home</a></li>
                <li><a href="create.jsp">Create Portfolio</a></li>
                <li><a href="viewPortServlet">Your Portfolio</a></li>
                <li><a href="contact.jsp">Contact Us</a></li>
                    <c:if test="${account.isIsAdmin()==true}">
                    <li><a href="AccountServlet">Accounts</a></li>
                    </c:if>
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
            <!-- Search form -->
            <div class="search">

                <form id="search-form" action="HomeServlet?index=1" method="POST" >
                    <div class="search-job">
                        <h3 class="search-field">Job:</h3>
                        <!-- search by job -->
                        <select name="job" id="sjob-list">
                            <option value="0">All</option>
                            <%
                                ArrayList<Field> fieldList = (ArrayList<Field>) session.getAttribute("fieldList");
                                int indexField = (Integer) session.getAttribute("indexField");
                                for (Field field : fieldList) {
                                    String selected = (field.getKey() == indexField) ? "selected" : "";
                                    if ("selected".equals(selected)) {
                                        fieldID = field.getKey();
                                    }
                            %>
                            <option <%= selected%> value="<%= field.getKey()%>"><%= field.getName()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="search-sex">
                        <h3 class="search-field">Sex:</h3>
                        <% String gender = (String) session.getAttribute("gender");
                            boolean t = gender.equals("male");
                        %>
                        <!-- search by sex -->
                        <input type="radio" <% if (gender.equals("all")) { %> checked="" <% } %> 
                               value="all" name="sex"> All
                        <input type="radio" <% if (gender.equals("male")) { %> checked="" <% } %> 
                               value="male" name="sex"> Male
                        <input type="radio" <% if (gender.equals("female")) { %> checked="" <% }%> 
                               value="female" name="sex"> Female
                    </div>

                    <button class="search-btn" type="submit">Search</button>
                </form>

            </div>

            <!-- Show portfolio by paging -->
            <div class="paging">
                <h2><i class="fa-solid fa-star paging-star"></i> People</h2>

                <div class="paging-container">
                    <c:if test="${listPaging != null}">

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
                                    </div>


                                    <div class="crud-admin">
                                        <a class="view" href="ViewServlet?id=${portfolio.getIDDetail()}">View</a>

                                        <c:if test="${account.isIsAdmin()==true}">
                                            <a class="update" href="UpdateServlet?id=${portfolio.getIDDetail()}">Edit</a>
                                            <a class="delete" onclick="Delete()" href="DeleteServlet?id=${portfolio.getIDDetail()}">Delete</a>
                                        </c:if>

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
                                    </div>

                                    <div class="crud-admin">
                                        <a class="view" href="ViewServlet?id=${portfolio.getIDDetail()}">View</a>

                                        <c:if test="${account.isIsAdmin()==true}">
                                            <a class="update" href="UpdateServlet?id=${portfolio.getIDDetail()}">Edit</a>
                                            <a class="delete" onclick="Delete()" href="DeleteServlet?id=${portfolio.getIDDetail()}">Delete</a>
                                        </c:if>

                                    </div>
                                </div>
                            </c:forEach>

                        </div>

                    </c:if>
                    <c:if test="${listPaging.size() == 0}">
                        <h3 style="position: absolute;">There is no Portfolio to see</h3>
                    </c:if>
                </div>

                <div class="page-index">
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <a class="${activeIndex == i ? "active" : ""}" href="HomeServlet?index=${i}&sex=<%= gender%>&job=<%= fieldID%>">${i}</a>
                    </c:forEach>
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
            const deletes = document.querySelectorAll('.delete')
            for (d of deletes) {
                d.addEventListener("click", function () {
                    window.confirm("Are you sure to delete this?")
                })
            }
        </script>

        <script src="js/homepage.js" type="text/javascript"></script>

    </body>
</html>
