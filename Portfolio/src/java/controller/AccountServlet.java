/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.PortfolioDetail;

/**
 *
 * @author LTC
 */
public class AccountServlet extends HttpServlet {

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
        
        String index = request.getParameter("index");
        int pageIndex = 1;
        int pageSize = 6;

        if (index == null) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(index);
        }
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int idAcc = account.getIDAcc();
        
        DBContext db = new DBContext();
        ArrayList<Account> listPaging = db.pagingAccount(pageIndex, pageSize,idAcc);
        int count = db.countAccounts(idAcc);

        int endPage = count / pageSize;
        if (count % pageSize != 0) {
            endPage++;
        }
        
        String error = (String) request.getAttribute("error");
        request.setAttribute("error", error);
        String successU = (String) request.getAttribute("successU");
        request.setAttribute("successU", successU);
        String successD = (String) request.getAttribute("successD");
        request.setAttribute("successD", successD);

        session.setAttribute("activeIndex", index);
        session.setAttribute("account", account);
        session.setAttribute("endPage", endPage);
        session.setAttribute("listPaging", listPaging);
        
        request.getRequestDispatcher("Account.jsp").forward(request, response);
        return;
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
