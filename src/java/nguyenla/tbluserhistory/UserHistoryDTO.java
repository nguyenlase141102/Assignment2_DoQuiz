/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tbluserhistory;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class UserHistoryDTO implements Serializable {

    private String courseName, userName, date, status;
    private int score;
    private String idHistory;
    
    public UserHistoryDTO() {
    }

    public UserHistoryDTO(String courseName, String userName, String date, String status, int score) {
        this.courseName = courseName;
        this.userName = userName;
        this.date = date;
        this.status = status;
        this.score = score;
    }

    public UserHistoryDTO(String courseName, String userName, String date, String status, int score, String idHistory) {
        this.courseName = courseName;
        this.userName = userName;
        this.date = date;
        this.status = status;
        this.score = score;
        this.idHistory = idHistory;
    }

    public String getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(String idHistory) {
        this.idHistory = idHistory;
    }

    
    
    
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
