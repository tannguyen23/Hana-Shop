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
import tan.db.MyConnection;
import tan.dtos.LogDTO;

/**
 *
 * @author tanta
 */
public class LogDAO implements Serializable {

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

    public String getLastID() throws Exception {
        String result = "";
        try {
            String sql = "Select Max(LogID) From tblLog";
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

    public boolean insert(LogDTO dto) throws Exception {
        boolean check;
        try {
            String sql = "Insert into tblLog(LogID,UserID,ProductID,Action,TimeOfCreate) Values (?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getLogID());
            preStm.setString(2, dto.getUserID());
            preStm.setString(3, dto.getProductID());
            preStm.setString(4, dto.getAction());
            preStm.setString(5, dto.getStringTimeOfCreate());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
