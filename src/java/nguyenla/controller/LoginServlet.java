/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.tbluser.ConvertPassword;
import nguyenla.tbluser.UserDAO;
import nguyenla.valid.Validation;

/**
 *
 * @author ANH NGUYEN
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOGINPAGE = "LoginAccount.jsp";
    private final String HOMEPAGE = "LoadHomeServlet";
    private String url;
    private String typeError;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            url = LOGINPAGE;
            HttpSession session = request.getSession();
            UserDAO userDAO = new UserDAO();
            String userEmail = request.getParameter("txtUserEmail").trim();
            String userPassword = request.getParameter("txtUserPassword").trim();
            String passwordConvert = ConvertPassword.convertPassword(userPassword);
            String userCase = userDAO.checkLogin(userEmail, passwordConvert).trim();

            if (!Validation.checkEmpty(userEmail)) {
                typeError = "User Email not empty !!";
            } else if (!Validation.checkEmpty(userPassword)) {
                typeError = "User Password not empty !!";
            } else if (!Validation.checkSpecialCharacter(userEmail)) {
                typeError = "User Email doesn't contain special character !!";
            } else if (!Validation.checkSpecialCharacter(userPassword)) {
                typeError = "User Password doesn't contain special character !!";
            } else if (!userCase.equals(userEmail)) {
                typeError = "Don't have this account in system !!";
            } else {
                url = HOMEPAGE;
                if (userCase.equalsIgnoreCase("admin")) {
                    session.setAttribute("nameUser", userEmail);
                    session.setAttribute("userRole", "admin");
                } else {
                    session.setAttribute("nameUser", userEmail);
                    session.setAttribute("userRole", "student");
                }
            }
            request.setAttribute("userEmail", userEmail);
            request.setAttribute("userPassword", userPassword);
            request.setAttribute("errorLogin", typeError);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
