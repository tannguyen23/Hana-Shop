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
import tan.dtos.UserDTO;

/**
 *
 * @author tanta
 */
public class UserDAO implements Serializable {

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
        if (rs != null) {
            rs.close();
        }
    }

    public String checkLogin(String userID, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "Select r.Name From tblUser u join tblRole r on u.RoleID = r.RoleID\n"
                    + "Where UserID = ? And Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Name");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public String getRoleByID(String roleID) throws Exception {
        String result = "";
        try {
            String sql = "Select Name From tblRole Where RoleID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, roleID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getString("Name");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public UserDTO getUserByID(String userID) throws Exception {
        UserDTO dto;
        try {
            String sql = "Select Name,Email,Status,RoleID From tblUser Where UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            dto = new UserDTO();
            if (rs.next()) {
                dto.setUserID(userID);
                dto.setName(rs.getNString("Name"));
                dto.setEmail(rs.getString("email"));
                dto.setStatus(rs.getBoolean("Status"));
                dto.setRole(rs.getString("RoleID"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean insert(UserDTO dto) throws Exception {
        boolean check;
        try {
            String sql = "Insert into tblUser(UserID,Name,Email,RoleID,Status) Values (?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setString(2, dto.getName());
            preStm.setString(3, dto.getEmail());
            preStm.setString(4, dto.getRole());
            preStm.setBoolean(5, true);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int findEmail(String email) throws Exception {
        int result = 0;
        try {
            String sql = "Select Count(UserID) From tblUser Where UserID like ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
