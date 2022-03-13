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
import model.PortfolioDetail;

/**
 *
 * @author LTC
 */
public class ViewCrudServlet extends HttpServlet {

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

        String idString = request.getParameter("id");
        int idPDT = Integer.parseInt(idString);

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        DBContext db = new DBContext();
        PortfolioDetail pdt = db.getPDTByIDPD(account.getIDAcc(), idPDT);

        String name = pdt.getNameUser();
        boolean gender = pdt.isGender();
        String field = pdt.getField();
        String des = pdt.getDescription();
        String skill = pdt.getSkill();
        String project = pdt.getProject();
        String address = pdt.getAddress();
        long phone = pdt.getPhone();
        String email = pdt.getEmail();

        String skill1 = "";
        String skill2 = "";
        String skill3 = "";

        String project1 = "";
        String project2 = "";
        String project3 = "";

        if (skill != null) {
            String[] skills = skill.split("-");

            for (int i = 0; i < skills.length; i++) {
                if (i == 0) {
                    skill1 = skills[i];
                }
                if (i == 1) {
                    skill2 = skills[i];
                }
                if (i == 2) {
                    skill3 = skills[i];
                }
            }
        } else {
            skill = "";
        }

        if (project != null) {
            String[] projects = project.split("-");

            for (int i = 0; i < projects.length; i++) {
                if (i == 0) {
                    project1 = projects[i];
                }
                if (i == 1) {
                    project2 = projects[i];
                }
                if (i == 2) {
                    project3 = projects[i];
                }
            }
        }

        String genderString = "";
        if (gender == false) {
            genderString = "Female";
        } else {
            genderString = "Male";
        }

        session.setAttribute("name", name);
        session.setAttribute("gender", genderString);
        session.setAttribute("field", field);
        session.setAttribute("des", des);
        session.setAttribute("skill1", skill1);
        session.setAttribute("skill2", skill2);
        session.setAttribute("skill3", skill3);
        session.setAttribute("project1", project1);
        session.setAttribute("project2", project2);
        session.setAttribute("project3", project3);
        session.setAttribute("address", address);
        session.setAttribute("phone", phone);
        session.setAttribute("email", email);

        //patrix
        if (pdt.getTemplate().getIDTem() == 1) {
            request.getRequestDispatcher("patrix.jsp").forward(request, response);
            return;
        }

        //elen
        if (pdt.getTemplate().getIDTem() == 2) {
            request.getRequestDispatcher("Elen.jsp").forward(request, response);
            return;
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
