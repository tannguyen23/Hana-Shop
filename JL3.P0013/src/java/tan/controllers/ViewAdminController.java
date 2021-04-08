/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tan.dao.CategoryDAO;
import tan.dao.ProductDAO;
import tan.dtos.CategoryDTO;
import tan.dtos.ProductDTO;

/**
 *
 * @author tanta
 */
public class ViewAdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = ERROR;
        int totalPage = 0;
        int pageIndex = 1;
        float maxPrice = 0;
        float minPrice = 0;
        try {
            String txtIndex = request.getParameter("index");
            if (txtIndex != null) {
                pageIndex = Integer.parseInt(txtIndex);
            }
            ProductDAO productDAO = new ProductDAO();
            totalPage = productDAO.getTotalPageProductAdmin(12);
            maxPrice = (productDAO.getMaxPrice() - (productDAO.getMaxPrice() % 1000) + 1000);
            ArrayList<ProductDTO> listProduct = (ArrayList<ProductDTO>) productDAO.getProductsByIndexAdmin(pageIndex, 12);
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<CategoryDTO> listCategory = (ArrayList<CategoryDTO>) categoryDAO.getAllCategory();
            if (listProduct != null) {                
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.setAttribute("currentIndex", pageIndex);
                request.setAttribute("txtMax", maxPrice);
                request.setAttribute("txtMin", minPrice);
                request.setAttribute("listCategory", listCategory);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error ViewMainController : " + e.getMessage());
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
