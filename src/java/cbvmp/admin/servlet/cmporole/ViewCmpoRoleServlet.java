/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.cmporole;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.manager.CmpoRoleModelManager;
import cbvmp.admin.data.model.CmpoRoleModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author tanbir
 */
@WebServlet(name = "CmpoRole", urlPatterns = {"/cmpo/role"})
public class ViewCmpoRoleServlet extends HttpServlet {
    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug(request.getServletPath());
        List<CmpoRoleModel> list = new CmpoRoleModelManager().listAll();
        request.getRequestDispatcher("/view/cmporole/view.jsp").forward(request, response);

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
