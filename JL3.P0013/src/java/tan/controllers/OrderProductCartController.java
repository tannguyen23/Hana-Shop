/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tan.dao.OrderDAO;
import tan.dao.OrderDetailDAO;
import tan.dao.ProductDAO;
import tan.dtos.CartProduct;
import tan.dtos.OrderDTO;
import tan.dtos.OrderDetailDTO;
import tan.dtos.ProductDTO;

/**
 *
 * @author tanta
 */
public class OrderProductCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewMainController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartProduct cartProduct = (CartProduct) session.getAttribute("cartProduct");
            String userID = cartProduct.getCustomer();
            OrderDAO orderDAO = new OrderDAO();
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            String orderID = orderDAO.getLastOrderByUser(userID);
            if (orderID == null) {
                orderID = "OD-" + userID + "-1";
            } else {
                String[] tmp = orderID.split("-");
                int count = Integer.parseInt(tmp[2]);
                orderID = "OD-" + userID + "-" + (count + 1);
            }
            float total = cartProduct.total();
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderID(orderID);
            orderDTO.setUserID(userID);
            orderDTO.setTotal(total);
            orderDTO.setTimeOfCreate(LocalDateTime.now());
            boolean check = orderDAO.insertOrder(orderDTO);
            if (check) {
                int count = 1;
                for (ProductDTO dto : cartProduct.getProductCart().values()) {
                    String orderDetailID = orderID + "-" + count++;
                    OrderDetailDTO orderDetailDto = new OrderDetailDTO();
                    orderDetailDto.setOrderDetailID(orderDetailID);
                    orderDetailDto.setOrderID(orderID);
                    orderDetailDto.setProductID(dto.getId());
                    orderDetailDto.setQuantity(dto.getQuantity());
                    orderDetailDto.setPrice(dto.getPrice() * dto.getQuantity());
                    ProductDTO proQOH = new ProductDAO().getProductByID(dto.getId());
                    if (proQOH.getQuantity() < dto.getQuantity()) {
                        request.setAttribute("error", dto.getName());
                        url = "ViewCartController";
                        break;
                    }
                    boolean chkOrder = orderDetailDAO.insertOrderDetail(orderDetailDto);
                    if (chkOrder) {
                        ProductDAO productDAO = new ProductDAO();
                        int currentQuantity = proQOH.getQuantity() - dto.getQuantity();
                        productDAO.setQuantity(currentQuantity, dto.getId());
                        url = SUCCESS;
                        cartProduct = null;
                    }
                }

            }
            session.setAttribute("cartProduct", cartProduct);
        } catch (Exception e) {
            log("Error at OrderProductCartController : " + e.getMessage());
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
