/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.user;

import cbvmp.admin.data.manager.UserInfoModelManager;
import cbvmp.admin.data.manager.UserLoginInfoModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.IsLoginCheckModel;
import cbvmp.admin.data.model.UserLoginInfoModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "ClearSessionUserServlet", urlPatterns = {"/cmpo/user/logout/*"})
public class ClearSessionUserServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");
//   private String userId=null;
//    private Integer id;

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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ClearSessionUserServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ClearSessionUserServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");

        Integer id = UrlSecurity.parseUrlParams(request.getPathInfo());
        System.out.println("user no " + id);
        UserModelManager userModelManager = new UserModelManager();
        UserModel userModel = userModelManager.get(new Long(id));
        System.out.println("user name: " + userModel.getName());
        UserInfoModelManager userManager = new UserInfoModelManager();

        UserLoginInfoModelManager userLoginInfoModelManager = new UserLoginInfoModelManager();
        IsLoginCheckModel logCheckModel = userManager.getIsLogin("CBVMP_MASTER", userModel.getUserId());

        if (logCheckModel != null) {
            Integer uid = logCheckModel.getLogin_no();
            System.out.println("user login no : " + uid);
            UserLoginInfoModel userLoginInfoModel = userLoginInfoModelManager.get(uid);
            userLoginInfoModel.setLogout_time(new Date());
            userLoginInfoModel.setIs_login(0);
            userLoginInfoModel.setIs_sess_time_out(0);
            userLoginInfoModelManager.update(userLoginInfoModel);

//        userModel.setActive(false);
//        userModelManager.update(userModel);
            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User session clear attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "0", "user : " + userModel.getName() + " session cleared");
            logger.info(generateRequestLogString);
//            request.getSession(true).setAttribute("authenticated", false); 
//        request.getSession(true).setAttribute("notLogPass", false); 
            logger.debug("session cleared");
            response.sendRedirect(request.getContextPath() + "/cmpo/user");
        } else {
            response.sendRedirect(request.getContextPath() + "/cmpo/user");
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
