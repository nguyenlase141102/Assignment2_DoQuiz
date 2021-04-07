/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.tblanswercontent.AnswercontentDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class CheckDoQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SUCCESS = "DoQuizServlet";
    private final String FAIL = "HomePage.jsp";
    private String url;
    private String typeError;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession();
            Map<String, List<AnswercontentDTO>> mapQuestion = new HashMap<>();
            mapQuestion = (Map<String, List<AnswercontentDTO>>) session.getAttribute("map");
            String courseName = (String) session.getAttribute("courseName");
            String chooseCourse = request.getParameter("listNameSubject");
            String timeQuiz = (String) session.getAttribute("timeQuiz");
             String timeCount = (String) session.getAttribute("timeCount");
            
            
            if (mapQuestion != null && !chooseCourse.equals(courseName)) {
                typeError = "Doing "+ courseName +" Quiz !!";
                url = FAIL;
            }else if(courseName == null){
                url = SUCCESS;
            }else if(mapQuestion != null && chooseCourse.equals(courseName)){
                url = SUCCESS;
            }
            
//            if(timeQuiz == null){
//                System.out.println("Time quiz null");
//            }else if(timeQuiz != null){
//                 request.setAttribute("checkTime","1");
//                 session.setAttribute("timeQuiz",timeQuiz);
//                 System.out.println("Time Quiz not null : "+timeQuiz);
//                  System.out.println("Time Count not null : " + timeCount);
//            }
            
            
            request.setAttribute("doQuizAlready", typeError);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error Messages (Check Do Quiz) : " + e.getMessage());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
