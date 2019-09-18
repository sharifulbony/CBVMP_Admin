/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet;

import cbvmp.admin.data.BaseClass;
import cbvmp.admin.data.manager.ChangePasswordModelManager;
import cbvmp.admin.data.model.ChangePasswordModel;
import cbvmp.admin.data.model.PasswordFrequencyModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.error.ApplicationError;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.CustomeEncryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author SIT
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/change/password"})
public class ChangePasswordServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

        if (userSession.userModel.isPasswdChangeRequired() || userSession.dateExp) {

            request.getRequestDispatcher("/view/login/forceChange.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/view/login/change.jsp").forward(request, response);
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
        //BaseClass basePost= (BaseClass) request.getSession().getAttribute("base");
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        String message = null;
        //PrintWriter out = response.getWriter();
        ChangePasswordModelManager passManager = new ChangePasswordModelManager();
        List<PasswordFrequencyModel> passFreqlist = new ArrayList<PasswordFrequencyModel>();

        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String reNew = request.getParameter("retypeNew");
        String passStrength = request.getParameter("passwordStrength");
        System.out.println("Strength " + passStrength);

        // String message = null;
        boolean error = false;
        boolean oldEncrypPass = false;

        //boolean oldPlainPass = oldPass.equals(userModel.getPassword());
        //logger.debug("Password to be checked:" + newPass);
        try {

            if (newPass.equals(reNew)) { // checks new == retype                
                oldEncrypPass = CustomeEncryption.validatePassword(oldPass, userSession.userModel.getPassword());

                if (oldEncrypPass) {

                    boolean passfound = false;
                    //long startTime = System.currentTimeMillis();
                    passFreqlist = passManager.checkPasswordFreq("CBVMP_MASTER", userSession.getUser());
//                    long stopTime = System.currentTimeMillis();
//                    long elapsedTime = stopTime - startTime;//                    
//                    logger.info((int)elapsedTime);                   
                    if (!(passFreqlist.isEmpty())) { // checks pass used previously
                        for (int i = 0; i < passFreqlist.size(); i++) {
                            //check password history table or current pass                                                         
                            if (newPass.equals(oldPass) || newPass.equals(userSession.getUser()) || CustomeEncryption.validatePassword(newPass, passFreqlist.get(i).getOldPassword())) {
                                passfound = true;
                                error = true;
                                message = "You cannot use this password";

                                break;
                            }
                        }
                    }
                    if (!passfound) {

                        if (newPass.length() < 8 || newPass.length() > 20) {
                            message = "Password length must be minimum 8 and maximum 20 characters long";
                            error = true;
                            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Change password attempt", "unsuccessful", "login no: " + userSession.getLoginNo().toString(), "0", "Password length mismatched");
                            logger.error(generateRequestLogString);
                        } else {
                            if (!userSession.getPasswordStrength().equalsIgnoreCase("Weak") && !userSession.getPasswordStrength().equalsIgnoreCase("good") && !passStrength.equals(userSession.getPasswordStrength())) {
                                message = "Password strength must be " + userSession.getPasswordStrength() + " (contains three types from A-Z,a-z,0-9,special characters)";
                                System.out.println("1st block: " + message);
                                //logger.error(message);
                                error = true;
                                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Change password attempt", "unsuccessful", "login no: " + userSession.getLoginNo().toString(), "0", "Password strength mismatched");
                                logger.error(generateRequestLogString);

                            }
                            else if (userSession.getPasswordStrength().equalsIgnoreCase("good") && passStrength.equalsIgnoreCase("weak")) {
                                message = "Password strength must be at least " + userSession.getPasswordStrength()+ " (contains two types from A-Z,a-z,0-9,special characters)";
                                System.out.println("2nd block: " + message);
                                error = true;
                                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Change password attempt", "unsuccessful", "login no: " + userSession.getLoginNo().toString(), "0", "Password strength mismatched");
                                logger.error(generateRequestLogString);

                            } else {

                                ChangePasswordModel passModel = passManager.changePassword("CBVMP_MASTER", userSession.getUser(), userSession.userModel.getPassword(), newPass, userSession.userModel.isPasswdChangeRequired());

                                if ((passModel.getIsError() == 0)) {
                                    //logger.info("user found" + " " + userSession.getUser());
                                    error = false;
                                    System.out.println("user : " + userSession.getUser());
                                    String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Change password attempt", "successful", "login no: " + userSession.getLoginNo().toString(), "0", " ");
                                    logger.info(generateRequestLogString);

//                                    request.getSession().invalidate();

                                    System.out.println("changed enter ");
                                    response.sendRedirect(request.getContextPath() + "/logout");
                                } else {
                                    message = passModel.getErrDesc();
                                    error = true;
                                }
                            }

                        }

                    }
                } else {
                    message = "Old password is not correct";
                    error = true;
                }

            } else {
                message = "Retype new password correctly";
                error = true;

            }
            if (error) {
                //logger.debug(message);
                //String message = ;
                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Change password attempt", "unsuccessful", "login no: " + userSession.getLoginNo().toString(), "0", message);
                logger.error(generateRequestLogString);

                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/change/password");
                //response.sendRedirect("../view/login/change.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //processRequest(request, response);
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
