/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import nguyenla.tblsubject.SubjectDAO;
import nguyenla.tblsubject.SubjectDTO;
import nguyenla.valid.Validation;

/**
 *
 * @author ANH NGUYEN
 */
public class CreateQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CREATEPAGE = "AddQuestion.jsp";
    private final String HOMEPAGE = "LoadHomeServlet";
    private String url;
    private String typeError;
    private String success;
    private boolean checkCreate;
    private boolean checkAnswerCorrect;
    private boolean checkOption;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        checkCreate = false;
        checkAnswerCorrect = false;
        checkOption = true;
        try (PrintWriter out = response.getWriter()) {
            List<SubjectDTO> listNameSubject = new ArrayList<>();
            SubjectDAO subjectDAO = new SubjectDAO();
            listNameSubject = subjectDAO.getAllNameSubject();
            QuestionDAO questionDAO = new QuestionDAO();
            AnswercontentDAO answerDAO = new AnswercontentDAO();

            HttpSession session = request.getSession();
            url = CREATEPAGE;
            /* TODO output your page here. You may use following sample code. */

            String content = request.getParameter("txtContent").trim();
            String option1 = request.getParameter("option1").trim();
            String option2 = request.getParameter("option2").trim();
            String option3 = request.getParameter("option3").trim();
            String option4 = request.getParameter("option4").trim();
            String idQuestion = request.getParameter("idQuestion").trim();
            String answerCorrect = request.getParameter("txtAnswerCorrect");
            String subjectName = request.getParameter("listNameSubject");

            String checkId = questionDAO.checkIdQuestion(idQuestion);
            String idSubjet = subjectDAO.getIdSubject(subjectName);
            List<String> listOptionQuestion = new ArrayList<>();
            listOptionQuestion.add(option1);
            listOptionQuestion.add(option2);
            listOptionQuestion.add(option3);
            listOptionQuestion.add(option4);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = new Date(System.currentTimeMillis());
            String currentDate = formatter.format(dateTime);

            Random dice = new Random();

            if (!Validation.checkSpecialCharacter(idQuestion)) {
                typeError = "Error : Id Question doesn't contain special characters !!";
            } else if (!checkId.equals("")) {
                typeError = "Error : Duplicated ID !!";
            } else if (questionDAO.checkQuestion(content) != null) {
                typeError = "Question is already";
            } else {    
                
                if (listOptionQuestion != null) {
                    
                    for (int i = 0; i < listOptionQuestion.size(); i++) {
                        String option = listOptionQuestion.get(i);
                        if (!answerCorrect.equalsIgnoreCase(option)) {
                            typeError = "Not valid answer correct";
                        } else {
                            checkAnswerCorrect = true;
                            break;
                        }
                    }

                    for (int i = 0; i < listOptionQuestion.size(); i++) {
                        String optionAnswer = listOptionQuestion.get(i);                        
                        for(int j = i + 1  ; j < listOptionQuestion.size();j++){
                            String optionNext = listOptionQuestion.get(j);
                            if(optionAnswer.equals(optionNext)){
                                typeError = "Duplicate answer";
                                checkOption = false;
                                break;
                            }
                        }
                    }
                }

                if (checkAnswerCorrect == true && checkOption == true) {
                    QuestionDTO newQuestion = new QuestionDTO(idQuestion, content, answerCorrect, currentDate, idSubjet, "active");
                    if (questionDAO.addQuestion(newQuestion)) {

                        for (int i = 0; i < listOptionQuestion.size(); i++) {
                            String answerID = dice.nextInt(1000000) + "";
                            String contentAnswer = listOptionQuestion.get(i).toString();
                            AnswercontentDTO newContent = new AnswercontentDTO(answerID, contentAnswer, idQuestion);
                            if (answerDAO.addQuestionContent(newContent)) {
                                checkCreate = true;
                                success = "Create question success";

                            } else {
                                typeError = "Add Question content fail";
                            }
                        }
                    } else {
                        typeError = "Create Question Fail";
                    }
                } 
            }

            if (checkCreate == true) {
                request.setAttribute("successCreate", success);
                RequestDispatcher dis = request.getRequestDispatcher(url);
                dis.forward(request, response);
            } else {
                request.setAttribute("errorCreate", typeError);
                request.setAttribute("content", content);
                request.setAttribute("option1", option1);
                request.setAttribute("option2", option2);
                request.setAttribute("option3", option3);
                request.setAttribute("option4", option4);
                request.setAttribute("correct", answerCorrect);
                request.setAttribute("idQuestion", idQuestion);
                session.setAttribute("listSubject", listNameSubject);
                request.setAttribute("successCreate", success);
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
            Logger.getLogger(SearchHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CreateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
