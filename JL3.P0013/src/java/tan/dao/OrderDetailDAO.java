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
import tan.dtos.OrderDetailDTO;
import tan.dtos.ProductDTO;

/**
 *
 * @author tanta
 */
public class OrderDetailDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

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

    public boolean insertOrderDetail(OrderDetailDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblOrderDetail(OrderDetailID, OrderID, Quantity, ProductID, Price)"
                    + " values(?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getOrderDetailID());
            preStm.setString(2, dto.getOrderID());
            preStm.setInt(3, dto.getQuantity());
            preStm.setString(4, dto.getProductID());
            preStm.setFloat(5, dto.getPrice());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ProductDTO> getProductInOrderHistory(String orderID) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select o.ProductID,p.Name, o.Quantity , p.Price, p.Image_File_Name\n"
                    + "From tblOrderDetail o join tblProduct p on o.ProductID = p.ProductID \n"
                    + "Where OrderID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, orderID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getString("Name"));
                dto.setQuantity(rs.getInt("Quantity"));
                dto.setPrice(rs.getFloat("Price"));
                dto.setImage_File_Name(rs.getString("Image_File_Name"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ProductDTO> getRecomendProductsByID(String productID) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select TOP(3) od.ProductID,Sum (od.Quantity) AS TotalBuy ,\n"
                    + "(Select Name From tblProduct Where ProductID = od.ProductID) as 'Name',\n"
                    + "(Select Price From tblProduct Where ProductID = od.ProductID) as 'Price'\n"
                    + "From tblOrderDetail od join tblProduct p on od.ProductID = p.ProductID\n"
                    + "Where OrderID In(Select OrderID From tblOrderDetail Where ProductID like ?) \n"
                    + "AND (od.ProductID != ?) \n"
                    + "AND (p.Quantity > 0 )\n"
                    + "Group By od.ProductID \n"
                    + "Having (Sum (od.Quantity) > 1)\n"
                    + "Order By TotalBuy Desc";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, productID);
            preStm.setString(2, productID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getString("Name"));
                dto.setPrice(rs.getFloat("Price"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ProductDTO> getRecomendProductsByUserID(String userID) throws Exception {
        List<ProductDTO> list;
        try {
            String sql = "Select TOP(3) od.ProductID,Sum (od.Quantity) AS TotalBuy ,\n"
                    + "(Select Name From tblProduct Where ProductID = od.ProductID) as 'Name',\n"
                    + "(Select Price From tblProduct Where ProductID = od.ProductID) as 'Price'\n"
                    + "From tblOrderDetail od join tblProduct p on od.ProductID = p.ProductID\n"
                    + "Where OrderID In (Select OrderID From tblOrder Where UserID like ?) \n"
                    + "AND (p.Quantity > 0 )\n"
                    + "Group By od.ProductID \n"
                    + "Having (Sum (od.Quantity) > 1)\n"
                    + "Order By TotalBuy Desc";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setId(rs.getString("ProductID"));
                dto.setName(rs.getString("Name"));
                dto.setPrice(rs.getFloat("Price"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
