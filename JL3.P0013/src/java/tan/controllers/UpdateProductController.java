/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tan.dao.LogDAO;
import tan.dao.ProductDAO;
import tan.dtos.LogDTO;

import tan.dtos.ProductDTO;
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class UpdateProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        String itemName = item.getName();
                        fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        String RealPath = getServletContext().getRealPath("/") + "img\\" + fileName;
                        if (!fileName.isEmpty()) {
                            File savedFile = new File(RealPath);
                            item.write(savedFile);
                        }

                    }
                }
                String productID = "";
                String txtName = "";
                String txtQuantity = "";
                String txtPrice = "";
                String txtCategory = "";
                String txtStatus = "";
                productID = (String) params.get("productID");
                txtName = (String) params.get("txtName");
                txtName = new String(txtName.getBytes("iso-8859-1"), "UTF-8");
                txtQuantity = (String) params.get("txtQuantity");
                txtPrice = (String) params.get("txtPrice");
                txtCategory = (String) params.get("slCategory");
                txtStatus = (String) params.get("slStatus");
                int quantity = Integer.parseInt(txtQuantity);
                float price = Float.parseFloat(txtPrice);

                Boolean status = null;
                if (txtStatus.equals("true")) {
                    status = true;
                } else if (txtStatus.equals("false")) {
                    status = false;
                }
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(productID);
                productDTO.setName(txtName);
                productDTO.setCategory(txtCategory);
                productDTO.setQuantity(quantity);
                productDTO.setPrice(price);
                productDTO.setStatus(status);
                if (fileName != null) {
                    productDTO.setImage_File_Name(fileName);
                }
                ProductDAO productDAO = new ProductDAO();
                if (productDAO.update(productDTO)) {
                    LogDTO logDTO = new LogDTO();
                    LogDAO logDAO = new LogDAO();
                    HttpSession session = request.getSession();
                    UserDTO userDTO = (UserDTO) session.getAttribute("user");
                    String id = logDAO.getLastID();
                    if (id != null) {
                        String[] tmp = id.split("-");
                        int count = Integer.parseInt(tmp[1]);
                        id = "LOG-" + (count + 1);
                    } else {
                        id = "LOG-0";
                    }

                    logDTO.setLogID(id);
                    logDTO.setAction("Update");
                    logDTO.setProductID(productID);
                    logDTO.setUserID(userDTO.getUserID());
                    logDTO.setTimeOfCreate(LocalDateTime.now());
                    logDAO.insert(logDTO);
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at UpdateProductController : " + e.getMessage());
//            e.printStackTrace();
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
