/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.deregistration;

import cbvmp.admin.data.manager.BulkEntryDetailsModelManager;
import cbvmp.admin.data.model.BulkEntryDetailsModel;
import cbvmp.admin.util.security.UrlSecurity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tanbir
 */
@WebServlet(name = "BulkDeregDetailShow", urlPatterns = {"/bulk/dereg/show/*"})
public class BulkDeregDetailShowServlet extends HttpServlet {

    private Integer id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        id = UrlSecurity.parseUrlParams(request.getPathInfo());
        request.setAttribute("requestId", id);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter out = response.getWriter();
//
//        DataTableObject dataTableObject = new DataTableObject();

        int amount = 0;
        int start = 0;
////        JsonMap jsonObject = new JsonMap();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        System.out.println("now started: " + sStart);
        System.out.println("now showed: " + sAmount);
////		String sCol = request.getParameter("iSortCol_0");
////		String sdir = request.getParameter("sSortDir_0");
//
        int count = 0;

        BulkEntryDetailsModelManager detailManager = new BulkEntryDetailsModelManager();
        List<BulkEntryDetailsModel> detailsList = detailManager.listAll(id);

        if (sStart != null) {
            start = Integer.parseInt(sStart);
            count = start + 1;
            if (start < 0) {
                start = 0;
            }
        }

        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (detailsList.size() < amount) {
                amount = detailsList.size();
            }
        }
        if (sStart != null && sAmount !=null) {
            int range = (start + amount) - 1;

            for (int i = start; i <= range; i++) {
//        for (BulkEntryDetailsModel bm : detailsList) {

                JSONArray ja = new JSONArray();
                if (i > detailsList.size() - 1) {
                    break;
                } else {

//                ja.put(bm.getMsisdn());
//                ja.put(bm.getStatus());
//                ja.put(bm.getRefSimNo());
//                ja.put(bm.getIsExecute());
//                ja.put(bm.getExecuteTime());                
//                        
                    ja.put(count);
                    ja.put(detailsList.get(i).getMsisdn());
                    if(detailsList.get(i).getStatus()==0)
                    {
                        ja.put("Not Processed");
                    }
                    else if(detailsList.get(i).getStatus()==1)
                    {
                        
                       ja.put("Not Found");
                    }
                    else if(detailsList.get(i).getStatus()==2)
                    {
                        ja.put("Deregistered");
                    }
                    else{
                        ja.put("Registered");
                    }
                    
                    ja.put(detailsList.get(i).getRefSimNo());
                    if(detailsList.get(i).getIsExecute()==0)
                    {
                        ja.put("NO");
                    }
                    else{
                        ja.put("YES");
                    }
                    
                    ja.put(detailsList.get(i).getExecuteTime());
                    array.put(ja);
                }
                count++;

            }

        }

//        List<BulkEntryDetailsModel> smallList = null;
//        smallList = detailsList.subList(start, amount);
        System.out.println("data found: " + array.length());
        try {
            result.put("iTotalRecords", detailsList.size());
            result.put("iTotalDisplayRecords", detailsList.size());
            result.put("aaData", array);
        } catch (Exception e) {

        }

        out.print(result);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

//        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
