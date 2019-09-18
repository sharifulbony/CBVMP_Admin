/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet;

import cbvmp.admin.api.User;
import cbvmp.admin.data.manager.UserLoginInfoModelManager;
import cbvmp.admin.data.model.UserLoginInfoModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
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
 * @author SIT
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

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
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        Integer id = userSession.getLoginNo();
        UserLoginInfoModelManager userLoginInfoModelManager = new UserLoginInfoModelManager();
        UserLoginInfoModel userLoginInfoModel = userLoginInfoModelManager.get(id);
        userLoginInfoModel.setLogout_time(new Date());
        userLoginInfoModel.setIs_login(0);
        userLoginInfoModel.setIs_sess_time_out(0);
        userLoginInfoModelManager.update(userLoginInfoModel);
        
        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Logout", "successful", "login no: " + userSession.getLoginNo().toString() , "0", " ");
        logger.info(generateRequestLogString);

        
        System.out.println("logout enter..... "+ userLoginInfoModel.getIs_login());
        request.getSession().setAttribute("authenticated", false); 
        request.getSession().setAttribute("notLogPass", false); 
        request.getSession().invalidate();
        response.sendRedirect("home.jsp");
        //System.exit(0);

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
