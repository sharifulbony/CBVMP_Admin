/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.data.manager.UserInfoModelManager;
import cbvmp.admin.data.model.ChangePasswordModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.error.ApplicationError;
import cbvmp.admin.util.security.CustomeEncryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

/**
 *
 * @author SIT
 */
@Path("/login")
public class LoginApi {

    @GET
    @Path("/get") //parameters: 
    @Produces(MediaType.TEXT_PLAIN)
    public Response Login(@Context HttpServletRequest request, @HeaderParam("X-Requested-With") String requestType, @QueryParam("username") String username, @QueryParam("password") String password) {

        //PrintWriter out = response.getWriter();
        //BaseClass baseLog= (BaseClass) request.getSession().getAttribute("base");
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        UserInfoModelManager userManager = new UserInfoModelManager();

        String user = username;
        String pass = password;
        System.out.println(user);
        //System.out.println(pass);

        try {
            if (requestType != null && requestType.equals("XMLHttpRequest")) {
                //userModel = userManager.verifyChangePasswordRequest("CBVMP_MASTER", user);           

//            if ((CustomeEncryption.validatePassword(pass, userModel.getPassword()))) {
//
//                userSession = new UserSessionManager();
//                userSession.setUser(user);
//                request.getSession().invalidate();
//                response.sendRedirect("view/login/change.jsp");
//
//            } else if (userModel != null && !(userModel.isPasswdChangeRequired())) {
                userSession.userModel = userManager.verifyUserNamePassword("CBVMP_MASTER", user, pass);

                if (userSession.userModel != null) {
                    //userSession = new UserSessionManager();
                    //userSession.setUser(user);
                    //userSession.setLoginTime(new Date());
                    System.out.println("before setlock = " + userSession.getLock());
                    userSession.setLock(0);
                    System.out.println("after setlock = " + userSession.getLock());
                    //count=0;
                    //request.getSession(true).setAttribute("userSession", userSession);
                    // request.getSession(false).setMaxInactiveInterval(10);
                    //response.sendRedirect("view/login/success.jsp");
                    return Response.status(200).entity(userSession.getLock()).build();
                } else {

                    //String message = ApplicationError.EXP010206_DESC;
                    //request.setAttribute("message", message);
                    //request.getRequestDispatcher("home.jsp").forward(request, response);
                    return Response.status(200).entity(userSession.getLock()).build();
                }
            } else {
                return Response.status(404).entity(userSession.getLock()).build();
            }
            /*String message = ApplicationError.EXP010206_DESC;
                request.setAttribute("message", message);
                request.getRequestDispatcher("home.jsp").forward(request, response);*/

        } catch (Exception e) {
            return Response.status(200).entity(userSession.getLock()).build();
        }

    }
}
