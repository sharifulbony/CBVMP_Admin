/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.user;

import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
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
@WebServlet(name = "LockUser", urlPatterns = {"/cmpo/user/lock/*"})
public class LockUserServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");

        Integer id = UrlSecurity.parseUrlParams(request.getPathInfo());
        UserModelManager userModelManager = new UserModelManager();
        UserModel userModel = userModelManager.get(new Long(id));
        userModel.setActive(false);
        userModelManager.update(userModel);

        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User lock attempt: "  , "successful", "login no: " + us.getLoginNo().toString(), "1", "user : "+ userModel.getName()+ " locked");
        logger.info(generateRequestLogString);

        response.sendRedirect(request.getContextPath() + "/cmpo/user");

    }

}
