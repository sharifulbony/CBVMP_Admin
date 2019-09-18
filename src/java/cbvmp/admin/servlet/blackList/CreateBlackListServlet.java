/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.blackList;

import cbvmp.admin.data.manager.BlackListModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.BlackListModel;
import cbvmp.admin.data.model.DocumentTypeModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CreateBlackList", urlPatterns = {"/blackList/create"})
public class CreateBlackListServlet extends HttpServlet {

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

        request.getRequestDispatcher("/view/blackList/create.jsp").forward(request, response);
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
//        processRequest(request, response);
        String docType = request.getParameter("docType");
        String docId = request.getParameter("docId");

        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");

        UserModel umodel = new UserModelManager().get(new Long(us.userModel.getId()));

        BlackListModelManager blackManager = new BlackListModelManager();
        BlackListModel blackModel = new BlackListModel();

        DocumentTypeModel dcModel = new DocumentTypeModel();
        dcModel.setDocTypeNo(Integer.parseInt(docType));

        System.out.println("form capture" + Integer.parseInt(docType) + " " + docId);
        blackModel.setDocTypeNo(dcModel);
        blackModel.setDocumenId(docId);
        blackModel.setCreatedBy(umodel);
        blackModel.setLoginNo(us.getLoginNo());

        String exception = blackManager.save(blackModel);

        if (exception == null) {

            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Black List Create attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "1", " ");
            logger.info(generateRequestLogString);
            response.sendRedirect(request.getContextPath() + "/blackList/view");
        } else {
            request.getSession().setAttribute("message", "Document ID is already Black Listed");
            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Black List Create attempt: ", "unsuccessful", "login no: " + us.getLoginNo().toString(), "1", exception);
            logger.info(generateRequestLogString);
            response.sendRedirect(request.getContextPath() + "/blackList/create");
        }
//        blackManager.save(blackModel);
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
