/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.request.AccessControlRequest;
import cbvmp.admin.api.response.ChildNodeResponse;
import cbvmp.admin.api.response.ParentNodeResponse;
import cbvmp.admin.data.manager.AccessControlListManager;
import cbvmp.admin.data.manager.AccessPermissionModelManager;
import cbvmp.admin.data.model.GetAccessControlListModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tanbir
 */
@Path("/access")
public class AccessControlApi {

    @GET
    @Path("/view/{roleId}/{userCat}") //parameters: 
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedList<ParentNodeResponse> getAccessControlList(@HeaderParam("X-Requested-With") String requestType, @PathParam("roleId") Integer roleId, @PathParam("userCat") Integer userCategory) {
        HashMap<Integer, ParentNodeResponse> response = new HashMap();

        AccessControlListManager aclManager = new AccessControlListManager();
        List<GetAccessControlListModel> aclList = aclManager.listAll(roleId, userCategory);
        LinkedList<ParentNodeResponse> responseList = new LinkedList<ParentNodeResponse>();

        if (requestType != null && requestType.equals("XMLHttpRequest")) {
            for (GetAccessControlListModel acl : aclList) {
                if (acl.getParentId() < 0) {
                    if (response.get(acl.getId()) == null) {
                        ParentNodeResponse parent = new ParentNodeResponse();
                        parent.setTitle(acl.getAlias());
                        parent.setKey(String.valueOf(acl.getId()));
                        parent.setExpand(true);
                        parent.setSelect(acl.isAllowed());
                        response.put(acl.getId(), parent);

                    }

                } else if (acl.getParentId() > 0) {
                    ChildNodeResponse child = new ChildNodeResponse();
                    child.setKey(String.valueOf(acl.getId()));
                    child.setSelect(acl.isAllowed());
                    child.setTitle(acl.getAlias());
                    response.get(acl.getParentId()).getChildren().add(child);

                }

            }

            for (Integer key : response.keySet()) {
                responseList.add(response.get(key));
            }

            return responseList;
        } else {
            return responseList;
        }

    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)

    public Response saveAccessControlList(List<AccessControlRequest> aclRequest) {
        AccessPermissionModelManager aclManager = new AccessPermissionModelManager();
        for (AccessControlRequest acl : aclRequest) {
            aclManager.updateOrSave(acl.getId(), acl.getRoleId(), acl.isSelected());
        }
        return Response.status(200).entity("Data saved").build();
    }

}
