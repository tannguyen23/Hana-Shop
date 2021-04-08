/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tan.dao.LogDAO;
import tan.dao.ProductDAO;
import tan.dtos.LogDTO;
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class RemoveProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO productDAO = new ProductDAO();
            String productID = request.getParameter("productID");
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            boolean check;
            check = productDAO.remove(productID);
            if (check) {
                LogDTO logDTO = new LogDTO();
                LogDAO logDAO = new LogDAO();

                String id = logDAO.getLastID();
                if (id != null) {
                    String[] tmp = id.split("-");
                    int count = Integer.parseInt(tmp[1]);
                    id = "LOG-" + (count + 1);
                } else {
                    id = "LOG-0";
                }

                logDTO.setLogID(id);
                logDTO.setAction("Remove");
                logDTO.setProductID(productID);
                logDTO.setUserID(userDTO.getUserID());
                logDTO.setTimeOfCreate(LocalDateTime.now());
                logDAO.insert(logDTO);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at RemoveProductController : " + e.getMessage());
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
