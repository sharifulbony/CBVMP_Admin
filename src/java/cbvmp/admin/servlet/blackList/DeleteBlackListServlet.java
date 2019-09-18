/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.blackList;

import cbvmp.admin.data.manager.BlackListModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.BlackListDeleteModel;
import cbvmp.admin.data.model.BlackListModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
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
@WebServlet(name = "DeleteBlackList", urlPatterns = {"/blackList/delete/*"})
public class DeleteBlackListServlet extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DeleteBlackListServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DeleteBlackListServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");

        UserModel umodel = new UserModelManager().get(new Long(us.userModel.getId()));
        Integer id = UrlSecurity.parseUrlParams(request.getPathInfo());
        BlackListModelManager blackManager = new BlackListModelManager();
        BlackListModel blackModel = blackManager.get(new Long(id));

        BlackListDeleteModel blackDel = null;
        Integer createdId = Integer.parseInt(umodel.getId().toString());
        System.out.println("values" + blackModel.getDocTypeNo().getDocTypeNo() + blackModel.getDocumenId() + umodel.getId());
        blackDel = blackManager.deleteBlackDoc(blackModel.getDocTypeNo().getDocTypeNo(), blackModel.getDocumenId(), createdId, us.getLoginNo());

        if (blackDel != null) {
            if (blackDel.getIsError() == 0) {
                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Black List Delete attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "1", " ");
                logger.info(generateRequestLogString);

                System.out.println("is_error " + blackDel.getIsError());
                System.out.println("error_desc " + blackDel.getErrDesc());
            } else {
                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Black List Delete attempt: ", "unsuccessful", "login no: " + us.getLoginNo().toString(), "1", blackDel.getErrDesc());
                logger.info(generateRequestLogString);

                System.out.println("is_error " + blackDel.getIsError());
                System.out.println("error_desc " + blackDel.getErrDesc());
            }
        } else {
            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Black List Delete attempt: ", "unsuccessful", "login no: " + us.getLoginNo().toString(), "1", "Database Transaction error");
            logger.info(generateRequestLogString);
            System.out.println("database problem");
        }

        response.sendRedirect(request.getContextPath() + "/blackList/view");

//            request.getRequestDispatcher("/view/blackList/view.jsp").forward(request, response);
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
