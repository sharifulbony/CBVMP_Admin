/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.response.TotalSimAgainstDocResponse;
import cbvmp.admin.data.manager.TotalSimAgainstDocModelManager;
import cbvmp.admin.data.model.TotalSimAgainstDocModel;
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
public class TotalSIMAgainstDoc {
    
    @Path("/total/sim/doc")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public TotalSimAgainstDocResponse getSimCountByDocument(@HeaderParam("X-Requested-With") String requestType,@QueryParam("cmpo")Integer cmpoNo){
        TotalSimAgainstDocResponse res = new TotalSimAgainstDocResponse();
        TotalSimAgainstDocModelManager manager = new TotalSimAgainstDocModelManager();
        ArrayList<TotalSimAgainstDocModel> modelList = (ArrayList<TotalSimAgainstDocModel>) manager.geTotalSim(cmpoNo);
        ArrayList<Integer> simCount = new ArrayList();
        ArrayList<String> docType = new ArrayList();
        ArrayList<String> colorList = new ArrayList();
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
        for(TotalSimAgainstDocModel mod:modelList){
            simCount.add(mod.getSimCount());
            docType.add(mod.getDocumentType());
            colorList.add(mod.getColorCode());
        }
        
        res.setDocType(docType);
        res.setSimCount(simCount);
        res.setColorCode(colorList);
        return res;
        }
        else{
            return res;
        }
    }
    
    
}
