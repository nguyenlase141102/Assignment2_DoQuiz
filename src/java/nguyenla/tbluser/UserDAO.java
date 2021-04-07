/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tbluser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class UserDAO implements Serializable{

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public String checkLogin(String userEmail, String userPassword) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select userEmail from userTBL where userEmail = ? and userPassword = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                ps.setString(2, userPassword);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getString(1);
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
        return "";
    }
       
       public boolean registrationUser (UserDTO u) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into userTBL values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1,u.getUserEmail());
                ps.setString(2,u.getUserName());
                ps.setString(3,u.getUserPassword());
                ps.setString(4,u.getUserRole());
                ps.setString(5,u.getUserStatus());
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
}
