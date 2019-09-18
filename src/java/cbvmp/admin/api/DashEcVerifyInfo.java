/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.response.DashECVerificationResponse;
import cbvmp.admin.data.manager.DashECVerificationModelManager;
import cbvmp.admin.data.model.DashECVerificationModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rahat
 */
@Path("/dashboard")
public class DashEcVerifyInfo {

    @GET
    @Path("/ec/verify")
    @Produces(MediaType.APPLICATION_JSON)
    public DashECVerificationResponse getTotalEcVerified(@HeaderParam("X-Requested-With") String requestType, @QueryParam("cmpo") Integer cmpoNo) {
        DashECVerificationModelManager manager = new DashECVerificationModelManager();
        List<DashECVerificationModel> modelList = manager.getEcVerified(cmpoNo);
        DashECVerificationResponse response = new DashECVerificationResponse();

        ArrayList<String> cmpoList = new ArrayList();
        ArrayList<Integer> totalNid = new ArrayList();
        ArrayList<Integer> thumbNotFound = new ArrayList();
        ArrayList<Integer> nidOk = new ArrayList();
        ArrayList<Integer> nidNotOk = new ArrayList();
        ArrayList<String> colorCode = new ArrayList();

        if (requestType != null && requestType.equals("XMLHttpRequest")) {
            for (DashECVerificationModel model : modelList) {
                //System.out.println(model.getCmpo());
                cmpoList.add(model.getCmpo());
                totalNid.add(model.getTotalNid());
                thumbNotFound.add(model.getThumbNotFound());
                nidOk.add(model.getNidOk());
                nidNotOk.add(model.getNidNotOK());
                colorCode.add(model.getColorCode());
            }
            response.setCmpoList(cmpoList);
            response.setTotalNid(totalNid);
            response.setThumbNot(thumbNotFound);
            response.setNidNotOk(nidNotOk);
            response.setNidOk(nidOk);
            response.setColorCode(colorCode);

            return response;
        } else {
            return response;
        }
    }
}
