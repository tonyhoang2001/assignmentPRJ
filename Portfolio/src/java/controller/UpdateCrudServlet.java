package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Portfolio;
import model.PortfolioDetail;
import model.Template;

/**
 *
 * @author LTC
 */
public class UpdateCrudServlet extends HttpServlet {

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
        PortfolioDetail pdt = db.getPDTByIDPD(idPDT);
        
        int idPD = pdt.getIDDetail();
        String name = pdt.getNameUser();
        boolean gender = pdt.isGender();
        String field = pdt.getField();
        String des = pdt.getDescription();
        String skill = pdt.getSkill();
        String project = pdt.getProject();
        String address = pdt.getAddress();
        long phone = pdt.getPhone();
        String email = pdt.getEmail();
        String namePortf = pdt.getPortfolio().getNamePortf();

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
        
        session.setAttribute("account", account);
        session.setAttribute("idPD", idPD);
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
        session.setAttribute("namePortf", namePortf);

        //patrix
        if (pdt.getTemplate().getIDTem() == 1) {
            request.getRequestDispatcher("patrixEdit.jsp").forward(request, response);
            return;
        }

        //elen
        if (pdt.getTemplate().getIDTem() == 2) {
            request.getRequestDispatcher("ElenEdit.jsp").forward(request, response);
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
        
        String idPDString = request.getParameter("idPD");
        int idPDT = Integer.parseInt(idPDString);
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        DBContext db = new DBContext();
        PortfolioDetail pdt = db.getPDTByIDPD(idPDT);
        
        int idTem = pdt.getTemplate().getIDTem();
        int idPortf = pdt.getPortfolio().getIDPortf();

        String name = request.getParameter("name");
        String genderString = request.getParameter("gender");
        String field = request.getParameter("field");
        String des = request.getParameter("des");
        String skill1 = request.getParameter("skill1");
        String skill2 = request.getParameter("skill2");
        String skill3 = request.getParameter("skill3");
        String project1 = request.getParameter("project1");
        String project2 = request.getParameter("project2");
        String project3 = request.getParameter("project3");
        String address = request.getParameter("address");
        String phoneString = request.getParameter("phone");
        String email = request.getParameter("email");
        String namePortf = request.getParameter("namePortf");

        boolean gender = false;
        if (genderString.equals("0")) {
            gender = false;
        }
        if (genderString.equals("1")) {
            gender = true;
        }

        String skill = skill1 + "-" + skill2 + "-" + skill3;
        String project = project1 + "-" + project2 + "-" + project3;
        long phone = Long.parseLong(phoneString);

        int IDAcc = account.getIDAcc();

        Portfolio p = new Portfolio(namePortf, IDAcc, idTem);
        Template t = db.getTemplateByID(idTem);
        PortfolioDetail pdAdd = new PortfolioDetail(name, gender, des, field, skill, project,
                address, phone, email, p, account, t);
        
        db.updatePDT(pdAdd, pdt.getIDDetail());
        db.updateP(pdAdd, pdt.getPortfolio().getIDPortf());
        
        session.setAttribute("account", account);
        session.setAttribute("message", "Update successfully!");
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
        session.setAttribute("namePortf", namePortf);
        
        if (idTem == 1){
            request.getRequestDispatcher("patrixEdit.jsp").forward(request, response);
            return;
        }
        
        if (idTem == 2){
            request.getRequestDispatcher("ElenEdit.jsp").forward(request, response);
            return;
        }
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
