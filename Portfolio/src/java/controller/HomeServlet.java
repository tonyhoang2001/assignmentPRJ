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
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Field;
import model.PortfolioDetail;

/**
 *
 * @author LTC
 */
public class HomeServlet extends HttpServlet {

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

        String field = request.getParameter("job");
        String gender = request.getParameter("sex");

        int indexField;
        if (field == null) {
            indexField = 0;
        } else {
            indexField = Integer.parseInt(field);
        }

        if (gender == null) {
            gender = "all";
        }

        // nguoi dung khong loc theo gi
        if (indexField == 0 && "all".equals(gender)) {

            String index = request.getParameter("index");
            int pageIndex = 1;
            int pageSize = 4;

            if (index == null) {
                pageIndex = 1;
            } else {
                pageIndex = Integer.parseInt(index);
            }

            DBContext db = new DBContext();
            ArrayList<Field> fieldList = db.getField();
            ArrayList<PortfolioDetail> listPaging = db.paging(pageIndex, pageSize);
            ArrayList<PortfolioDetail> crudList = db.paging(pageIndex, 12);
            int count = db.count();

            int endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            
            int endPage2 = count / 12;
            if (count % 12 != 0) {
                endPage2++;
            }

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");

            session.setAttribute("fieldList", fieldList);
            session.setAttribute("activeIndex", index);
            session.setAttribute("account", account);
            session.setAttribute("endPage", endPage);
            session.setAttribute("endPage2", endPage2);
            session.setAttribute("listPaging", listPaging);
            session.setAttribute("crudList", crudList);
            session.setAttribute("indexField", indexField);
            session.setAttribute("gender", gender);

            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        } // nguoi dung da chon loc
        else {
            DBContext db = new DBContext();
            ArrayList<Field> fieldList = db.getField();
            boolean isGender = false;
            String fieldName = "";
            int pageSize = 4;
            int endPage = 0;
            int endPage2 = 0;
            String index = request.getParameter("index");
            int activeIndex = Integer.parseInt(index);
            ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
            ArrayList<PortfolioDetail> crudList = new ArrayList<>();

            for (Field field1 : fieldList) {
                if (field1.getKey() == indexField) {
                    fieldName = field1.getName();
                    break;
                }
            }

            if (gender.equals("male")) {
                isGender = true;
            }
            if (gender.equals("female")) {
                isGender = false;
            }

            // chi search theo field
            if (gender.equals("all")) {
                int count = db.countSearchByField(fieldName);

                endPage = count / pageSize;
                if (count % pageSize != 0) {
                    endPage++;
                }
                
                endPage2 = count / 10;
                if (count % 12 != 0) {
                    endPage2++;
                }
                
                crudList = db.pagingSearchByField(activeIndex, 12, fieldName);
                listPaging = db.pagingSearchByField(activeIndex, pageSize, fieldName);

            } //chi search theo gioi tinh
            else if (indexField == 0) {
                int count = db.countSearchByGender(isGender);

                endPage = count / pageSize;
                if (count % pageSize != 0) {
                    endPage++;
                }
                
                endPage2 = count / 10;
                if (count % 12 != 0) {
                    endPage2++;
                }
                crudList = db.pagingSearchByGender(activeIndex, 12, isGender);
                listPaging = db.pagingSearchByGender(activeIndex, pageSize, isGender);
            } // search theo ca 2
            else {
                int count = db.countSearch(fieldName, isGender);

                endPage = count / pageSize;
                if (count % pageSize != 0) {
                    endPage++;
                }
                endPage2 = count / 10;
                if (count % 12 != 0) {
                    endPage2++;
                }
                crudList = db.pagingSearch(activeIndex, 12, fieldName, isGender);
                listPaging = db.pagingSearch(activeIndex, pageSize, fieldName, isGender);
            }

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");

            session.setAttribute("endPage", endPage);
            session.setAttribute("endPage2", endPage2);
            session.setAttribute("activeIndex", activeIndex);
            session.setAttribute("fieldList", fieldList);
            session.setAttribute("account", account);
            session.setAttribute("listPaging", listPaging);
            session.setAttribute("crudList", crudList);
            session.setAttribute("indexField", indexField);
            session.setAttribute("gender", gender);

            request.getRequestDispatcher("home.jsp").forward(request, response);
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
