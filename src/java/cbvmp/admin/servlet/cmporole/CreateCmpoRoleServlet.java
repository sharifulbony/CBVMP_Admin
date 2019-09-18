/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.cmporole;

import cbvmp.admin.data.manager.CmpoRoleModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.CmpoRoleModel;
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
 * @author tanbir
 */
@WebServlet(name = "CreateCmpoRole", urlPatterns = {"/cmpo/role/create"})
public class CreateCmpoRoleServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug(request.getServletPath());
//        Long id = new Long(Integer.parseInt(request.getParameter("cpoRoleId")));
        UserSessionManager usm = (UserSessionManager) request.getSession().getAttribute("userSession");
        String cmpoRoleType = request.getParameter("cmpoRoleType");
        //String cmpoId = request.getParameter("cmpoId");
//        boolean isActive = Boolean.parseBoolean(request.getParameter("active"));
//        
        UserModel umodel = new UserModelManager().get(new Long(usm.userModel.getId()));
        CmpoRoleModel cmpoRoleModel = new CmpoRoleModel();
        cmpoRoleModel.setActive(true);
        cmpoRoleModel.setName(cmpoRoleType.toUpperCase());
        cmpoRoleModel.setCreatedById(umodel);
        cmpoRoleModel.setLoginNo(usm.getLoginNo());

        /* if (cmpoId.isEmpty()) {
            cmpoRoleModel.setCmpoId(null);
        } else {
            cmpoRoleModel.setCmpoId(Integer.parseInt(cmpoId));
        }*/
        cmpoRoleModel.setCmpoId(usm.getUserCmpoId());

        CmpoRoleModelManager cmpoRoleManager = new CmpoRoleModelManager();
        String exception = cmpoRoleManager.save(cmpoRoleModel);

        if (exception == null) {

            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, usm.getUser(), "CMPO role creation attempt: ", "successful", "login no: " + usm.getLoginNo().toString(), "0", "role:" + cmpoRoleType);
            logger.info(generateRequestLogString);

            response.sendRedirect(request.getContextPath() + "/cmpo/role");
//        request.getRequestDispatcher("/cmpo/role").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "User Role already exists, retry with another");
            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, usm.getUser(), "CMPO role creation attempt: ", "unsuccessful", "login no: " + usm.getLoginNo().toString(), "1" + " ", exception);
            logger.info(generateRequestLogString);
            response.sendRedirect(request.getContextPath() + "/cmpo/role/create");
        }

    }

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

        request.getRequestDispatcher("/view/cmporole/create.jsp").forward(request, response);

//        processRequest(request, response);
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
