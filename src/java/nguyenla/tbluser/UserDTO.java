/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tbluser;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class UserDTO implements Serializable {

    private String userEmail;
    private String userName;
    private String userPassword;
    private String userRole;
    private String userStatus;

    public UserDTO() {
    }

    public UserDTO(String userEmail, String userName, String userPassword, String userRole, String userStatus) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public UserDTO(String userEmail, String userRole) {
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}
