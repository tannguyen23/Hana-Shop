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
import javax.servlet.http.HttpSession;
import tan.dao.UserDAO;
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String USER = "welcome.jsp";
    private static final String ADMIN = "ViewAdminController";
    private static final String FAILED = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            if (email != null) {
                session = request.getSession();
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(email);
                userDTO.setEmail(email);
                userDTO.setName(email);
                userDTO.setRole("us");
                userDTO.setStatus(true);
                session.setAttribute("user", userDTO);
                UserDAO userDAO = new UserDAO();
                if (userDAO.findEmail(email) == 0) {
                    userDAO.insert(userDTO);
                }
                session.removeAttribute("email");
                url = "ViewMainController";
            } else {
                String userID = request.getParameter("txtUserID");
                String password = request.getParameter("txtPassword");
                UserDAO userDAO = new UserDAO();
                String role = userDAO.checkLogin(userID, password);
                if (role == null || role.equals("failed")) {
                    request.setAttribute("errorLogin", "Account is not found");
                    url = FAILED;
                } else {
                    session = request.getSession();
                    UserDTO userDTO = userDAO.getUserByID(userID);
                    session.setAttribute("user", userDTO);
                    if (role.equals("user")) {
                        url = USER;
                    } else if (role.equals("admin")) {
                        url = ADMIN;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            log("Error at LoginController : " + e.getMessage());
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
