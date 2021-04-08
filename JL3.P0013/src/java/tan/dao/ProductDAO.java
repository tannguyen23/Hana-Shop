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
import tan.dtos.ProductDTO;

/**
 *
 * @author tanta
 */
public class ProductDAO implements Serializable {

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

    public List<ProductDTO> getAllProducts() throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select ProductID,Name,Image_File_Name,Quantity,Price From tblProduct";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getNString("Name"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setQuantity(rs.getInt("Quantity"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    public ProductDTO getProductByID(String id) throws Exception {
        ProductDTO dto = new ProductDTO();
        try {
            String sql = "Select ProductID,p.Name,c.CateID as 'Category',Image_File_Name,Quantity,TimeOfCreate,Price,Status\n"
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getNString("Name"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                dto.setCategory(rs.getString("Category"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setTimeOfCreate(Timestamp.valueOf(rs.getString("TimeOfCreate")).toLocalDateTime());
                dto.setStatus(rs.getBoolean("Status"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<ProductDTO> getProductsByIndex(int index, int mount) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select ProductID,c.Name as 'CateName',p.Name,Image_File_Name,Quantity,Price,TimeOfCreate,Status "
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Where (p.Status = 1) AND (p.Quantity > 0)\n"
                    + "Order By TimeOfCreate\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, (mount * (index - 1)));
            preStm.setInt(2, mount);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getNString("Name"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setCategory(rs.getString("CateName"));
                dto.setStatus(rs.getBoolean("Status"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;

    }

    public int getTotalPageProduct(int numEachPage) throws Exception {
        int result = 0;
        int tmp = 0;
        try {
            String sql = "Select COUNT(ProductID) from tblProduct Where (Status = 1 AND (Quantity > 0))";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                tmp = rs.getInt(1);
            }
            result = tmp / numEachPage;
            if (tmp % numEachPage != 0) {
                result++;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ProductDTO> getProductsByIndexAdmin(int index, int mount) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select ProductID,c.Name as 'CateName',p.Name,Image_File_Name,Quantity,Price,TimeOfCreate,Status "
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Order By TimeOfCreate\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, (mount * (index - 1)));
            preStm.setInt(2, mount);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getNString("Name"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setCategory(rs.getString("CateName"));
                dto.setStatus(rs.getBoolean("Status"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;

    }

    public int getTotalPageProductAdmin(int numEachPage) throws Exception {
        int result = 0;
        int tmp = 0;
        try {
            String sql = "Select COUNT(ProductID) from tblProduct";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                tmp = rs.getInt(1);
            }
            result = tmp / numEachPage;
            if (tmp % numEachPage != 0) {
                result++;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public Float getMaxPrice() throws Exception {
        Float result = 0f;
        try {
            String sql = "Select Max(Price) from tblProduct";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getFloat(1);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public int getLastId() throws Exception {
        int result = 0;
        try {
            String sql = "Select Max(CAST(ProductID as int)) as result From tblProduct";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("result");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(ProductDTO dto) throws Exception {
        boolean check;
        try {
            String sql = "Insert into tblProduct(ProductID,Name,CateID,Image_File_Name,Quantity,Price,TimeOfCreate,Status) Values \n"
                    + "(?,?,?,?,?,?,?,?)";

            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getId());
            preStm.setNString(2, dto.getName());
            preStm.setString(3, dto.getCategory());
            preStm.setString(4, dto.getImage_File_Name());
            preStm.setInt(5, dto.getQuantity());
            preStm.setFloat(6, dto.getPrice());
            preStm.setString(7, dto.getStringTimeOfCreate());
            preStm.setBoolean(8, dto.isStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(ProductDTO dto) throws Exception {
        boolean check;
        try {
            String sql = "Update tblProduct Set Name = ?,CateID = ?,Quantity = ?, Price = ?, Status = ?\n"
                    + "Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, dto.getName());
            preStm.setString(2, dto.getCategory());
            preStm.setInt(3, dto.getQuantity());
            preStm.setFloat(4, dto.getPrice());
            preStm.setBoolean(5, dto.isStatus());
            preStm.setString(6, dto.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean setQuantity(int quantity, String productID) throws Exception {
        boolean check;
        try {
            String sql = "Update tblProduct Set Quantity = ? Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setString(2, productID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean remove(String idProduct) throws Exception {
        boolean check;
        try {
            String sql = "Update tblProduct Set Status = 0 Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, idProduct);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ProductDTO> getSearchProductsByIndex(int index, int mount, String searchName, String searchCategory, Float min, Float max) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select ProductID,c.Name as 'CateName',p.Name,Image_File_Name,Quantity,Price,TimeOfCreate,Status \n"
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Where (p.Name like N'%" + searchName + "%' \n"
                    + "AND (p.Price BETWEEN " + min + " AND " + max + ")\n"
                    + "AND (p.Status = 1) \n"
                    + "AND (p.CateID like '%" + searchCategory + "%') \n"
                    + "AND (p.Quantity > 0))\n"
                    + "Order By TimeOfCreate\n"
                    + "OFFSET " + (mount * (index - 1)) + " ROWS FETCH NEXT " + mount + " ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getNString("Name"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                dto.setCategory(rs.getString("CateName"));
                dto.setStatus(rs.getBoolean("Status"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;

    }

    public List<ProductDTO> getSearchProductsByIndexAdmin(int index, int mount, String searchName, String searchCategory, Float min, Float max) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select ProductID,c.Name as 'CateName',p.Name,Image_File_Name,Quantity,Price,TimeOfCreate,Status \n"
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Where (p.Name like N'%" + searchName + "%' \n"
                    + "AND (p.Price BETWEEN " + min + " AND " + max + ")\n"
                    + "AND (p.CateID like '%" + searchCategory + "%')) \n"
                    + "Order By TimeOfCreate\n"
                    + "OFFSET " + (mount * (index - 1)) + " ROWS FETCH NEXT " + mount + " ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getNString("Name"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setCategory(rs.getString("CateName"));
                dto.setStatus(rs.getBoolean("Status"));
                list.add(dto);
            }

        } finally {
            closeConnection();
        }
        return list;

    }

    public int getTotalSearchPageProduct(int numEachPage, String searchName, String searchCategory, Float min, Float max) throws Exception {
        int result = 0;
        int tmp = 0;
        try {
            String sql = "Select COUNT(p.ProductID) \n"
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Where (p.Name like N'%" + searchName + "%' \n"
                    + "AND (p.Price  BETWEEN " + min + " AND " + max + ")\n"
                    + "AND (p.Status = 1) AND (p.Quantity > 0) \n"
                    + "AND (p.CateID like '%" + searchCategory + "%'))\n";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                tmp = rs.getInt(1);
            }
            result = tmp / numEachPage;
            if (tmp % numEachPage != 0) {
                result++;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalSearchPageProductAdmin(int numEachPage, String searchName, String searchCategory, Float min, Float max) throws Exception {
        int result = 0;
        int tmp = 0;
        try {
            String sql = "Select COUNT(p.ProductID) \n"
                    + "From tblProduct p join tblCategory c on p.CateID = c.CateID \n"
                    + "Where (p.Name like N'%" + searchName + "%' \n"
                    + "AND (p.Price  BETWEEN " + min + " AND " + max + ")\n"
                    + "AND (p.CateID like '%" + searchCategory + "%'))\n";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                tmp = rs.getInt(1);
            }
            result = tmp / numEachPage;
            if (tmp % numEachPage != 0) {
                result++;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
