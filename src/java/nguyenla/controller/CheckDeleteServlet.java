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
import javax.servlet.http.HttpSession;
import nguyenla.tblanswercontent.AnswercontentDAO;
import nguyenla.tblanswercontent.AnswercontentDTO;
import nguyenla.tblquestion.QuestionDAO;
import nguyenla.tblquestion.QuestionDTO;
import nguyenla.tblsubject.SubjectDAO;

/**
 *
 * @author ANH NGUYEN
 */
public class CheckDeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String DELETESERVLET = "DeleteQuestionServlet";
    private final String FAIL = "QuestionSubject.jsp";
    private String url;
    private String typeError;
    boolean checkDelete;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            checkDelete = true;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            QuestionDAO questionDAO = new QuestionDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            AnswercontentDAO answerDAO = new AnswercontentDAO();
            List<QuestionDTO> listSaveQuiz = new ArrayList<>();
            String idDelete = request.getParameter("idHidden").trim();
            listSaveQuiz = (List<QuestionDTO>) session.getAttribute("listSaveQuiz");
            List<QuestionDTO> listQuestion = new ArrayList<>();
            List<AnswercontentDTO> listContentAnswer = new ArrayList<>();
            String courseName = request.getParameter("courseName").trim();

            if (listSaveQuiz != null) {
                for (int i = 0; i < listSaveQuiz.size(); i++) {
                    String idQuestion = listSaveQuiz.get(i).getIdQuestion().trim();
                    if (idQuestion.equals(idDelete)) {
                        typeError = "This question doing quiz";
                        request.setAttribute("errorDelete", typeError);
                        checkDelete = false;
                        break;
                    }

                }
            }

            
            if (checkDelete == true) {
                url = DELETESERVLET;
            } else {
                url = FAIL;
            }

            String idSubject = subjectDAO.getIdSubject(courseName).trim();
            if (courseName.equalsIgnoreCase("None")) {
                listQuestion = questionDAO.getAllQuestion();
                listContentAnswer = answerDAO.getAllContentAnswer();
            } else {
                listQuestion = questionDAO.getQuestionBySubject(idSubject);
                listContentAnswer = answerDAO.getAllContentAnswer();
            }

            request.setAttribute("listQuestionSubject", listQuestion);
            request.setAttribute("listContentAnswer", listContentAnswer);
            request.setAttribute("courseName", courseName);
            request.setAttribute("choiceAction", "deleteQuestion");
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
            Logger.getLogger(CheckDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CheckDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
