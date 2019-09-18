/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.permission;

import cbvmp.admin.data.manager.AccessControlListManager;
import cbvmp.admin.data.manager.AccessPermissionModelManager;
import cbvmp.admin.data.model.AccessControlListModel;
import cbvmp.admin.data.model.AccessPermissionModel;
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
 * @author tanbir
 */
@WebServlet(name = "EditPermission", urlPatterns = {"/role/permission/*"})
public class EditPermissionServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println(request.getParameter("id"));
        AccessPermissionModelManager aclManager = new AccessPermissionModelManager();
        UserSessionManager usm = (UserSessionManager) request.getSession().getAttribute("userSession");

        List<AccessPermissionModel> acl = aclManager.getAclByRole(Integer.parseInt(request.getParameter("id")));
        for (AccessPermissionModel names : acl) {
            names.setAllowed(isChekboxSelected((String) request.getParameter("v" + names.getId())));
            aclManager.update(names);

        }
        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, usm.getUser(), "Permission edit attempt: ", "successful", "login no: " + usm.getLoginNo().toString(), "0", " ");
        logger.info(generateRequestLogString);

        response.sendRedirect(request.getContextPath() + "/cmpo/role");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer id = UrlSecurity.parseUrlParams(request.getPathInfo());
        request.setAttribute("id", id);
        request.getRequestDispatcher("/view/permission/view.jsp").forward(request, response);
    }

    private boolean isChekboxSelected(String value) {
        return value != null;

    }

}
