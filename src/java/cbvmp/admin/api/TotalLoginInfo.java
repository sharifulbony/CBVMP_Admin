/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.response.TotalLoginInfoResponse;
import cbvmp.admin.data.manager.TotalLoginInfoModelManager;
import cbvmp.admin.data.model.TotalLoginInfoModel;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tanbir
 */
@Path("/dashboard")
public class TotalLoginInfo {
    
    
    @GET
    @Path("/total/login")
    @Produces(MediaType.APPLICATION_JSON)
    public TotalLoginInfoResponse getTotalLoginInfo(@HeaderParam("X-Requested-With") String requestType,@QueryParam("cmpo")Integer cmpoNo){
        
        TotalLoginInfoModelManager manager = new TotalLoginInfoModelManager();
        ArrayList<TotalLoginInfoModel> modelList = (ArrayList)manager.geTotalLoginInfo(cmpoNo);
        TotalLoginInfoResponse response = new TotalLoginInfoResponse();
        ArrayList<String> category = new ArrayList(); 
        ArrayList<String> cmpoId = new ArrayList(); 
        ArrayList<String> colorCode = new ArrayList(); 
        ArrayList<Integer> totalCount = new ArrayList(); 
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
        for(TotalLoginInfoModel model:modelList){
            category.add(model.getCategory());
            cmpoId.add(model.getCmpoId());
            colorCode.add(model.getColorCode());
            totalCount.add(model.getTotalCount());
        }
        
        response.setCategory(category);
        response.setCmpoId(cmpoId);
        response.setColorCode(colorCode);
        response.setTotalCount(totalCount);
        return response;
        }else{
            return response;
        }
        
    }
}
