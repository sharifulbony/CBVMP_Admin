/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.policy;

import cbvmp.admin.data.manager.PolicyMasterModelManager;
import cbvmp.admin.data.manager.PolicyModelManager;
import cbvmp.admin.data.model.PolicyMasterModel;
import cbvmp.admin.data.model.PolicyModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "EditPolicyServlet", urlPatterns = {"/policy/edit/*"})
public class EditPolicyServlet extends HttpServlet {

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

        Long id = new Long(UrlSecurity.parseUrlParams(request.getPathInfo()));

        PolicyMasterModel policyModel = new PolicyMasterModelManager().get(id);

        request.setAttribute("policyModel", policyModel);
        request.getRequestDispatcher("/view/policy/edit.jsp").forward(request, response);
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
            Long id = Long.parseLong(request.getParameter("policyId"));
            Long typeNo = Long.parseLong(request.getParameter("policyTypeId"));
            //logger.debug("policyval is the iddsfadfsf"+id);
            PolicyMasterModelManager policyMasterModelManager = new PolicyMasterModelManager();
            PolicyMasterModel policyMasterModel = policyMasterModelManager.get(id);

            UserSessionManager usm = (UserSessionManager) request.getSession().getAttribute("userSession");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date startDate = null;
            Date endDate = null;
            String start = request.getParameter("startDate");
            String end = request.getParameter("endDate");

            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);

            for (PolicyMasterModel pm : policyMasterModelManager.listAll()) {
                
                
                if (pm.getPolicyTypeNo().equals(typeNo) &&  !(pm.getPolicyNO().equals(id))) {
                    
                   
                    if (!((endDate.after(startDate)) && (pm.getPolicyStart().after(endDate) || pm.getPolicyEnd().before(startDate)))) {
                        message = "Date invalid entry";
                        error = true;
                        break;

                    }
                }

            }

            if (error) {
                logger.debug("Entered");

                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/policy/edit/" + id);

            } else {
                policyMasterModel.setPolicyValue(request.getParameter("policyValue"));
                policyMasterModel.setPolicyStart(startDate);
                policyMasterModel.setPolicyEnd(endDate);

                policyMasterModelManager.update(policyMasterModel);

                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, usm.getUser(), "Policy edit attempt: ", "successful", "login no: " + usm.getLoginNo().toString(), "0", " ");
                logger.info(generateRequestLogString);
                message = null;
                error = false;
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/policy/view");
            }

//            out.println(request.getParameter("policyValue"));
//            out.println(request.getParameter("startDate"));
//            out.println(request.getParameter("endDate"));
            //request.getRequestDispatcher("/view/policy/view.jsp").forward(request, response);
            // PolicyModel policyModel = new PolicyModel();
        } catch (ParseException ex) {
            Logger.getLogger(EditPolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
