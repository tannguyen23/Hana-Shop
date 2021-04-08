/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanta
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogOutController";
    private static final String PAGING = "ViewMainController";
    private static final String PAGINGSEARCH = "SearchProductController";
    private static final String PAGINGADMIN = "ViewAdminController";
    private static final String VIEWPRODUCTDETAIL = "ViewProductDetailController";
    private static final String ADDPRODUCTCART = "AddProductCartController";
    private static final String REMOVEPRODUCTCART = "RemoveProductCartController";
    private static final String UPDATEPRODUCTCART = "UpdateProductCartController";
    private static final String ORDERPRODUCTCART = "OrderProductCartController";
    private static final String REMOVEPRODUCT = "RemoveProductController";
    private static final String UPDATEPRODUCT = "UpdateProductController";
    private static final String VIEWUPDATE = "ViewUpdateProductController";
    private static final String VIEWCREATE = "ViewCreateProductController";
    private static final String CREATEPRODUCT = "CreateProductController";
    private static final String SEARCHPRODUCT = "SearchProductController";
    private static final String VIEWHISTORYORDERADMIN = "ViewHistoryOrderAdminController";
    private static final String VIEWHISTORYORDER = "ViewHistoryOrderController";
    private static final String VIEWHISTORYCARTORDER ="ViewHistoryCartOrderController";
    private static final String SEARCHHISTORY = "SearchHistoryController";
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");

            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("LogOut")) {
                url = LOGOUT;
            } else if (action.equals("Paging")) {
                url = PAGING;
            } else if (action.equals("PagingSearch")) {
                url = PAGINGSEARCH;
            } else if (action.equals("PagingAdmin")) {
                url = PAGINGADMIN;
            } else if (action.equals("ViewProductDetail")) {
                url = VIEWPRODUCTDETAIL;
            } else if (action.equals("AddProductCart")) {
                url = ADDPRODUCTCART;
            } else if (action.equals("RemoveProductCart")) {
                url = REMOVEPRODUCTCART;
            } else if (action.equals("UpdateProductCart")) {
                url = UPDATEPRODUCTCART;
            } else if (action.equals("OrderProduct")) {
                url = ORDERPRODUCTCART;
            } else if (action.equals("RemoveProduct")) {
                url = REMOVEPRODUCT;
            } else if (action.equals("Update Product")) {
                url = UPDATEPRODUCT;
            } else if (action.equals("ViewUpdateProduct")) {
                url = VIEWUPDATE;
            } else if (action.equals("ViewCreateProduct")) {
                url = VIEWCREATE;
            } else if (action.equals("Create Product")) {
                url = CREATEPRODUCT;
            } else if (action.equals("Search Product")) {
                url = SEARCHPRODUCT;
            } else if (action.equals("ViewHistoryOrderAdmin")) {
                url = VIEWHISTORYORDERADMIN;
            } else if (action.equals("ViewHistoryOrder")) {
                url = VIEWHISTORYORDER;
            } else if (action.equals("ViewHistoryCartOrder")) {
                url = VIEWHISTORYCARTORDER;
            } else if (action.equals("Search History")) {
                url = SEARCHHISTORY;
            }

        } catch (Exception e) {
            log("ERROR at MainController : " + e.getMessage());
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
