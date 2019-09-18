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
import cbvmp.admin.util.security.UrlSecurity;
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
@WebServlet(name = "EditPassPolicyServlet", urlPatterns = {"/pass/edit/*"})
public class EditPassPolicyServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");
    String message = null;

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
//            out.println("<title>Servlet EditPassPolicyServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EditPassPolicyServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        Long id = new Long(UrlSecurity.parseUrlParams(request.getPathInfo()));
        // System.out.println(id);
        PasswordConfigModel passModel = new PasswordConfigModelManager().get(id);

        request.setAttribute("passModel", passModel);
        request.getRequestDispatcher("/view/password/edit.jsp").forward(request, response);
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
        try {
            //String val = request.getParameter("policyValue");
            //System.out.println(val);
            boolean error = false;
            //processRequest(request, response);
            PrintWriter out = response.getWriter();
            Long id = Long.parseLong(request.getParameter("passNo"));
            PasswordConfigModelManager passwordConfigModelManager = new PasswordConfigModelManager();
            UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");
            PasswordConfigModel passwordConfigModel = passwordConfigModelManager.get(id);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date startDate = null;
            Date endDate = null;
            String start = request.getParameter("startDate");
            String end = request.getParameter("endDate");

            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            /*            
            passwordConfigModel.setPassStrength(request.getParameter("passValue"));
            passwordConfigModel.setPassStart(startDate);
            passwordConfigModel.setPassEnd(endDate);
          
            passwordConfigModelManager.update(passwordConfigModel);
             */

//            out.println(request.getParameter("passValue"));
//            out.println(request.getParameter("startDate"));
//            out.println(request.getParameter("endDate"));
            //request.getRequestDispatcher("/view/password/view.jsp").forward(request, response);
            for (PasswordConfigModel pm : passwordConfigModelManager.listAll()) {
                if (!id.equals(pm.getPassNO())) {
                    if (!(endDate.after(startDate) && (pm.getPassStart().after(endDate) || pm.getPassEnd().before(startDate)))) {
                        message = "Date invalid entry";
                        error = true;
                        break;

                    }

                }
            }

            if (error) {
                //logger.debug("Entered");

                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/pass/edit/" + id);

            } else {
                passwordConfigModel.setPassStrength(request.getParameter("passValue"));
                passwordConfigModel.setPassStart(startDate);
                passwordConfigModel.setPassEnd(endDate);

                passwordConfigModelManager.update(passwordConfigModel);

                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Password policy edit attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "0", " ");
                logger.info(generateRequestLogString);

                message = null;
                error = false;
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/pass/view");
            }

        } catch (ParseException ex) {
            Logger.getLogger(EditPassPolicyServlet.class.getName()).log(Level.SEVERE, null, ex);

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
