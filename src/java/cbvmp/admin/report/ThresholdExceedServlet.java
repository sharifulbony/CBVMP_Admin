/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report;

import cbvmp.admin.data.manager.DocumentTypeModelManager;
import cbvmp.admin.data.manager.PolicyMasterModelManager;
import cbvmp.admin.data.model.DocumentTypeModel;
import cbvmp.admin.data.model.PolicyMasterModel;
import cbvmp.admin.report.manager.ThresholdExceedModelManager;
import cbvmp.admin.report.model.ThresholdExceedModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "DocWiseThresholdExceedServlet", urlPatterns = {"/report/thres"})
public class ThresholdExceedServlet extends HttpServlet {

    int lastPageIndex;
    String limit;
    Integer docTypeParam;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DocWiseThresholdExceedServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DocWiseThresholdExceedServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    private void generateReport(HttpServletRequest request,
            HttpServletResponse response) {

        /**
         * Variable Declaration.
         */
        String reportPath, savedPath;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        Map reportParameters;
        lastPageIndex = 0;

        try {
            //reportPath = request.getSession().getServletContext().getRealPath("/Jasper") + "\\ThresholdExceed.jrxml";
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream input = classLoader.getResourceAsStream("cbvmp/jasper/jrxml/ThresholdExceed.jrxml");
            reportPath = "ThresholdExceed.jrxml";
            reportParameters = new HashMap();
            reportParameters.put("reportPath", reportPath);

            jasperDesign = JRXmlLoader.load(input);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            /**
             * Get report DataSource.
             */
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            PolicyMasterModelManager policyMasterModelManager = new PolicyMasterModelManager();
            List<PolicyMasterModel> policyMasterModel = policyMasterModelManager.listAll();
            String thresholdVal = null;
            // dateFormat date = new  SimpleDateFormat() ;
            for (PolicyMasterModel pm : policyMasterModel) {
                if (pm.getPolicyTypeNo() == 1 && pm.getPolicyStart().before(date) && pm.getPolicyEnd().after(date)) {
                    thresholdVal = pm.getPolicyValue();
//                  
                }
            }

            int highLimitParam = Integer.MAX_VALUE;
            int lowLimitParam = 0;
            limit = request.getParameter("limit");
            String[] range = limit.split("-");
            if (range.length == 1) {

                if (range[0].equals("1")) {
                    lowLimitParam = Integer.parseInt(thresholdVal) + 1;
                } else if (range[0].equals("1000")) {
                    lowLimitParam = Integer.parseInt(range[0]);
                }

            } else if (range.length == 2) {
                lowLimitParam = Integer.parseInt(range[0]);
                highLimitParam = Integer.parseInt(range[1]);
            }
//            for (int i = 0; i < 100; i++) {
//                System.out.println(lowLimitParam);
//                System.out.println(highLimitParam);
//                System.out.println("this is input ::::" + limit);
//                System.out.println("range lenth:" + range.length);
//                System.out.println("range 0 val");
//                System.out.println(Integer.parseInt(range[0]));
//                System.out.println("threshold:" + thresholdVal);
//
//            }

            docTypeParam = Integer.parseInt(request.getParameter("docType"));
            
            /**
             * ********-for retrieving doctypeModel name*****
             */

            String docTypeName = null;
            DocumentTypeModelManager documentTypeModelManager = new DocumentTypeModelManager();

            List<DocumentTypeModel> doclist = documentTypeModelManager.listAll();
            for (DocumentTypeModel dm : doclist) {
                if (dm.getDocTypeNo()==docTypeParam) {
                        docTypeName = dm.getDocType();
//                    System.out.println("name:"+docTypeName);
//                    System.out.println("dm.get:"+dm.getDocType());
                    }
            }
            if(docTypeParam == 10){
                
                docTypeName="All";
            }

            /**
             * **********end for doctype*******
             */
            if (limit.equals("1")) {
                limit = "Above Threshold";
            }

            reportParameters.put("docTypeName", docTypeName);
            reportParameters.put("limit", limit);

            ThresholdExceedModelManager thresholdManager = new ThresholdExceedModelManager();

            List<ThresholdExceedModel> list = null;

            list = thresholdManager.viewReport(docTypeParam, lowLimitParam, highLimitParam);

            reportSource = new JRBeanCollectionDataSource(list);

            JasperPrint jp = null;
            jp = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);
            HtmlExporter exporter = new HtmlExporter();

            //***For showing in html with pagination
            String reportName = "Threshold_Exceed_Report";
            request.getSession().setAttribute("name", reportName);
            String message = null;
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            HashMap<String, JasperPrint> jpMap = new HashMap();
            jpMap.put("THRESHOLD", jp);
            System.out.println("jmap" + jpMap.toString());

            request.getSession(false).setAttribute("docType", docTypeParam);
            request.getSession(false).setAttribute("limit", limit);

            if (jp.getPages().size() > 0) {
                System.out.println("jp: " + jp.getPages().size());
                lastPageIndex = jp.getPages().size() - 1;

                request.getSession().setAttribute("jpMap", jpMap);
                request.getSession().setAttribute("lastPageIndex", lastPageIndex);
                request.getSession().setAttribute("currentPageIndex", 0);
                response.sendRedirect(request.getContextPath() + "/report/thres");
            } else {

                message = "No data found";
                request.getSession().setAttribute("jpMap", null);
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/report/thres");
            }
            //       }
        } catch (Exception e) {
            System.out.println("Error***************");
            e.printStackTrace();
        }
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
        // processRequest(request, response);
        String page = request.getParameter("page");
        if (page != null && (Integer.parseInt(page) <= lastPageIndex)) {
            request.getSession(false).setAttribute("docType", docTypeParam);
            request.getSession(false).setAttribute("limit", limit);
            request.getSession().setAttribute("currentPageIndex", Integer.parseInt(page));
            request.getRequestDispatcher("/view/report/thresholdExceedSearch.jsp").forward(request, response);
        } else if (page != null && (Integer.parseInt(page) > lastPageIndex)) {
            request.getSession(false).setAttribute("docType", docTypeParam);
            request.getSession(false).setAttribute("limit", limit);
            request.getSession().setAttribute("currentPageIndex", 0);
            request.getRequestDispatcher("/view/report/thresholdExceedSearch.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/view/report/thresholdExceedSearch.jsp").forward(request, response);
        }
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
        // processRequest(request, response);
        generateReport(request, response);
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
