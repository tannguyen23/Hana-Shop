/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dtos;

import java.io.Serializable;

/**
 *
 * @author tanta
 */
public class UserDTO implements Serializable {

    private String userID, password, name, email, role;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(String userID, String name, String email, String role, boolean status) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
