/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author tanta
 */
public class ProductDTO implements Serializable {

    private String id, name, category;
    private int quantity;
    private String image_File_Name;
    private float price;
    private boolean status;
    private LocalDateTime timeOfCreate;

    public ProductDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage_File_Name() {
        return image_File_Name;
    }

    public void setImage_File_Name(String image_File_Name) {
        this.image_File_Name = image_File_Name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
