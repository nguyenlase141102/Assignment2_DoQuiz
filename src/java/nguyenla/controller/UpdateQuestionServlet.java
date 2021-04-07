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
import nguyenla.valid.Validation;

/**
 *
 * @author ANH NGUYEN
 */
public class UpdateQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String UPDATEPAGEERROR = "UpdateQuestion.jsp";
    private final String UPDATEQUESTION = "QuestionSubject.jsp";
    private String url;
    private String typeError;
    boolean update;
    private boolean checkAnswerCorrect;
    private boolean checkOption;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            update = false;
            checkAnswerCorrect = false;
            url = UPDATEPAGEERROR;
            checkOption = true;
            /* TODO output your page here. You may use following sample code. */
            List<AnswercontentDTO> listContentAnswer = new ArrayList<>();
            List<AnswercontentDTO> listSaveContent = new ArrayList<>();
            List<QuestionDTO> listQuestion = new ArrayList<>();
            List<AnswercontentDTO> listLoadContentAnswer = new ArrayList<>();

            QuestionDAO questionDAO = new QuestionDAO();
            AnswercontentDAO answerDAO = new AnswercontentDAO();
            SubjectDAO subjectDAO = new SubjectDAO();

            HttpSession session = request.getSession();
            listContentAnswer = (List<AnswercontentDTO>) session.getAttribute("listContent");
            String contentUpdate = request.getParameter("txtContentUpdate").trim();
            String option1 = request.getParameter("option1").trim();
            String option2 = request.getParameter("option2").trim();
            String option3 = request.getParameter("option3").trim();
            String option4 = request.getParameter("option4").trim();
            String correctUpdate = request.getParameter("txtCorrectUpdate").trim();
            String idUpdate = request.getParameter("idUpdate").trim();
            String statusUpdate = request.getParameter("status").trim();
            String courseNameChange = request.getParameter("listNameSubject").trim();
            String courseNameOriginal = request.getParameter("courseName").trim();
            String changeIdSubject = subjectDAO.getIdSubject(courseNameChange).trim();
            String idSubjectOriginal = subjectDAO.getIdSubject(courseNameOriginal).trim();

            if (!Validation.checkEmpty(contentUpdate)) {
                typeError = "Error : Content Question not empty !!";
            } else if (!Validation.checkEmpty(correctUpdate)) {
                typeError = "Error : Answer correct not empty !!";
            } else if (!Validation.checkEmpty(option1)) {
                typeError = "Error : Option 1 not empty !!";
            } else if (!Validation.checkEmpty(option2)) {
                typeError = "Error : Option 2 not empty !!";
            } else if (!Validation.checkEmpty(option3)) {
                typeError = "Error : Option 3 not empty !!";
            } else if (!Validation.checkEmpty(option4)) {
                typeError = "Error : Option 4 not empty !!";
            } else if (!Validation.checkEmpty(statusUpdate)) {
                typeError = "Error : status not empty !!";
            } else if (!Validation.checkSpecialCharacter(statusUpdate)) {
                typeError = "Error : status doesn't contain special characters";
            } else {
                AnswercontentDTO answOption1 = new AnswercontentDTO(listContentAnswer.get(0).getIdAnswerContent(), option1, idUpdate);
                AnswercontentDTO answOption2 = new AnswercontentDTO(listContentAnswer.get(1).getIdAnswerContent(), option2, idUpdate);
                AnswercontentDTO answOption3 = new AnswercontentDTO(listContentAnswer.get(2).getIdAnswerContent(), option3, idUpdate);
                AnswercontentDTO answOption4 = new AnswercontentDTO(listContentAnswer.get(3).getIdAnswerContent(), option4, idUpdate);
                listSaveContent.add(answOption1);
                listSaveContent.add(answOption2);
                listSaveContent.add(answOption3);
                listSaveContent.add(answOption4);

                if (listSaveContent != null) {
                    for (int i = 0; i < listSaveContent.size(); i++) {
                        String option = listSaveContent.get(i).getContentQuestion();
                        if (!correctUpdate.equalsIgnoreCase(option)) {
                            typeError = "Not valid answer correct";
                        } else {
                            checkAnswerCorrect = true;
                            break;
                        }
                    }

                    for (int i = 0; i < listSaveContent.size(); i++) {
                        String optionAnswer = listSaveContent.get(i).getContentQuestion();
                        for (int j = i + 1; j < listSaveContent.size(); j++) {
                            String optionNext = listSaveContent.get(j).getContentQuestion();
                            if (optionAnswer.equals(optionNext)) {
                                typeError = "Duplicate answer";
                                checkOption = false;
                                break;
                            }
                        }
                    }
                }

                if (checkAnswerCorrect == true && checkOption == true) {
                    if (questionDAO.updateQuestion(contentUpdate, correctUpdate, idUpdate, statusUpdate, changeIdSubject)) {
                        for (int i = 0; i < listSaveContent.size(); i++) {
                            String idAnswer = listSaveContent.get(i).getIdAnswerContent();
                            String option = listSaveContent.get(i).getContentQuestion();
                            answerDAO.updateContentQuestion(idAnswer, option);
                        }
                        update = true;
                        url = UPDATEQUESTION;
                    } else {
                        typeError = "Cant update ";
                    }
                }

            }
            if (courseNameOriginal.equalsIgnoreCase("None")) {
                listQuestion = questionDAO.getAllQuestion();
                listLoadContentAnswer = answerDAO.getAllContentAnswer();
            } else {
                listQuestion = questionDAO.getQuestionBySubject(idSubjectOriginal);
                listLoadContentAnswer = answerDAO.getAllContentAnswer();
            }

            if (update == true) {
                request.setAttribute("listQuestionSubject", listQuestion);
                request.setAttribute("listContentAnswer", listLoadContentAnswer);
                request.setAttribute("updateMessage", "Update question success ");
                RequestDispatcher dis = request.getRequestDispatcher(url);
                dis.forward(request, response);
            } else {
                request.setAttribute("contentQuestionUpdate", contentUpdate);
                request.setAttribute("option1", option1);
                request.setAttribute("option2", option2);
                request.setAttribute("option3", option3);
                request.setAttribute("option4", option4);
                request.setAttribute("correctQuestionUpdate", correctUpdate);
                request.setAttribute("errorQuestionUpdate", typeError);
                request.setAttribute("statusQuestionUpdate", statusUpdate);

                RequestDispatcher dis = request.getRequestDispatcher(url);
                dis.forward(request, response);
            }
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
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
