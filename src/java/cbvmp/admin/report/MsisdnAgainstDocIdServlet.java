/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report;

import cbvmp.admin.data.manager.CmpoModelManager;
import cbvmp.admin.data.manager.DocumentTypeModelManager;
import cbvmp.admin.data.model.CmpoModel;
import cbvmp.admin.data.model.DocumentTypeModel;
import cbvmp.admin.report.manager.MsisdnAgainstDocIdModelManager;
import cbvmp.admin.report.model.MsisdnAgainstDocIdModel;
//import com.JasperDataBean;
import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

@WebServlet(name = "MsisdnDocId", urlPatterns = {"/report/msisdn"})
public class MsisdnAgainstDocIdServlet extends HttpServlet {

    Integer lastPageIndex;
    Integer docType;
    String docTypeNo;
    String cmpo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * This method is used to Generate and download JasperReport in pdf format.
     *
     * @param HttpServletResponse object
     */
    private void generateReport(HttpServletRequest request,
            HttpServletResponse response) {
        /**
         * Variable Declaration.
         *
         */
        String reportPath, savedPath;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        Map reportParameters;

        try {

            //reportPath = request.getSession().getServletContext().getRealPath("/Jasper/MsisdnAgnDoc.jrxml");
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream input = classLoader.getResourceAsStream("cbvmp/jasper/jrxml/MsisdnAgnDoc.jrxml");
            reportPath = "MsisdnAgnDoc.jrxml";

            jasperDesign = JRXmlLoader.load(input);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            /**
             * Get report DataSource.
             */
            docType = Integer.parseInt(request.getParameter("docType"));
            docTypeNo = request.getParameter("docTypeNo");
            cmpo = request.getParameter("cmpo");
            if (cmpo.isEmpty()) {
                cmpo = null;
            }
            reportParameters = new HashMap();
            /**
             * ********-for retrieving doctypeModel name*****
             */

            String docTypeName = null;
            DocumentTypeModelManager documentTypeModelManager = new DocumentTypeModelManager();

            List<DocumentTypeModel> doclist = documentTypeModelManager.listAll();
            for (DocumentTypeModel dm : doclist) {
                if (dm.getDocTypeNo() == docType) {
                    docTypeName = dm.getDocType();
                    System.out.println("name:" + docTypeName);
                    System.out.println("dm.get:" + dm.getDocType());
                }
            }

            /**
             * **********end for doctype*******
             */
            /**
             * ***********for retrieving cmpoModel name***********************
             */
            String cmpoName = null;
            //int cmpoNo= Integer.BYTES;
            CmpoModelManager cmpoModelManager = new CmpoModelManager();
            List<CmpoModel> cmpolist = cmpoModelManager.listAll(7);
            for (CmpoModel cm : cmpolist) {
                if (cmpo == null) {
                    cmpoName = "All Operator";
                } else if (cm.getId() == Integer.parseInt(cmpo)) {
                    cmpoName = cm.getCmpoName();
                }
            }

            /**
             * **********end for cmpo*******
             */
            reportParameters.put("reportPath", reportPath);
            // reportParameters.put("docTypeName",request.getParameter("docType"));
            reportParameters.put("docTypeName", docTypeName);
            reportParameters.put("docTypeNo", docTypeNo);
            reportParameters.put("cmpoName", cmpoName);

            String reportName = "MSISDN_Against_Document_ID";
            request.getSession().setAttribute("name", reportName);
            MsisdnAgainstDocIdModelManager msisdnAgainstNidModelManager = new MsisdnAgainstDocIdModelManager();

            List<MsisdnAgainstDocIdModel> list = null;
            list = msisdnAgainstNidModelManager.viewReport(docType, docTypeNo, cmpo);

            reportSource = new JRBeanCollectionDataSource(list);

            JasperPrint jp = null;
            jp = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);
            HtmlExporter exporter = new HtmlExporter();

            //String pageStr = null;
            String message = null;
            lastPageIndex = 0;
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

            //StringBuffer sbuffer = new StringBuffer();
            HashMap<String, JasperPrint> jpMap = new HashMap();
            jpMap.put("MSISDN", jp);
            System.out.println("jmap" + jpMap.toString());

            request.getSession().setAttribute("docTypeNo", docTypeNo);
            request.getSession().setAttribute("cmpo", cmpo);
            request.getSession().setAttribute("docType", docType);
            if (jp.getPages().size() > 0) {
                System.out.println("jp: " + jp.getPages().size());
                lastPageIndex = jp.getPages().size() - 1;
                request.getSession().setAttribute("jpMap", jpMap);
                request.getSession().setAttribute("lastPageIndex", lastPageIndex);
                request.getSession().setAttribute("currentPageIndex", 0);
                response.sendRedirect(request.getContextPath() + "/report/msisdn");
            } else {

                message = "No data found";

                request.getSession().setAttribute("jpMap", null);
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/report/msisdn");
            }
            //      }
        } catch (Exception e) {
            System.out.println("Error***************");
            e.printStackTrace();
        }
    }

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

        // other report sessions deleted;
        String page = request.getParameter("page");
        //int index =  Integer.parseInt(page);
        if (page != null && (Integer.parseInt(page) <= lastPageIndex)) {
            request.getSession().setAttribute("docTypeNo", docTypeNo);
            request.getSession().setAttribute("cmpo", cmpo);
            request.getSession().setAttribute("docType", docType);
            request.getSession().setAttribute("currentPageIndex", Integer.parseInt(page));
            request.getRequestDispatcher("/view/report/msisdnAgainstNidSearch.jsp").forward(request, response);
        } else if (page != null && (Integer.parseInt(page) > lastPageIndex)) {
            request.getSession().setAttribute("currentPageIndex", 0);
            request.getRequestDispatcher("/view/report/msisdnAgainstNidSearch.jsp").forward(request, response);

        } else {
            //request.getSession().removeAttribute("jpMap");
            request.getRequestDispatcher("/view/report/msisdnAgainstNidSearch.jsp").forward(request, response);
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
        //System.out.println("Posting....");

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
