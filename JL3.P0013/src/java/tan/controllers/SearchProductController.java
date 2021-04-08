/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tan.dao.CategoryDAO;
import tan.dao.ProductDAO;
import tan.dao.UserDAO;
import tan.dtos.CategoryDTO;
import tan.dtos.ProductDTO;
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class SearchProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "main.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = ERROR;
        int totalPage = 0;
        int pageIndex = 1;
        try {
            String txtSearch = request.getParameter("txtSearch");
            String txtCategory = request.getParameter("slCategory");
            String txtMin = request.getParameter("txtMin");
            Float min = Float.parseFloat(txtMin);
            String txtMax = request.getParameter("txtMax");
            Float max = Float.parseFloat(txtMax);
            String txtIndex = request.getParameter("index");
            if (txtIndex != null) {
                pageIndex = Integer.parseInt(txtIndex);
            }
            ProductDAO productDAO = new ProductDAO();
            ArrayList<ProductDTO> listProduct = new ArrayList<>();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");
            String role ="";
            if (user != null) {
                UserDAO userDAO = new UserDAO();
                role = userDAO.getRoleByID(user.getRole());
            }
            if (role.equals("admin")) {
                listProduct = (ArrayList<ProductDTO>) productDAO.getSearchProductsByIndexAdmin(pageIndex, 12, txtSearch, txtCategory, min, max);
                totalPage = productDAO.getTotalSearchPageProductAdmin(12, txtSearch, txtCategory, min, max);
                url = ADMIN;
            } else {
                listProduct = (ArrayList<ProductDTO>) productDAO.getSearchProductsByIndex(pageIndex, 8, txtSearch, txtCategory, min, max);
                totalPage = productDAO.getTotalSearchPageProduct(8, txtSearch, txtCategory, min, max);
                url = USER;
            }

            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<CategoryDTO> listCategory = (ArrayList<CategoryDTO>) categoryDAO.getAllCategory();
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("currentIndex", pageIndex);
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("txtCategory", txtCategory);
            request.setAttribute("txtMin", txtMin);
            request.setAttribute("txtMax", txtMax);
            request.setAttribute("checkSearch", "true");
        } catch (Exception e) {
            log("Error at SearchProductController : " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
