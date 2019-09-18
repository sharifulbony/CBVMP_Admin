/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.response.TotalBulkDeregResponse;
import cbvmp.admin.api.response.TotalLoginInfoResponse;
import cbvmp.admin.data.manager.TotalBulkDeregModelManager;
import cbvmp.admin.data.manager.TotalLoginInfoModelManager;
import cbvmp.admin.data.model.TotalBulkDeregModel;
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
public class TotalBulkDereg {

    @GET
    @Path("/total/bulkdereg")
    @Produces(MediaType.APPLICATION_JSON)
    public TotalBulkDeregResponse getTotalBulkDereg(@HeaderParam("X-Requested-With") String requestType, @QueryParam("cmpo") Integer cmpoNo) {

        TotalBulkDeregModelManager manager = new TotalBulkDeregModelManager();
        ArrayList<TotalBulkDeregModel> modelList = (ArrayList<TotalBulkDeregModel>) manager.geTotalBulkDereg(cmpoNo);
        TotalBulkDeregResponse response = new TotalBulkDeregResponse();

        ArrayList<String> cmpoList = new ArrayList();
        ArrayList<Integer> totalValidMsisdn = new ArrayList();
        ArrayList<Integer> totalExecuted = new ArrayList();
        ArrayList<String> colorCode = new ArrayList();

        if (requestType != null && requestType.equals("XMLHttpRequest")) {
            for (TotalBulkDeregModel model : modelList) {
                cmpoList.add(model.getCmpo());
                totalValidMsisdn.add(model.getTotalValidMsisdn());
                totalExecuted.add(model.getTotalCountExcuted());
                colorCode.add(model.getColorCode());
            }
            response.setCmpoList(cmpoList);
            response.setColorCode(colorCode);
            response.setTotalExecuted(totalExecuted);
            response.setTotalValidMsisdn(totalValidMsisdn);

            return response;
        } else {
//            response.setCmpoList(null);
//            response.setColorCode(null);
//            response.setTotalExecuted(null);
//            response.setTotalValidMsisdn(null);
            return response;
        }

    }
}
