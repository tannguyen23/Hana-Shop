/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tan.db.MyConnection;
import tan.dtos.HistoryOrderDTO;

/**
 *
 * @author tanta
 */
public class HistoryOrderDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<HistoryOrderDTO> getHistoryOrderByUserID(String userID) throws Exception {
        List<HistoryOrderDTO> list;
        try {
            String sql = "Select OrderDetailID,OrderID,p.Name as 'pName',o.Quantity,o.Price,p.Image_File_Name\n"
                    + "From tblOrderDetail o join tblProduct p on o.ProductID = p.ProductID\n"
                    + "Where o.OrderID like 'OD-" + userID + "%'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                HistoryOrderDTO dto = new HistoryOrderDTO();
                dto.setOrderDetailID(rs.getString("OrderDetailID"));
                dto.setOrderID(rs.getString("OrderID"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setProductName(rs.getString("pName"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<HistoryOrderDTO> getAllHistoryOrder() throws Exception {
        List<HistoryOrderDTO> list;
        try {
            String sql = "Select OrderDetailID,OrderID,p.Name as 'pName',o.Quantity,o.Price,p.Image_File_Name\n"
                    + "From tblOrderDetail o join tblProduct p on o.ProductID = p.ProductID";

            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                HistoryOrderDTO dto = new HistoryOrderDTO();
                dto.setOrderDetailID(rs.getString("OrderDetailID"));
                dto.setOrderID(rs.getString("OrderID"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setProductName(rs.getString("pName"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<HistoryOrderDTO> searchHistoryOrderByUserID(String userID, String txtName, String txtMin, String txtMax) throws Exception {
        List<HistoryOrderDTO> list;
        try {
            String sql = "Select OrderDetailID,od.OrderID,p.Name as 'pName',od.Quantity,od.Price,o.TimeOfCreate\n"
                    + "From ((tblOrderDetail od\n"
                    + "join tblProduct p on od.ProductID = p.ProductID)\n"
                    + "join tblOrder o on o.OrderID = od.OrderID)\n"
                    + "Where  (o.TimeOfCreate BETWEEN '" + txtMin + "' AND '" + txtMax + "')\n"
                    + "AND (p.Name like '%" + txtName + "%') \n"
                    + "AND (o.OrderID like 'OD-" + userID + "-%')";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                HistoryOrderDTO dto = new HistoryOrderDTO();
                dto.setOrderDetailID(rs.getString("OrderDetailID"));
                dto.setOrderID(rs.getString("OrderID"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setProductName(rs.getString("pName"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
