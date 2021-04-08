/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author tanta
 */
public class HistoryOrderDTO implements Serializable {

    private String orderDetailID;
    private String orderID;
    private String productName;
    private String image_File_Name;
    private int quantity;
    private float price;

    public HistoryOrderDTO() {
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String OrderDetailID) {
        this.orderDetailID = OrderDetailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String OrderID) {
        this.orderID = OrderID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage_File_Name() {
        return image_File_Name;
    }

    public void setImage_File_Name(String image_File_Name) {
        this.image_File_Name = image_File_Name;
    }

}
