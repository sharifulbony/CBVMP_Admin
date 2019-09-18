/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.user;

import cbvmp.admin.data.manager.CmpoModelManager;
import cbvmp.admin.data.manager.CmpoRoleModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.manager.UserRoleMapModelManager;
import cbvmp.admin.data.model.CmpoModel;
import cbvmp.admin.data.model.CmpoRoleModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserRoleMapModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditUser", urlPatterns = {"/cmpo/user/edit/*"})
public class EditUserServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //out.println(request.getParameter("userName"));
        boolean error = false;
        String message = null;
        String cmpoId = request.getParameter("cmpoId");
        String categoryId = request.getParameter("userCategory");
        Long id = new Long(Integer.parseInt(request.getParameter("userId")));

        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");
        UserModelManager userModelManager = new UserModelManager();
        UserModel userModel = userModelManager.get(id);

        CmpoModel cmpo = new CmpoModelManager().get(Integer.parseInt(cmpoId));

        if (Integer.parseInt(categoryId) == 1) {
            userModel.setAdmin(isChekboxSelected(request.getParameter("isAdmin")));
        } else if (Integer.parseInt(categoryId) == 2 && isChekboxSelected(request.getParameter("isAdmin"))) {
            message = "Uncheck System Admin ";
            error = true;
        }

        if (Integer.parseInt(categoryId) == 2 && cmpo.getCmpoSeq() != 7) {
            userModel.setCmpoNo(cmpo);
        } else if (Integer.parseInt(categoryId) == 1 && cmpo.getCmpoSeq() == 7) {
            userModel.setCmpoNo(cmpo);
        } else {
            message = "Select valid User Company ";
            error = true;
        }

        if (error) {
            request.getSession().setAttribute("message", message);
            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User edit attempt: ", "unsuccessful", "login no: " + us.getLoginNo().toString(), "1" + " ", "User tried to manipulate system and edit user with unprivileged access");
            logger.info(generateRequestLogString);

            response.sendRedirect(request.getContextPath() + "/cmpo/user/edit/" + id);

        } else {
            userModel.setName(request.getParameter("userName"));

            userModel.setUserCatId(Integer.parseInt(categoryId));
            userModelManager.update(userModel);

            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User edit attempt: " + userModel.getName() + " edited", "successful", "login no: " + us.getLoginNo().toString(), "0", "");
            logger.info(generateRequestLogString);
//        out.println(userModel.getId());

            CmpoRoleModelManager cmpoRoleModelManager = new CmpoRoleModelManager();
            List<CmpoRoleModel> cmpoList = cmpoRoleModelManager.listAll();

            UserRoleMapModelManager userRoleModelMapManager = new UserRoleMapModelManager();

            for (CmpoRoleModel cmpoRole : cmpoList) {
                UserRoleMapModel userRoleMapModel = new UserRoleMapModel();
                userRoleMapModel.setRoleId(cmpoRole.getId());
                userRoleMapModel.setUserId(userModel.getId());
                userRoleModelMapManager.update(userRoleMapModel, this.isChekboxSelected(request.getParameter("v" + cmpoRole.getId())));
            }
            response.sendRedirect(request.getContextPath() + "/cmpo/user/view/" + userModel.getId());

        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = UrlSecurity.parseUrlParams(request.getPathInfo());
        PrintWriter out = response.getWriter();
        //out.println(id);
        UserModelManager userModelManager = new UserModelManager();
        UserModel userModel = userModelManager.get(new Long(id));

        request.setAttribute("userModel", userModel);
        request.getRequestDispatcher("/view/user/edit.jsp").forward(request, response);
    }

    private boolean isChekboxSelected(String value) {
        return value != null;

    }

}
