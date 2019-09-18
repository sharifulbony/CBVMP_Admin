/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.response.TotalSimRegistrationResponse;
import cbvmp.admin.data.manager.TotalSimCountModelManager;
import cbvmp.admin.data.model.TotalSimCountModel;
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
public class TotalSimRegistration {

    @GET
    @Path("/total/sim/reg")
    @Produces(MediaType.APPLICATION_JSON)
    public TotalSimRegistrationResponse findTotalSimRegistration(@HeaderParam("X-Requested-With") String requestType,@QueryParam("cmpo") Integer cmpoNo) {

        TotalSimRegistrationResponse res = new TotalSimRegistrationResponse();
        ArrayList<Integer> dataList = new ArrayList();
        ArrayList<String> cmpoList = new ArrayList();
        ArrayList<String> colorList = new ArrayList();
        TotalSimCountModelManager totalSimCountManager = new TotalSimCountModelManager();
        
        ArrayList<TotalSimCountModel> totalSimCountModel =  (ArrayList<TotalSimCountModel>) totalSimCountManager.geTotalSim(cmpoNo);
        
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
        
        for(TotalSimCountModel tm: totalSimCountModel){
            dataList.add(tm.getSimCount());
            cmpoList.add(tm.getCmpo());
            colorList.add(tm.getColorCode());
        }
        res.setCmpoList(cmpoList);
        res.setDataList(dataList);
        res.setColorCode(colorList);
        return res;
        }else{
            return res;
        }

    }
}
