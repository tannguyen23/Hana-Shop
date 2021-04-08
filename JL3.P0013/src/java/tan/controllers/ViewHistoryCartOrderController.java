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
import tan.dao.OrderDetailDAO;
import tan.dao.UserDAO;
import tan.dtos.CartProduct;
import tan.dtos.ProductDTO;
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class ViewHistoryCartOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String USER = "historyOrder.jsp";
    private static final String ADMIN = "historyOrderAdmin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String orderID = request.getParameter("orderID");
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            ArrayList<ProductDTO> listPro = (ArrayList<ProductDTO>) orderDetailDAO.getProductInOrderHistory(orderID);
            CartProduct cartProduct = new CartProduct("");
            for (ProductDTO productDTO : listPro) {
                cartProduct.addToCart(productDTO, productDTO.getQuantity());
            }
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            String role = "";
            if (userDTO != null) {
                role = userDTO.getRole();
                UserDAO userDAO = new UserDAO();
                role = userDAO.getRoleByID(role);
            }
            
            if (role.equals("admin")) {
                url = ADMIN;
            } else {
                url = USER;
            }   
            request.setAttribute("cartProduct", cartProduct);
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at ViewHistoryCartOrderController : " + e.getMessage());
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
