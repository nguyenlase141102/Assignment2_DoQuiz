/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblanswercontent;

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
public class AnswercontentDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean addQuestionContent(AnswercontentDTO q) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into answerContentTBL values(?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, q.getIdAnswerContent());
                ps.setString(2, q.getContentQuestion());
                ps.setString(3, q.getIdQuestion());
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

    public List<AnswercontentDTO> getAllContentAnswer() throws SQLException, NamingException {
        try {
            List<AnswercontentDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select content_question,idQuestion from answerContentTBL ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String idQuestion = rs.getString(2);
                    AnswercontentDTO ans = new AnswercontentDTO(content, idQuestion);
                    list.add(ans);
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

    public List<AnswercontentDTO> getAllContentAnswerByID(String idUpdate) throws SQLException, NamingException {
        try {
            List<AnswercontentDTO> listSearch = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select content_question,idQuestion,idAnswerContent from answerContentTBL where idQuestion = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, idUpdate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String idQuestion = rs.getString(2);
                    String idAnswer = rs.getString(3);
                    AnswercontentDTO ans = new AnswercontentDTO(content, idQuestion, idAnswer, false);
                    listSearch.add(ans);
                }
                return listSearch;
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
    List<AnswercontentDTO> listSearch2;

    public List<AnswercontentDTO> getAllContentAnswerByID2(String idUpdate) throws SQLException, NamingException {
        try {
            
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select content_question,idQuestion,idAnswerContent from answerContentTBL where idQuestion = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, idUpdate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String idQuestion = rs.getString(2);
                    String idAnswer = rs.getString(3);
                    AnswercontentDTO ans = new AnswercontentDTO(content, idQuestion, idAnswer, false);
                    listSearch2.add(ans);
                }
                return listSearch2;
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

    public boolean updateContentQuestion(String idAns, String option) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update answerContentTBL set content_question = ? where idAnswerContent = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, option);
                ps.setString(2, idAns);
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
