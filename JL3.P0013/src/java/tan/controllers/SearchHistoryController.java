/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tan.dao.HistoryOrderDAO;
import tan.dao.OrderDAO;
import tan.dao.UserDAO;
import tan.dtos.HistoryOrderDTO;
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class SearchHistoryController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "historyAdmin.jsp";
    private static final String USER = "history.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String txtName = request.getParameter("txtName");
            String txtTimeStart = request.getParameter("txtMin");
            String txtTimeEnd = request.getParameter("txtMax");
            OrderDAO orderDAO = new OrderDAO();
            if (txtTimeStart == null || txtTimeStart.isEmpty()) {
                txtTimeStart = orderDAO.getMinDate();
                Date minDate = new SimpleDateFormat("yyyy-mm-dd").parse(txtTimeStart);
                Date tomorrow = new Date(minDate.getTime() - (1000 * 60 * 60 * 24));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                txtTimeStart = dateFormat.format(tomorrow);
            }
            if (txtTimeEnd == null || txtTimeEnd.isEmpty()) {
                txtTimeEnd = orderDAO.getMaxDate();
                Date maxDate = new SimpleDateFormat("yyyy-mm-dd").parse(txtTimeEnd);
                Date tomorrow = new Date(maxDate.getTime() + (1000 * 60 * 60 * 24));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                txtTimeEnd = dateFormat.format(tomorrow);
            }
            HistoryOrderDAO historyOrderDAO = new HistoryOrderDAO();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");
            UserDAO userDAO = new UserDAO();
            String role = userDAO.getRoleByID(user.getRole());
            ArrayList<HistoryOrderDTO> listOrder = new ArrayList<>();
            if (role.equals("admin")) {
                listOrder = (ArrayList<HistoryOrderDTO>) historyOrderDAO.searchHistoryOrderByUserID("%", txtName, txtTimeStart, txtTimeEnd);
                url = ADMIN;
            } else {
                listOrder = (ArrayList<HistoryOrderDTO>) historyOrderDAO.searchHistoryOrderByUserID(user.getUserID(), txtName, txtTimeStart, txtTimeEnd);
                url = USER;
            }
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("txtName", txtName);
            request.setAttribute("txtMin", txtTimeStart);
            request.setAttribute("txtMax", txtTimeEnd);
        } catch (Exception e) {
            log("Error at SearchHistoryController : " + e.getMessage());
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
