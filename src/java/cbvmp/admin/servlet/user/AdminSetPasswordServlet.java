/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.user;

import cbvmp.admin.data.manager.ChangePasswordModelManager;
import cbvmp.admin.data.manager.UserModelManager;

import cbvmp.admin.data.model.ChangePasswordModel;
import cbvmp.admin.data.model.PasswordFrequencyModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;


import cbvmp.admin.util.security.CustomeEncryption;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author rahat
 */
@WebServlet(name = "ChangeUserPassword", urlPatterns = {"/cmpo/user/change/*"})
public class AdminSetPasswordServlet extends HttpServlet {
     Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");
    private String userId=null;
    private Integer id;

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AdminSetPasswordServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AdminSetPasswordServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        id = UrlSecurity.parseUrlParams(request.getPathInfo());      
        UserModelManager userModelManager = new UserModelManager();
        UserModel userModel = userModelManager.get(new Long(id));
        userId=userModel.getUserId();
        request.setAttribute("userModel", userModel);
        request.getRequestDispatcher("/view/user/userPasswordChange.jsp").forward(request, response);
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
        String message = null;
        //PrintWriter out = response.getWriter();
        ChangePasswordModelManager passManager = new ChangePasswordModelManager();
        List<PasswordFrequencyModel> passFreqlist = new ArrayList<PasswordFrequencyModel>();

        UserSessionManager u = (UserSessionManager) request.getSession().getAttribute("userSession");
        
        String newPass = request.getParameter("password");
        String reNew = request.getParameter("retypePassword");
        //String passStrength = request.getParameter("passwordStrength");
        //System.out.println("Strength" + passStrength);

        // String message = null;
        boolean error = false;
       

        //boolean oldPlainPass = oldPass.equals(userModel.getPassword());
        //logger.debug("Password to be checked:" + newPass);
        try {

            if (newPass.equals(reNew)) { // checks new == retype                
                //oldEncrypPass = CustomeEncryption.validatePassword(oldPass, userModel.getPassword());

             

                    boolean passfound = false;
                    //long startTime = System.currentTimeMillis();
                    passFreqlist = passManager.checkPasswordFreq("CBVMP_MASTER",userId );
//                    long stopTime = System.currentTimeMillis();
//                    long elapsedTime = stopTime - startTime;//                    
//                    logger.info((int)elapsedTime);                   
                    if (!(passFreqlist.isEmpty())) { // checks pass used previously
                        for (int i = 0; i < passFreqlist.size(); i++) {
                            //check password history table or current pass                                                         
                            if (newPass.equals(userId) || CustomeEncryption.validatePassword(newPass, passFreqlist.get(i).getOldPassword())) {
                                passfound = true;
                                error = true;
                                message = "You cannot use this password";
                                break;
                            }
                        }
                    }
                    if (!passfound) {

                      

                            ChangePasswordModel passModel = passManager.changePassword("CBVMP_MASTER", userId, null, newPass, true);

                            if ((passModel.getIsError() == 0)) {
                                //logger.info("user found" + " " + userId);
                                error = false;
                                
                                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, u.getUser(), "Change password attempt "+" "+userId +" password changed" , "successful","login no: " + u.getLoginNo().toString(), "1", " ");
                                logger.info(generateRequestLogString);
                                
                                response.sendRedirect(request.getContextPath() +  "/cmpo/user");
                            } else {
                                message = passModel.getErrDesc();
                                error = true;
                            }
                        
                    }
                

            } else {
                message = "Retype new password correctly";
                error = true;

            }
            if (error) {
                //logger.debug(message);
                //String message = ;
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/cmpo/user/change/" + id);
                //response.sendRedirect("../view/login/change.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
