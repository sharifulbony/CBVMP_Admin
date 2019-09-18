/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.data.model.UserSessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import oracle.sql.DATE;

/**
 *
 * @author SIT
 */
@Path("/time")
public class LastActiveTime {

    @GET
    @Path("/get") //parameters: 
    @Produces(MediaType.TEXT_PLAIN)
    public Response setLastActiveTime(@Context HttpServletRequest request,@HeaderParam("X-Requested-With") String requestType, @QueryParam("lasttime") Long time) throws IOException
    {
        
        if(requestType != null && requestType.equals("XMLHttpRequest")) {
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        userSession.setLastActiveTime(time);
        System.out.println("last active time="+userSession.getLastActiveTime());
         return Response.status(200).entity(userSession.getLastActiveTime()).build();
        }
        else{
            return Response.status(404).entity(time).build();
        }
    }
    @POST
    @Path("/post")
    
    public Response setLastActiveTime(@Context HttpServletRequest request) {
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        return Response.status(200).entity(userSession.getLastActiveTime()).build();
    }

    
}
