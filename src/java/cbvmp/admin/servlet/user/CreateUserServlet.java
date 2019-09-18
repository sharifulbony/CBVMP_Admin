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
import cbvmp.admin.util.security.CustomeEncryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
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
 * @author tanbir
 */
@WebServlet(name = "CreateUser", urlPatterns = {"/cmpo/user/create"})
public class CreateUserServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            boolean error = false;
            String message = null;
            String password = request.getParameter("password");
            String cmpoId = request.getParameter("cmpoId");

            String categoryId = request.getParameter("userCategory");
            password = CustomeEncryption.encryptPassword(password);

            UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");

            UserModel userModel = new UserModel();
            userModel.setName(request.getParameter("userName"));
            userModel.setUserId(request.getParameter("userId"));
            userModel.setPassword(password);
            userModel.setPassChangeRequired(true);

            CmpoModel cmpo = new CmpoModelManager().get(Integer.parseInt(cmpoId));

            UserModel umodel = new UserModelManager().get(new Long(us.userModel.getId()));

            if (Integer.parseInt(categoryId) == 1 ) {
                userModel.setAdmin(isChekboxSelected(request.getParameter("isAdmin")));
            } else if(Integer.parseInt(categoryId) == 2 && isChekboxSelected(request.getParameter("isAdmin")) ){
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
                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User creation attempt: " , "unsuccessful", "login no: " + us.getLoginNo().toString(), "1" + " ","User tried to manipulate system and create user with unprivileged access");
                logger.info(generateRequestLogString);

                response.sendRedirect(request.getContextPath() + "/cmpo/user/create");
            } else {
                userModel.setActive(true);
                userModel.setUserCatId(Integer.parseInt(request.getParameter("userCategory")));
                userModel.setCreatedById(umodel);
                userModel.setLoginNo(us.getLoginNo());
                UserModelManager userModelManager = new UserModelManager();

                String exception = userModelManager.save(userModel);

                if(exception == null)
                {
                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User creation attempt: " , "successful", "login no: " + us.getLoginNo().toString(), "0",userModel.getName() + "created" );
                logger.info(generateRequestLogString);

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
                else{
                    
                request.getSession().setAttribute("message", "User ID already exists, retry with another" );
                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "User creation attempt: " , "unsuccessful", "login no: " + us.getLoginNo().toString(), "1" + " ",exception);
                logger.info(generateRequestLogString);
                response.sendRedirect(request.getContextPath() + "/cmpo/user/create");
                
                }
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CreateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(CreateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/view/user/create.jsp").forward(request, response);
    }

    private boolean isChekboxSelected(String value) {
        return value != null;

    }
}
