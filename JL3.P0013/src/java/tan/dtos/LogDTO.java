/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import tan.dao.LogDAO;

/**
 *
 * @author tanta
 */
public class LogDTO implements Serializable {

    private String logID;
    private String userID;
    private String productID;
    private String action;
    private LocalDateTime timeOfCreate;

    public LogDTO() {
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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


    @Override
    public String toString() {
        return "LogDTO{" + "logID=" + logID + ", userID=" + userID + ", productID=" + productID + ", action=" + action + ", timeOfCreate=" + getStringTimeOfCreate() + '}';
    }

}
