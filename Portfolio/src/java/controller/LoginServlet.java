/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author LTC
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckLoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckLoginServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1>day la check login</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        //check co cookie hay khong
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            String Username = null, Password = null;
//            for (Cookie cooky : cookies) {
//                if (cooky.getName().equals("Username")) {
//                    Username = cooky.getValue();
//                }
//                if (cooky.getName().equals("Password")) {
//                    Password = cooky.getValue();
//                }
//            }
//            if (Username != null && Password != null) {
//                request.getRequestDispatcher("home.jsp").forward(request, response);
//                return;
//            }
//        }
        
        response.sendRedirect("login.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String Username = request.getParameter("username");
        String Password = request.getParameter("password");
        String remember = request.getParameter("remember");


        if (Username.length() < 5 || Password.length() < 5) {
            request.setAttribute("message", "username or password must contain at least 5 characters!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        DBContext db = new DBContext();
        Account account = db.getAccountByUsername(Username);
        if (account != null) {
            if (account.getPassword().equals(Password)) {
                if (remember != null) {
                    Cookie username = new Cookie("Username", Username);
                    Cookie password = new Cookie("Password", Password);
                    username.setMaxAge(60 * 60 * 24 * 7);
                    password.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(username);
                    response.addCookie(password);
                }
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                request.getRequestDispatcher("HomeServlet").forward(request, response);
                return;
            }
        }
        request.setAttribute("message", "username or password is not correct!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
