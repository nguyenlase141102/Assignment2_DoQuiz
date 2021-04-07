/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.tblanswercontent.AnswercontentDAO;
import nguyenla.tblanswercontent.AnswercontentDTO;
import nguyenla.tblquestion.QuestionDAO;
import nguyenla.tblquestion.QuestionDTO;
import nguyenla.tbluserhistory.UserHistoryDAO;
import nguyenla.tbluserhistory.UserHistoryDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class StudentHistoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String HOMEPAGE = "LoadHomeServlet";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = HOMEPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String courseName = request.getParameter("course").trim();
            String scoreString = request.getParameter("score").trim();
            int score = Integer.parseInt(scoreString);
            UserHistoryDAO userDAO = new UserHistoryDAO();
            QuestionDAO questionDAO = new QuestionDAO();
//            HistoryDetailsDAO hisDAO = new HistoryDetailsDAO();
            AnswercontentDAO answerDAO = new AnswercontentDAO();
            String name = request.getParameter("userName").trim();
            String date = request.getParameter("date").trim();
            String status;
            if (score > 5) {
                status = "passed";
            } else {
                status = "failed";
            }

            Map<String, List<AnswercontentDTO>> mapStudentAnswer = new HashMap<>();
            mapStudentAnswer = (Map<String, List<AnswercontentDTO>>) session.getAttribute("mapStudent");

            int total = (int) session.getAttribute("totalQuestion");
            String currentDate = (String) session.getAttribute("currentDate");
            List<QuestionDTO> listSaveQuiz;
            listSaveQuiz = (List<QuestionDTO>) session.getAttribute("listSaveQuiz");

            String userName = (String) session.getAttribute("name");

            request.setAttribute("score", score);
            request.setAttribute("totalQuestion", total);
            request.setAttribute("courseName", courseName);
            request.setAttribute("currentDate", currentDate);
            request.setAttribute("userName", userName);

            Random dice = new Random();
            String idHistory = dice.nextInt(1000000) + "";

            UserHistoryDTO newHistory = new UserHistoryDTO(courseName, name, date, status, score, idHistory);
            userDAO.addHistory(newHistory);

            for (int i = 0; i < listSaveQuiz.size(); i++) {
                String idQuestion = listSaveQuiz.get(i).getIdQuestion();

//                hisDAO.addQuestion(userName, idQuestion,"null", idHistory, courseName);
            }

            for (Map.Entry<String, List<AnswercontentDTO>> mapS : mapStudentAnswer.entrySet()) {
                for (int i = 0; i < mapS.getValue().size(); i++) {
                    if (mapS.getValue().get(i).isStatus() == true) {
                        String studentAnswer = mapS.getValue().get(i).getContentQuestion().trim();
                        String idQuestion = mapS.getValue().get(i).getIdQuestion();
//                        hisDAO.updateDetails(idQuestion, studentAnswer);

                    }
                }
            }
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(StudentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(StudentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(StudentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(StudentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
