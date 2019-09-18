/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.cmporole;

import cbvmp.admin.data.manager.CmpoRoleModelManager;
import cbvmp.admin.data.model.CmpoRoleModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author tanbir
 */
@WebServlet(name = "EditCmpoRole", urlPatterns = {"/cmpo/role/edit/*"})
public class EditCmpoRoleServlet extends HttpServlet {
    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = new Long(UrlSecurity.parseUrlParams(request.getPathInfo()));
        
        try {
            CmpoRoleModel cmpoRoleModel = new CmpoRoleModelManager().get(id);
            request.setAttribute("cmpoRoleModel", cmpoRoleModel);
        } catch (Exception ex) {
            Logger.getLogger(EditCmpoRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/view/cmporole/edit.jsp").forward(request, response);

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
        
        CmpoRoleModelManager cmpoRoleManager = new CmpoRoleModelManager();
        UserSessionManager usm = (UserSessionManager) request.getSession().getAttribute("userSession");
        Long id = new Long(Integer.parseInt(request.getParameter("cmpoRoleId")));
        String name = request.getParameter("cmpoRoleType");
        //String cmpoId = request.getParameter("cmpoId");
        CmpoRoleModel cmpoRoleModel = cmpoRoleManager.get(id);
        cmpoRoleModel.setId(id);
        cmpoRoleModel.setName(name.toUpperCase());
//        if (cmpoId.isEmpty()) {
//            cmpoRoleModel.setCmpoId(null);
//        } else {
//            cmpoRoleModel.setCmpoId(Integer.parseInt(cmpoId));
//        }
        cmpoRoleModel.setCmpoId(usm.getUserCmpoId());
        cmpoRoleManager.update(cmpoRoleModel);
        
        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, usm.getUser(), "CMPO role edit attempt: ", "successful","login no: "+usm.getLoginNo().toString(), "0","role:" +name);
        logger.info(generateRequestLogString);
        
        request.getRequestDispatcher("/cmpo/role").forward(request, response);
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
