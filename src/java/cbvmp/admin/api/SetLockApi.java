/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.data.BaseClass;
import cbvmp.admin.data.model.UserSessionManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
@Path("/setlock")
public class SetLockApi {

    @GET
    @Path("/set")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setLockApi(@Context HttpServletRequest request,@HeaderParam("X-Requested-With") String requestType) {
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
        userSession.setLock(1);
        System.out.println("in side setlock=" + userSession.getLock());
        return Response.status(200).entity(userSession.getLock()).build();
        }else{
            return Response.status(404).entity(userSession.getLock()).build();
        }

    }

    @POST
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLockApi(@Context HttpServletRequest request) {
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        return Response.status(200).entity(userSession.getLock()).build();

    }

}
