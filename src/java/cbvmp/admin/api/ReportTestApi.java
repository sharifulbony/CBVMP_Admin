/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api;

import cbvmp.admin.api.request.DataTableRequest;
import cbvmp.admin.api.response.DataResponse;
import cbvmp.admin.api.response.ReportResponse;
import cbvmp.admin.api.response.ThresholdExceedResponse;
import cbvmp.admin.data.manager.ReportModelManager;
import cbvmp.admin.data.model.ReportModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author Administrator
 */
@Path("report")
public class ReportTestApi {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger(ReportTestApi.class);

    @POST
    @Path("/show") //parameters: 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ReportResponse getAccessControlList(@Context HttpServletRequest request, DataTableRequest dtRequest) {

        logger.debug("Column Size " + dtRequest.getColumns().get(0).getSearch().getValue());
        logger.debug("Order Size " + dtRequest.getOrder().size());
        logger.debug("Search value " + dtRequest.getSearch().getValue());

        int limitStart = dtRequest.getStart();
        int length = dtRequest.getLength();
        int simCountParam = 0;
        int docTypeParam = -1;
        String nidParam = "";
//        String orderByParam = "";
//        String orderDirParam = "";
        ReportModelManager reportModelManager = new ReportModelManager();
        List<ReportModel> reportList = new ArrayList<ReportModel>();

        reportList = reportModelManager.viewReport(limitStart, length, simCountParam, nidParam, docTypeParam);

        ReportResponse response = new ReportResponse();
        
        response.setDraw(dtRequest.getDraw());
        if (reportList.isEmpty()) {
            response.setRecordsTotal(0);
        } else {
            response.setRecordsTotal(reportList.get(0).getColumnCount());
        }

        nidParam = dtRequest.getColumns().get(0).getSearch().getValue();
        String simCount = dtRequest.getColumns().get(1).getSearch().getValue();
        if (simCount != null && !simCount.isEmpty()) {
            simCountParam = Integer.parseInt(simCount);
        }
        String docType = dtRequest.getColumns().get(2).getSearch().getValue();
        if (docType != null && !docType.isEmpty()) {
            docTypeParam = Integer.parseInt(docType);
        }

        reportList = reportModelManager.viewReport(limitStart, length, simCountParam, nidParam, docTypeParam);
        List<DataResponse> finalData = new ArrayList();
        
        if (reportList.isEmpty()) {
            response.setRecordsFiltered(0);
        } else {
            response.setRecordsFiltered(reportList.get(0).getColumnCount());
        }
        

        for (ReportModel reportModel : reportList) {
            DataResponse data = new DataResponse();
            data.setDocTypNo(Long.toString(reportModel.getDocTypeNo()));
            data.setNidNo(reportModel.getNID());
            data.setSimCount(Long.toString(reportModel.getSimCount()));

            finalData.add(data);
        }
        response.setData(finalData);
        return response;

    }

    @GET
    @Path("/show") //parameters: 
    @Produces(MediaType.APPLICATION_JSON)
    public ThresholdExceedResponse getReportData(@Context HttpServletRequest request) {
        
        ThresholdExceedResponse thResponse = new ThresholdExceedResponse();
        thResponse.setDraw(1);
        thResponse.setRecordsFiltered(1);
        thResponse.setRecordsTotal(10);
        return thResponse;
        

    }

}
