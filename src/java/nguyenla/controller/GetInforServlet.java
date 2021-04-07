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

/**
 *
 * @author ANH NGUYEN
 */
public class GetInforServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String UPDATEPAGE = "UpdateQuestion.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = UPDATEPAGE;
            /* TODO output your page here. You may use following sample code. */
            
          
            HttpSession session = request.getSession();
            String idInfor = request.getParameter("idHidden");
            String contentInfor = request.getParameter("contentQuestionHidden");
            String correctInfor = request.getParameter("correctQuestionHidden");
            String statusInfor = request.getParameter("statusQuestionHidden");
            String courseNameInfo = request.getParameter("courseName");
            List<AnswercontentDTO> listContetAnswer = new ArrayList<>();
            AnswercontentDAO answerDAO = new AnswercontentDAO();
            listContetAnswer = answerDAO.getAllContentAnswerByID(idInfor);
            String option1 = listContetAnswer.get(0).getContentQuestion();
            String option2 = listContetAnswer.get(1).getContentQuestion();
            String option3 = listContetAnswer.get(2).getContentQuestion();
            String option4 = listContetAnswer.get(3).getContentQuestion();

            session.setAttribute("idUpdate", idInfor);
            request.setAttribute("contentQuestionUpdate", contentInfor);
            request.setAttribute("correctQuestionUpdate", correctInfor);
            request.setAttribute("option1", option1);
            request.setAttribute("option2", option2);
            request.setAttribute("option3", option3);
            request.setAttribute("option4", option4);
            session.setAttribute("courseName",courseNameInfo);
            request.setAttribute("statusQuestionUpdate", statusInfor);
            session.setAttribute("listContent", listContetAnswer);

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
            Logger.getLogger(GetInforServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GetInforServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetInforServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GetInforServlet.class.getName()).log(Level.SEVERE, null, ex);
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
