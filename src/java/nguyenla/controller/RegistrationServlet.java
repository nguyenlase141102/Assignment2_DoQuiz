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
import nguyenla.tbluser.ConvertPassword;
import nguyenla.tbluser.UserDAO;
import nguyenla.tbluser.UserDTO;
import nguyenla.valid.Validation;

/**
 *
 * @author ANH NGUYEN
 */
public class RegistrationServlet extends HttpServlet {

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
    private final String REGISPAGE = "Registration.jsp";
    private String url;
    private String typeError;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            url = REGISPAGE;
            UserDAO user = new UserDAO();
            String userEmail = request.getParameter("emailUser").trim();
            String userName = request.getParameter("nameUser").trim();
            String userPassword = request.getParameter("password").trim();
            String userRole = request.getParameter("role").trim();
            String status = request.getParameter("status");

            String passwordConvert = ConvertPassword.convertPassword(userPassword).trim();
            String nameCheck = user.checkLogin(userEmail, passwordConvert);
           
            
            if (!Validation.checkEmpty(userName)) {
                typeError = "Name not empty !!";
            } else if (!Validation.checkEmpty(userPassword)) {
                typeError = "Password not empty !!";
            } else if (!Validation.checkSpecialCharacter(userEmail)) {
                typeError = "Email doesn't contain special characters !!";
            } else if (!Validation.checkSpecialCharacter(userName)) {
                typeError = "Name doesn't contain special characters !!";
            } else if (!Validation.checkSpecialCharacter(userPassword)) {
                typeError = "Password doesn't contain special characters !!";
            } else if (!nameCheck.equals("")) {
                typeError = "Duplicated code !!";
            } else {
                UserDTO newUser = new UserDTO(userEmail, userName, passwordConvert, userRole, status);
                if (user.registrationUser(newUser)) {
                    url = LOGINPAGE;
                } else {
                    typeError = "Create account fail !!";
                }
            }
            request.setAttribute("errorRegis", typeError);
            request.setAttribute("email", userEmail);
            request.setAttribute("name", userName);
            request.setAttribute("password", userPassword);
            request.setAttribute("messCreate", "Create account success !!");
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
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
