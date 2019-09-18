/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.user;

import cbvmp.admin.data.manager.CmpoRoleModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.manager.UserRoleMapModelManager;
import cbvmp.admin.data.model.CmpoRoleModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserRoleMapModel;
import cbvmp.admin.data.model.UserRoleMapProcModel;
import cbvmp.admin.data.model.UserSessionManager;
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

/**
 *
 * @author rahat
 */
@WebServlet(name = "UserViewServlet", urlPatterns = {"/cmpo/user/view/*"})
public class UserViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = UrlSecurity.parseUrlParams(request.getPathInfo());
        PrintWriter out = response.getWriter();
        //out.println(id);
        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");
        UserModelManager userModelManager = new UserModelManager();
        UserModel userModel = userModelManager.get(new Long(id));

        UserRoleMapModelManager userRoleMap = new UserRoleMapModelManager();
        List<UserRoleMapProcModel> userRole = userRoleMap.getRoleListByUserId(userModel.getId(),us.getUserCmpoId());

        List<String> allowedRole= new ArrayList();
        for (UserRoleMapProcModel uRole : userRole) {
            if (uRole.isAllowed()) {
                allowedRole.add(uRole.getName());

            }
        }
        System.out.println("roles: " +  allowedRole);
        request.setAttribute("allowedRole", allowedRole);
        request.setAttribute("userModel", userModel);
        request.getRequestDispatcher("/view/user/detailView.jsp").forward(request, response);
    }

    private boolean isChekboxSelected(String value) {
        return value != null;

    }

}
