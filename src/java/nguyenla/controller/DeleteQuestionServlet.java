/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nguyenla.tblanswercontent.AnswercontentDAO;
import nguyenla.tblanswercontent.AnswercontentDTO;
import nguyenla.tblquestion.QuestionDAO;
import nguyenla.tblquestion.QuestionDTO;
import nguyenla.tblsubject.SubjectDAO;

/**
 *
 * @author ANH NGUYEN
 */
public class DeleteQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String QUIZPAGE = "QuestionSubject.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = QUIZPAGE;
            /* TODO output your page here. You may use following sample code. */
            QuestionDAO questionDAO = new QuestionDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            AnswercontentDAO answerDAO = new AnswercontentDAO();
            String courseName = request.getParameter("courseName").trim();
            List<QuestionDTO> listQuestion= new ArrayList<>();
            List<AnswercontentDTO> listContentAnswer = new ArrayList<>();

            String idDelete = request.getParameter("idHidden").trim();
            if (idDelete != null) {
                if (questionDAO.deleteQuestion(idDelete)) {
                    String idSubject = subjectDAO.getIdSubject(courseName).trim();
                    if (courseName.equalsIgnoreCase("None")) {
                        listQuestion = questionDAO.getAllQuestion();
                        listContentAnswer = answerDAO.getAllContentAnswer();
                    } else {
                        listQuestion= questionDAO.getQuestionBySubject(idSubject);
                        listContentAnswer = answerDAO.getAllContentAnswer();
                    }
                }
            }

            request.setAttribute("listQuestionSubject", listQuestion);
            request.setAttribute("listContentAnswer", listContentAnswer );
            request.setAttribute("courseName", courseName);
            request.setAttribute("choiceAction", "deleteQuestion");
            request.setAttribute("deleteMessage","Delete question success ");
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
            Logger.getLogger(DeleteQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DeleteQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DeleteQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
