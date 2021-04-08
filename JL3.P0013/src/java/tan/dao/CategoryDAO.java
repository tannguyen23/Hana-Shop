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
import tan.dtos.CategoryDTO;

/**
 *
 * @author tanta
 */
public class CategoryDAO implements Serializable {

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
    
    public List<CategoryDTO> getAllCategory() throws Exception{
        List<CategoryDTO> list;
        try {
            String sql = "Select CateID,Name From tblCategory";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(rs.getString("CateID"));
                dto.setName(rs.getString("Name"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;
    }

}
