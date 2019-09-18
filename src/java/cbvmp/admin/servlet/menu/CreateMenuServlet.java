/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.menu;

import cbvmp.admin.data.manager.MenuModelManager;
import cbvmp.admin.data.model.MenuModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jws.WebService;
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
@WebServlet(name = "CreateMenu", urlPatterns = {"/menu/create"})
public class CreateMenuServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/menu/create.jsp").forward(request, response);

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
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("menuName"));
        out.println(request.getParameter("menuUrl"));
        out.println(request.getParameter("parentId"));
        Long parentId;
        if (request.getParameter("parentId").equals("")) {
            parentId = null;
        } else {
            parentId = new Long(String.valueOf(request.getParameter("parentId")));
        }
        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");
        MenuModelManager menuModelManager = new MenuModelManager();

        MenuModel menuModel = new MenuModel();
        menuModel.setAlias(request.getParameter("menuName"));
        menuModel.setParentId(parentId);
        menuModel.setUrl(request.getParameter("menuUrl"));
        menuModel.setUserCategory(Integer.parseInt(request.getParameter("userCategory")));
        menuModelManager.save(menuModel);

        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Menu creation attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "0", menuModel.getAlias() + " " + menuModel.getUserCategory().toString());
        logger.info(generateRequestLogString);
        response.sendRedirect(request.getContextPath() + "/menu/view");

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
