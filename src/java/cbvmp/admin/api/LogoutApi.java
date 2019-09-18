/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.data.manager.UserLoginInfoModelManager;
import cbvmp.admin.data.model.UserLoginInfoModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author SIT
 */
@Path("/logout")
public class LogoutApi {

    @GET
    @Path("/get") //parameters: 
    @Produces(MediaType.TEXT_PLAIN)
    public Response Logout(@Context HttpServletRequest request, HttpServletResponse response, @HeaderParam("X-Requested-With") String requestType, @QueryParam("username") String username, @QueryParam("password") String password) throws IOException {

        Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        Integer id = userSession.getLoginNo();
        UserLoginInfoModelManager userLoginInfoModelManager = new UserLoginInfoModelManager();
        if (requestType != null && requestType.equals("XMLHttpRequest")) {

            UserLoginInfoModel userLoginInfoModel = userLoginInfoModelManager.get(id);
            userLoginInfoModel.setLogout_time(new Date());
            userLoginInfoModel.setIs_login(0);
            userLoginInfoModel.setIs_sess_time_out(1);
            userLoginInfoModelManager.update(userLoginInfoModel);

            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Logout", "successful", "login no: " + userSession.getLoginNo().toString(), "0", "Session timeout");
            logger.info(generateRequestLogString);
            request.getSession().invalidate();

            request.getSession().setAttribute("message", "Your session is timed out!");
            //response.sendRedirect("home.jsp");
            return Response.status(200).entity("logout").build();
        } else {
            return Response.status(404).entity("logout").build();
        }
    }
}
