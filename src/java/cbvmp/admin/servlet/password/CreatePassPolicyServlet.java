/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.password;

import cbvmp.admin.data.manager.PasswordConfigModelManager;
import cbvmp.admin.data.model.PasswordConfigModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Administrator
 */
@WebServlet(name = "CreatePassPolicyServlet", urlPatterns = {"/pass/create"})
public class CreatePassPolicyServlet extends HttpServlet {

    String message = null;
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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CreatePassPolicyServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CreatePassPolicyServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        request.getRequestDispatcher("/view/password/create.jsp").forward(request, response);
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
        boolean error = false;
        //processRequest(request, response);
        PasswordConfigModelManager passwordConfigModelManager = new PasswordConfigModelManager();
        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");
        PasswordConfigModel passwordConfigModel = new PasswordConfigModel();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date startDate = null;
        Date endDate = null;
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");

        try {
            startDate = dateFormat.parse(start);
        } catch (ParseException ex) {
            Logger.getLogger(CreatePassPolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            endDate = dateFormat.parse(end);
        } catch (ParseException ex) {
            Logger.getLogger(CreatePassPolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*      passwordConfigModel.setPassStrength(request.getParameter("passValue"));
            passwordConfigModel.setPassStart(startDate);
            passwordConfigModel.setPassEnd(endDate);
            
            passwordConfigModelManager.save(passwordConfigModel);
            
//            out.println(request.getParameter("passValue"));
//            out.println(request.getParameter("startDate"));
//            out.println(request.getParameter("endDate"));
            request.getRequestDispatcher("/view/password/view.jsp").forward(request, response);*/
        for (PasswordConfigModel pm : passwordConfigModelManager.listAll()) {

            if (!(endDate.after(startDate) && (pm.getPassStart().after(endDate) || pm.getPassEnd().before(startDate)))) {
                message = "Date invalid entry";
                error = true;
                break;

            }

        }

        if (error) {
            //logger.debug("Entered");

            request.getSession().setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/pass/create");

        } else {
            passwordConfigModel.setPassStrength(request.getParameter("passValue"));
            passwordConfigModel.setPassStart(startDate);
            passwordConfigModel.setPassEnd(endDate);

            passwordConfigModelManager.save(passwordConfigModel);

            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Password policy creation attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "0", " ");
            logger.info(generateRequestLogString);
            message = null;
            error = false;
            request.getSession().setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/pass/view");
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
