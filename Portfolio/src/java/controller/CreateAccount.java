/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author LTC
 */
public class CreateAccount extends HttpServlet {

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
            out.println("<title>Servlet CreateAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateAccount at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("createAcc.jsp");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String adminString = request.getParameter("isAdmin");

        if (username.length() < 5 || password.length() < 5) {
            request.setAttribute("error", "Username and password must be greater than 4 characters");
            request.getRequestDispatcher("createAcc.jsp").forward(request, response);
            return;
        }

        if (!repassword.equals(password)) {
            request.setAttribute("error", "Pasword and Re-password must be same");
            request.getRequestDispatcher("createAcc.jsp").forward(request, response);
            return;
        }
        DBContext db = new DBContext();
        Account account = db.getAccountByUsername(username);
        
        if (account != null) {
            request.setAttribute("error", "username existed! Choose another name");
            request.getRequestDispatcher("createAcc.jsp").forward(request, response);
            return;
        }

        boolean isAdmin = false;

        if ("on".equals(adminString)) {
            isAdmin = true;
        }

        Account eAccount = new Account(username, password, isAdmin);

        db.insertAccount(eAccount);

        HttpSession session = request.getSession();

        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("repassword", repassword);
        session.setAttribute("isAdmin", isAdmin);

        request.setAttribute("successC", "Create successfully!");
        request.getRequestDispatcher("createAcc.jsp").forward(request, response);
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
