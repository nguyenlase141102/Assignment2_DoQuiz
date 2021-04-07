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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
public class SearchQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String HOMEPAGE = "HomePage.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = HOMEPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String statusQuestion = request.getParameter("statusQuestion").trim();
            String nameSubject = request.getParameter("listNameSubject").trim();
            String questionSearch = request.getParameter("txtSearch");
            String indexString = request.getParameter("index");
            QuestionDAO quesDAO = new QuestionDAO();
            AnswercontentDAO answDAO = new AnswercontentDAO();
            SubjectDAO subDAO = new SubjectDAO();
            String idSubject = subDAO.getIdSubject(nameSubject);
            List<QuestionDTO> listQuestion = new ArrayList<>();
            List<AnswercontentDTO> listContent = new ArrayList<>();

            Map<QuestionDTO, List<AnswercontentDTO>> mapQuestion = new HashMap<>();
            int index;
            if (indexString == null) {
                index = 1;
            } else {
                index = Integer.parseInt(indexString);
            }

            int pageSize = 10;
            int countPage = 0;
            if (nameSubject.equals("None") && questionSearch.equals("")) {
                countPage = quesDAO.countNotSubject(questionSearch, statusQuestion);

            } else if (!nameSubject.equals("None") && !questionSearch.equals("")) {

            } else {
                countPage = quesDAO.countWithSubject(idSubject, statusQuestion);

            }

            int endPage = countPage / pageSize;
            if (countPage % pageSize != 0) {
                endPage++;
            }

            if (nameSubject.equals("None")) {
                listQuestion = quesDAO.searchNotSubject(questionSearch, statusQuestion, index);
            } else {
                listQuestion = quesDAO.searchWithSubject(idSubject, statusQuestion, index);

            }

            for (int i = 0; i < listQuestion.size(); i++) {
                String contentQuestion = listQuestion.get(i).getQuestionContent();
                String idQuestion = listQuestion.get(i).getIdQuestion();
                String answerCorrect = listQuestion.get(i).getAnswerCorrect();
                QuestionDTO question = new QuestionDTO(contentQuestion, answerCorrect);
                listContent = answDAO.getAllContentAnswerByID(idQuestion);
                mapQuestion.put(question, listContent);
            }

            //Sort Map
            Map<QuestionDTO,List<AnswercontentDTO>> treeMap = new TreeMap<QuestionDTO,List<AnswercontentDTO>>(
            new Comparator<QuestionDTO>(){
                @Override
                public int compare(QuestionDTO o1, QuestionDTO o2) {
                    return o2.getQuestionContent().compareTo(o1.getQuestionContent());
                }
                
            });
                    
           treeMap.putAll(mapQuestion);

            request.setAttribute("endPage", endPage);
            request.setAttribute("clickSearch", "search");
            request.setAttribute("listQuestionSearch", listQuestion);
            session.setAttribute("statusQuestion", statusQuestion);
            session.setAttribute("nameSubject", nameSubject);
            session.setAttribute("questionSearch", questionSearch);
            session.setAttribute("map",treeMap);
            request.setAttribute("clickSearch", "search");
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
            Logger.getLogger(SearchQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
