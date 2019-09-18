/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.response.DashOtpInfoResponse;
import cbvmp.admin.data.manager.DashOtpInfoModelManager;
import cbvmp.admin.data.model.DashOtpInfoModel;
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
 * @author Administrator
 */
@Path("/dashboard")
public class DashOtpInfo {

    @GET
    @Path("/otp/info")
    @Produces(MediaType.APPLICATION_JSON)
    public DashOtpInfoResponse getOtpInfo(@HeaderParam("X-Requested-With") String requestType, @QueryParam("cmpo") Integer cmpoNo) {
        DashOtpInfoModelManager manager = new DashOtpInfoModelManager();
        List<DashOtpInfoModel> modelList = manager.getOtpInfo(cmpoNo);
        DashOtpInfoResponse response = new DashOtpInfoResponse();

        ArrayList<String> cmpoList = new ArrayList();
        ArrayList<Integer> consent = new ArrayList();
        ArrayList<Integer> unconsent = new ArrayList();
        ArrayList<String> colorCode = new ArrayList();
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
            for (DashOtpInfoModel model : modelList) {
                //System.out.println(model.getCmpo());
                cmpoList.add(model.getCmpo());
                consent.add(model.getConsentQty());
                unconsent.add(model.getUnconsentQty());
                colorCode.add(model.getColorCode());
            }
            response.setCmpoList(cmpoList);
            response.setConsent(consent);
            response.setUnconsent(unconsent);
            response.setColorCode(colorCode);

            return response;
        } else {
            return response;
        }
    }
}
