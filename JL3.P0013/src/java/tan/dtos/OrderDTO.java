/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author tanta
 */
public class OrderDTO implements Serializable {

    private String orderID, userID;
    private float total;
    private LocalDateTime timeOfCreate;

    public OrderDTO() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDateTime getTimeOfCreate() {
        return timeOfCreate;
    }

    public String getStringTimeOfCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.timeOfCreate.format(formatter);
    }

    public void setTimeOfCreate(LocalDateTime timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }

    public void setTimeOfCreate(String timeOfCreate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timeOfCreate = LocalDateTime.parse(timeOfCreate, formatter);
    }

}
