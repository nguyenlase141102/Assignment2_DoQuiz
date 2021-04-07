/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblquestion;

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
public class QuestionDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean addQuestion(QuestionDTO q) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into questionTBL values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, q.getIdQuestion());
                ps.setString(2, q.getQuestionContent());
                ps.setString(3, q.getAnswerCorrect());
                ps.setString(4, q.getDate());
                ps.setString(5, q.getSubjectId());
                ps.setString(6, q.getStatus());
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

    public List<QuestionDTO> getQuestionBySubject(String id) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select question_content,answer_correct,idQuestion,status  from questionTBL where subjectID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getNString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    list.add(ques);
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

    public String checkIdQuestion(String idQuestion) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select answer_correct from questionTBL where idQuestion = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, idQuestion);
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

    public List<QuestionDTO> getAllQuestion() throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select question_content,answer_correct,idQuestion,status  from questionTBL";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getNString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    list.add(ques);
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

    public String checkQuestion(String question) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select idQuestion from questionTBL where question_content = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, question);
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
        return null;
    }

    public boolean deleteQuestion(String id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update questionTBL set status = 'deactive' where idQuestion = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
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

    public boolean updateQuestion(String content, String correct, String id, String status, String subID) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update questionTBL set question_content = ?,answer_correct = ?,status = ?,subjectID = ? where idQuestion = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, content);
                ps.setString(2, correct);
                ps.setString(3, status);
                ps.setString(4, subID);
                ps.setString(5, id);
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

    public int countNotSubject(String contentQuestion, String status) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select count(*) from questionTBL where question_content like ? and status = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + contentQuestion + "%");
                ps.setString(2, status);
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

    public String getContentQuestion(String idQuestion) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select question_content from questionTBL where idQuestion = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idQuestion);
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
        return null;
    }


    public List<QuestionDTO> searchNotSubject(String questionContent, String statusQuestion, int index) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (Select ROW_NUMBER() over(order by question_content desc) as r, * from questionTBL  where question_content like ? and status = ?)\n"
                        + "select question_content,answer_correct,idQuestion,status from x where r between ?*10-9 and ?*10";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + questionContent + "%");
                ps.setString(2, statusQuestion);
                ps.setInt(3, index);
                ps.setInt(4, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    list.add(ques);
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

    public int countWithSubject(String subjectID, String status) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select count(*)/2 from randomQuizTBL where subjectID = ? and status = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                ps.setString(2, status);
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

    public List<QuestionDTO> searchWithSubject(String subjectId, String statusQuestion, int index) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (Select ROW_NUMBER() over(order by question_content desc) as r, * from questionTBL  where subjectID = ? and status = ?)\n"
                        + "select question_content,answer_correct,idQuestion,status from x where r between ?*10-9 and ?*10";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, statusQuestion);
                ps.setInt(3, index);
                ps.setInt(4, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    list.add(ques);
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

    public int countAll(String contentQuestion, String status) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select count(*) from questionTBL where question_content like ? and status = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + contentQuestion + "%");
                ps.setString(2, status);
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

    public List<QuestionDTO> questionQuiz(String idSub, int index, String statusQuestion) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (Select TOP 50 PERCENT  ROW_NUMBER() over(order by (select NULL)) as r, * from randomQuizTBL  where subjectID = ? and status = ?)\n"
                        + "                        select question_content,answer_correct,idQuestion,status from x where r between ?*5-4 and ?*5";
                ps = con.prepareStatement(sql);
                ps.setString(1, idSub);
                ps.setString(2, statusQuestion);
                ps.setInt(3, index);
                ps.setInt(4, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    list.add(ques);
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

    public List<QuestionDTO> saveQuiz(String idSub, String statusQuestion) throws SQLException, NamingException {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select TOP 50 PERCENT question_content,answer_correct,idQuestion,status from randomQuizTBL where subjectID = ? and status = ? \n"
                        + "order by (select NULL)";
                ps = con.prepareStatement(sql);
                ps.setString(1, idSub);
                ps.setString(2, "active");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    list.add(ques);
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

    public List<QuestionDTO> listRandom(String subjectId, String statusRandom) throws SQLException, NamingException {
        try {
            List<QuestionDTO> listRandom = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select question_content,answer_correct,idQuestion,status from questionTBL where subjectID = ?  and status = ? \n"
                        + "order by newid() desc";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, statusRandom);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String content = rs.getString(1);
                    String answer = rs.getString(2);
                    String idQuestion = rs.getString(3);
                    String status = rs.getString(4);
                    QuestionDTO ques = new QuestionDTO(idQuestion, content, answer, status);
                    listRandom.add(ques);
                }
                return listRandom;
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

    public boolean addRandomQuestion(QuestionDTO q) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into randomQuizTBL values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, q.getQuestionContent());
                ps.setString(2, q.getAnswerCorrect());
                ps.setString(3, q.getIdQuestion());
                ps.setString(4, q.getStatus());
                ps.setString(5, q.getSubjectId());
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

    public boolean dropRandomTable() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "DELETE FROM randomQuizTBL";
                ps = con.prepareStatement(sql);
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
