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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tan.db.MyConnection;
import tan.dtos.OrderDTO;

/**
 *
 * @author tanta
 */
public class OrderDAO implements Serializable {

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

    public String getLastOrderByUser(String userID) throws Exception {
        String id = null;
        try {
            String sql = "Select OrderID From tblOrder Where TimeOfCreate = (Select MAX(TimeOfCreate) From tblOrder Where UserID = ?)";
            Connection conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getString("OrderID");
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public List<OrderDTO> getAllOrder() throws Exception {
        List<OrderDTO> list;
        try {
            String sql = "Select OrderID,UserID,Total,TimeOfCreate From tblOrder Order By TimeOfCreate";
            Connection conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                OrderDTO dto = new OrderDTO();
                dto.setOrderID(rs.getString("OrderID"));
                dto.setUserID(rs.getString("UserID"));
                dto.setTotal(rs.getFloat("Total"));
                dto.setTimeOfCreate(Timestamp.valueOf(rs.getString("TimeOfCreate")).toLocalDateTime());
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertOrder(OrderDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblOrder(OrderID, UserID, Total, TimeOfCreate)"
                    + " values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getOrderID());
            preStm.setString(2, dto.getUserID());
            preStm.setFloat(3, dto.getTotal());
            preStm.setString(4, dto.getStringTimeOfCreate());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String getMaxDate() throws Exception {
        String result="";
        try {
            String sql = "Select Left((Convert(varchar,Max(TimeOfCreate),120)),10)\n"
                    + "From tblOrder";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    public String getMinDate() throws Exception {
        String result="";
        try {
            String sql = "Select Left((Convert(varchar,Min(TimeOfCreate),120)),10)\n"
                    + "From tblOrder";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
