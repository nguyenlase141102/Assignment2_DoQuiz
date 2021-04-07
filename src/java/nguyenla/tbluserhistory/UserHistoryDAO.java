/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tbluserhistory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class UserHistoryDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean addHistory(UserHistoryDTO q) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into studentHistoryTBL values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, q.getIdHistory());
                ps.setString(2, q.getCourseName());
                ps.setInt(3, q.getScore());
                ps.setString(4, q.getDate());
                ps.setString(5, q.getStatus());
                ps.setString(6, q.getUserName());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }

        }
        return false;
    }

    public List<UserHistoryDTO> getUserHistory(String userEmail) throws SQLException, NamingException {
        try {
            List<UserHistoryDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select courseName,score,date,status,userEmail,idHistory  from studentHistoryTBL where userEmail = ?  ";
                ps = con.prepareStatement(sql);
                ps.setString(1,userEmail);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String course = rs.getString(1);
                    int score = rs.getInt(2);
                    String date = rs.getString(3);
                    String status = rs.getNString(4);
                    String userName = rs.getString(5);
                    String idHistory = rs.getString(6);
                    UserHistoryDTO user = new UserHistoryDTO(course, userName, date, status, score,idHistory);
                    list.add(user);
                }
                return list;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public int countHistorySubject(String subjectName,String userEmail) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select count(*) from studentHistoryTBL where courseName like ? and userEmail = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + subjectName + "%");
                ps.setString(2,userEmail);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int count = rs.getInt(1);
                    return count;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

    public List<UserHistoryDTO> searchHistorySubject(String subjectName, int index,String userEmail) throws SQLException, NamingException {
        try {
            List<UserHistoryDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (Select ROW_NUMBER() over(order by date desc) as r, * from studentHistoryTBL  where courseName like ? and userEmail = ?)\n"
                        + "select courseName,score,date,status,userEmail from x where r between ?*5-4 and ?*5";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + subjectName + "%");
                ps.setString(2,userEmail);
                ps.setInt(3, index);
                ps.setInt(4, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String course = rs.getString(1);
                    int score = rs.getInt(2);
                    String date = rs.getString(3);
                    String status = rs.getNString(4);
                    String userName = rs.getString(5);
                    UserHistoryDTO user = new UserHistoryDTO(course, userName, date, status, score);
                    list.add(user);
                }
                return list;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }
}
