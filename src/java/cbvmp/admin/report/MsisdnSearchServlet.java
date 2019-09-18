/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report;

import cbvmp.admin.data.manager.CmpoModelManager;
import cbvmp.admin.data.model.CmpoModel;
import cbvmp.admin.report.manager.MsisdnSearchModelManager;
import cbvmp.admin.report.model.MsisdnSearchHistoryModel;
import cbvmp.admin.report.model.MsisdnSearchModel;
import com.google.common.collect.HashBiMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author farhan
 */
@WebServlet(name = "MsisdnSearchServlet", urlPatterns = {"/report/msisdn/search"})
public class MsisdnSearchServlet extends HttpServlet {

    
        String msisdn;
        Integer cmpo;
        
        
        
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
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MsisdnSearchServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MsisdnSearchServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        
        request.getRequestDispatcher("/view/report/msisdnSearch.jsp").forward(request, response);
//        processRequest(request, response);
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
        
        
        
        String reportPath, subPath,savedPath;
        
        JasperReport jasperReport;
        JasperReport subReport;
        
        JasperDesign jasperDesign;
        JasperDesign subDesign;
        
        JRDataSource reportSource;
        JRDataSource subReportSource;
        
        Map reportParameters;
        Map subReportParameters;
       
        try {

            //reportPath = request.getSession().getServletContext().getRealPath("/Jasper/MsisdnAgnDoc.jrxml");
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream input = classLoader.getResourceAsStream("cbvmp/jasper/jrxml/MsisdnSearch.jrxml");
            InputStream subInput = classLoader.getResourceAsStream("cbvmp/jasper/jrxml/MsisdnSearch_History.jrxml");
            reportPath = "MsisdnSearch.jrxml";
            subPath="MsisdnSearch_History.jrxml";
            

            jasperDesign = JRXmlLoader.load(input);
            subDesign = JRXmlLoader.load(subInput);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            subReport = JasperCompileManager.compileReport(subDesign);
             //subReport = JasperCompileManager.compileReport(subPath);
             
             
            //subReport = (JasperReport)JRLoader.loadObject(new File("msisdn_search_history.jasper"));

            msisdn=request.getParameter("msisdn");
            cmpo=Integer.parseInt(request.getParameter("cmpo"));
          
            reportParameters = new HashMap();
            subReportParameters=new HashMap();

            String cmpoName = null;
            //int cmpoNo= Integer.BYTES;
            CmpoModelManager cmpoModelManager = new CmpoModelManager();
            List<CmpoModel> cmpolist = cmpoModelManager.listAll(7);
           
              for (CmpoModel cm : cmpolist) {
                    //cm.setId(cmpo); 
                   if (cm.getId() == cmpo) {
                    cmpoName = cm.getCmpoName();
                }
                    //cmpoName = cm.getCmpoName();
            }

            /**
             * **********end for cmpo*******
             */
            reportParameters.put("reportPath", reportPath);
            
            subReportParameters.put("subPath",subPath);
            // reportParameters.put("docTypeName",request.getParameter("docType"));
          
            reportParameters.put("msisdn", msisdn);
            reportParameters.put("cmpoName", cmpoName);

            String reportName = "MSISDN_Search";
            request.getSession().setAttribute("name", reportName);
               
            
        MsisdnSearchModelManager msisdnSearchManager = new MsisdnSearchModelManager();
        List<MsisdnSearchModel> msisdnSearchModel =msisdnSearchManager.searchMsisdn(cmpo,msisdn);
        
       MsisdnSearchModelManager msisdnSearchHistoryManager = 
               new MsisdnSearchModelManager();
       List<MsisdnSearchHistoryModel> msisdnSearchHistoryModel = 
               msisdnSearchManager.searchMsisdnHistory(cmpo,msisdn);

            reportSource = new JRBeanCollectionDataSource(msisdnSearchModel);
            subReportSource=new JRBeanCollectionDataSource(msisdnSearchHistoryModel);

            JasperPrint jp = null;
            
            JasperPrint subJP = null;
            
            jp = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);
            
            subJP = JasperFillManager.fillReport(subReport, subReportParameters, subReportSource);
            
            HtmlExporter exporter = new HtmlExporter();

            //String pageStr = null;
            String message = null;
//            lastPageIndex = 0;
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

            //StringBuffer sbuffer = new StringBuffer();
            HashMap<String, JasperPrint> jpMap = new HashMap();
            jpMap.put("SEARCH", jp);
            System.out.println("jmap" + jpMap.toString());

         
            request.getSession().setAttribute("cmpo", cmpo);
            request.getSession().setAttribute("msisdn", msisdn);
            if (jp.getPages().size() > 0) {
                System.out.println("jp: " + jp.getPages().size());
//                lastPageIndex = jp.getPages().size() - 1;
                request.getSession().setAttribute("jpMap", jpMap);
//                request.getSession().setAttribute("lastPageIndex", lastPageIndex);
//                request.getSession().setAttribute("currentPageIndex", 0);
                response.sendRedirect(request.getContextPath() + "/report/msisdn/search");
            } else {

                message = "No data found";

                request.getSession().setAttribute("jpMap", null);
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/report/msisdn/search");
            }
            //      }
        } catch (Exception e) {
            System.out.println("Error***************");
            e.printStackTrace();
        }
        
        
        
        
        
        
//        processRequest(request, response);
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
