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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DoQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String QUIZPAGE = "DoQuiz.jsp";
    private final String RESULTSERVLET = "ResultQuizServlet";
    private final String HOMEPAGE = "HomePage.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = QUIZPAGE;

            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String courseName = request.getParameter("listNameSubject").trim();

            Map<String, List<AnswercontentDTO>> mapQuestion = new HashMap<>();
            Map<String, List<AnswercontentDTO>> mapStudentAnswer = new HashMap<>();
            mapStudentAnswer = (Map<String, List<AnswercontentDTO>>) session.getAttribute("mapStudent");
            if (mapStudentAnswer == null) {
                mapStudentAnswer = new HashMap<>();
            }
            String currentDate = null;
            List<QuestionDTO> listRandom = new ArrayList<>();
            List<QuestionDTO> listQuestion = new ArrayList<>();
            List<QuestionDTO> listStudentAnswer = new ArrayList<>();
            List<QuestionDTO> listSaveQuiz = new ArrayList<>();
            List<AnswercontentDTO> listContentAnswer = new ArrayList<>();
            SubjectDAO subjectDAO = new SubjectDAO();
            AnswercontentDAO answerDAO = new AnswercontentDAO();
            QuestionDAO questionDAO = new QuestionDAO();

            String idSubject = subjectDAO.getIdSubject(courseName);
            String btNext = request.getParameter("btNext");
            String btPrevious = request.getParameter("btPrevious");
            String indexString = request.getParameter("index");
            String indexTest = request.getParameter("indexTest");
            String countString = request.getParameter("lastCount");
            String indexSave = request.getParameter("index2");
            String finish = request.getParameter("finish");
            String name = request.getParameter("nameUser");
            String nextPage = request.getParameter("action");
            String timeCount = request.getParameter("myField");
            String quizRandom = request.getParameter("random");
            String randomCheck = (String) session.getAttribute("randomCheck");
            String checkQuiz = request.getParameter("checkTime");
            String timeQuiz = null;
            if (checkQuiz == null) {
                timeQuiz = timeCount == null ? subjectDAO.getTimeSubject(courseName) : timeCount;
            } else {
                timeQuiz = (String) session.getAttribute("timeQuiz");
            }
            int indexSaveAnswer = 0;
            int indexLoadQuestion = 0;
            int indexTestNext = 0;
            if (btNext == null  ) {
                indexSaveAnswer = indexSave == null ? 0 : Integer.parseInt(indexSave);
                indexLoadQuestion = indexString == null ? 1 : Integer.parseInt(indexString);
            } else if (btNext.equalsIgnoreCase("Next")) {
                indexTestNext = Integer.parseInt(indexSave) + 1;
                indexSaveAnswer = indexSave == null ? 0 : Integer.parseInt(indexSave);
                indexLoadQuestion = indexTestNext;;
            } else if (btPrevious.equalsIgnoreCase("Previous")){
                System.out.println("vao day");
                indexTestNext = Integer.parseInt(indexSave) - 1;
                indexSaveAnswer = indexSave == null ? 0 : Integer.parseInt(indexSave);
                indexLoadQuestion = indexTestNext;
            }

            int indexNumberQuestion = 0;
            int countNumberQuestion = 0;
            if (countString == null) {
            } else {
                countNumberQuestion = Integer.parseInt(countString);
                if (countNumberQuestion == 1) {
                    countNumberQuestion = 0;
                } else {
                    indexNumberQuestion = indexLoadQuestion - 1;
                    countNumberQuestion = indexNumberQuestion * 5;
                }
            }

            if (quizRandom == null) {
                if (randomCheck == null) {
                    questionDAO.dropRandomTable();
                    listRandom = questionDAO.listRandom(idSubject, "active");
                    for (int i = 0; i < listRandom.size(); i++) {
                        String question = listRandom.get(i).getQuestionContent();
                        String answerCorrect = listRandom.get(i).getAnswerCorrect();
                        String idQuestion = listRandom.get(i).getIdQuestion();
                        String status = listRandom.get(i).getStatus();
                        QuestionDTO questionRandom = new QuestionDTO(idQuestion, question, answerCorrect, idSubject, status);
                        questionDAO.addRandomQuestion(questionRandom);
                    }
                    session.setAttribute("randomCheck", "random");
                } else {
                    questionDAO.dropRandomTable();
                    listRandom = questionDAO.listRandom(idSubject, "active");
                    for (int i = 0; i < listRandom.size(); i++) {
                        String question = listRandom.get(i).getQuestionContent();
                        String answerCorrect = listRandom.get(i).getAnswerCorrect();
                        String idQuestion = listRandom.get(i).getIdQuestion();
                        String status = listRandom.get(i).getStatus();
                        QuestionDTO questionRandom = new QuestionDTO(idQuestion, question, answerCorrect, idSubject, status);
                        questionDAO.addRandomQuestion(questionRandom);
                    }
                    session.setAttribute("randomCheck", "random");
                }
            }

            if (finish == null) {
                listQuestion = questionDAO.questionQuiz(idSubject, indexLoadQuestion, "active");
                listStudentAnswer = questionDAO.questionQuiz(idSubject, indexSaveAnswer, "active");
                listSaveQuiz = questionDAO.saveQuiz(idSubject, "active");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateTime = new Date(System.currentTimeMillis());
                currentDate = formatter.format(dateTime);
            } else {
                url = RESULTSERVLET;
                listStudentAnswer = questionDAO.questionQuiz(idSubject, indexSaveAnswer, "active");
                listSaveQuiz = questionDAO.saveQuiz(idSubject, "active");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateTime = new Date(System.currentTimeMillis());
                currentDate = formatter.format(dateTime);

            }

            //Load question and content answer on quiz online 
            for (int i = 0; i < listQuestion.size(); i++) {
                String contentQuestion = listQuestion.get(i).getQuestionContent();
                String idQuestion = listQuestion.get(i).getIdQuestion();
                listContentAnswer = answerDAO.getAllContentAnswerByID(idQuestion);
                mapQuestion.put(contentQuestion, listContentAnswer);
            }

//            //Save student answers 
            if (nextPage == null) {
                listStudentAnswer = questionDAO.getQuestionBySubject(idSubject);
                for (int i = 0; i < listStudentAnswer.size(); i++) {
                    String contentQuestion = listStudentAnswer.get(i).getQuestionContent();
                    String idQuestion = listStudentAnswer.get(i).getIdQuestion().trim();
                    listContentAnswer = answerDAO.getAllContentAnswerByID(idQuestion);
                    mapStudentAnswer.put(idQuestion, listContentAnswer);
                }
            } else {
                for (int i = 0; i < listStudentAnswer.size(); i++) {
                    String contentQuestion = listStudentAnswer.get(i).getQuestionContent();
                    String idQuestion = listStudentAnswer.get(i).getIdQuestion().trim();
                    String studentAnswer = request.getParameter(idQuestion);
                    listContentAnswer = answerDAO.getAllContentAnswerByID(idQuestion);

                    if (studentAnswer == null) {
                        mapStudentAnswer.put(idQuestion, listContentAnswer);
                    } else {
                        for (int j = 0; j < listContentAnswer.size(); j++) {
                            String answer = listContentAnswer.get(j).getContentQuestion().trim();
                            if (studentAnswer.equals(answer)) {
                                listContentAnswer.get(j).setStatus(true);
                            }
                        }
                        mapStudentAnswer.put(idQuestion, listContentAnswer);
                    }
                }
            }

            //Paging on quiz online
            int pageSize = 5;
            int countPage = questionDAO.countWithSubject(idSubject, "active");
            int endPage = countPage / pageSize;
            if (countPage % pageSize != 0) {
                endPage++;
            }

            session.setAttribute("listSaveQuiz", listSaveQuiz);
            request.setAttribute("countPage", countNumberQuestion);
            session.setAttribute("courseName", courseName);
            request.setAttribute("endPage", endPage);
            session.setAttribute("map", mapQuestion);
            session.setAttribute("mapStudent", mapStudentAnswer);
            request.setAttribute("indexPage", indexLoadQuestion);
            session.setAttribute("timeQuiz", timeQuiz);
            session.setAttribute("timeCount", timeCount);
            session.setAttribute("currentDate", currentDate);
            session.setAttribute("name", name);
            session.setAttribute("totalQuestion", countPage);

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
            Logger.getLogger(DoQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DoQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DoQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DoQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
