/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.policy;

import cbvmp.admin.data.manager.PolicyMasterModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.PolicyMasterModel;
import cbvmp.admin.data.model.UserModel;
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
@WebServlet(name = "CreatePolicyServlet", urlPatterns = {"/policy/create"})
public class CreatePolicyServlet extends HttpServlet {

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
//            out.println("<title>Servlet CreatePolicyServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CreatePolicyServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        request.getRequestDispatcher("/view/policy/create.jsp").forward(request, response);
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
        //processRequest(request, response);
        UserSessionManager upm = (UserSessionManager) request.getSession().getAttribute("userSession");
        PrintWriter out = response.getWriter();
        boolean error = false;
        //    Long id = Long.parseLong(request.getParameter("policyId"));
        PolicyMasterModelManager policyMasterModelManager = new PolicyMasterModelManager();
        PolicyMasterModel policyMasterModel = new PolicyMasterModel();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date startDate = null;
        Date endDate = null;
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");

        try {
            startDate = dateFormat.parse(start);
        } catch (ParseException ex) {
            Logger.getLogger(CreatePolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            endDate = dateFormat.parse(end);
        } catch (ParseException ex) {
            Logger.getLogger(CreatePolicyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Long pId = Long.parseLong(request.getParameter("policyTypeId"));
        // Long.parseLong(request.getParameter("policyId"))
        // policyMasterModel.setPolicyTypeNo(request.getParameter("policyId"));

        /* policyMasterModel.setPolicyTypeNo(pId);
            policyMasterModel.setPolicyValue(request.getParameter("policyValue"));
            policyMasterModel.setPolicyStart(startDate);
            policyMasterModel.setPolicyEnd(endDate);
            
            policyMasterModelManager.save(policyMasterModel);
            
            out.println(request.getParameter("policyTypeId"));
            out.println(request.getParameter("policyValue"));
            out.println(request.getParameter("startDate"));
            out.println(request.getParameter("endDate"));
            out.println("-----------");
            for (PolicyMasterModel pm : policyMasterModelManager.listAll()) {
                if(pm.getPolicyTypeNo()==pId){
                if (!(((endDate.after(startDate))&&(pm.getPolicyStart().after(endDate))) || ((endDate.after(startDate))&&(pm.getPolicyEnd().after(startDate))))  ) {
                    out.println(pm.getPolicyValue());
                    out.println(pm.getPolicyTypeNo());
                    out.println(pm.getPolicyStart());
                    out.println(pm.getPolicyEnd());
                    out.println("in the loop");
                }
                }
                }*/
        //  request.getRequestDispatcher("/view/policy/view.jsp").forward(request, response);
        for (PolicyMasterModel pm : policyMasterModelManager.listAll()) {
            if (pm.getPolicyTypeNo().equals(pId)) {
                if (!((endDate.after(startDate)) && (pm.getPolicyStart().after(endDate) || pm.getPolicyEnd().before(startDate)))) {
                    message = "Date invalid entry";
                    error = true;
                    break;

                }
            }

        }
        UserModel umodel = new UserModelManager().get(new Long(upm.userModel.getId()));

        if (error) {
            //logger.debug("Entered");

            request.getSession().setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/policy/create");

        } else {
            policyMasterModel.setPolicyTypeNo(pId);
            policyMasterModel.setPolicyValue(request.getParameter("policyValue"));
            policyMasterModel.setPolicyStart(startDate);
            policyMasterModel.setPolicyEnd(endDate);
            policyMasterModel.setCreatedById(umodel);
            policyMasterModel.setLoginNo(upm.getLoginNo());

            policyMasterModelManager.save(policyMasterModel);

            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, upm.getUser(), "Policy creation attempt: ", "successful", "login no: " + upm.getLoginNo().toString(), "0", " ");
            logger.info(generateRequestLogString);
            message = null;
            error = false;
            request.getSession().setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/policy/view");
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
