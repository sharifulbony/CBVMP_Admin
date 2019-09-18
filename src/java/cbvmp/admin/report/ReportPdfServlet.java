/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ReportDownloadServlet", urlPatterns = {"/report/pdf"})
public class ReportPdfServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReportDownloadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportDownloadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        byte[] excel =null;
         String DOWNLOAD_FILE_NAME = "REPORT.pdf";
           JasperDesign jasperDesign;
           JasperReport jasperReport;
  /**
   * File type.
   */
 String FILE_TYPE = "application/pdf";
     String reportPath;
        try {
            //processRequest(request, response);
           
            OutputStream outStream;
            JasperPrint jasperPrint = (JasperPrint) request.getSession().getAttribute("currentJp");
            
            JRPdfExporter exporter = null;
            String name =(String) request.getSession().getAttribute("name");
            
            //----response set header should be declared before output stream
            response.setHeader("Content-Disposition", "attachment; filename=\""+name+".pdf\""); 
            OutputStream  outputStream = response.getOutputStream();
            exporter = new JRPdfExporter();
            exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
            
            
//            JRXlsExporter exporter = new JRXlsExporter();
//            
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);            
//            exporter.exportReport();
//            excel = xlsReport.toByteArray();

//            response.reset();
            
            response.setContentType("application/pdf");
                      
            outputStream.close();
            
          

       
            


          
            
//            response.setHeader("Content-Disposition", "inline, filename=" + "report_sonet.pdf");
//            response.setContentType(FILE_TYPE);
//            outStream = response.getOutputStream();
//            JRExporter exporter = null;
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.exportReport();
            
//        for (int i=0;i<100;i++){
//            System.out.println("the size of jasper report"+ jasperPrint.toString());
//        
//        }
/* response.setContentType(FILE_TYPE);
response.setContentLength(byteStream.length);*/
/*exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("G:\\CBVMP\\admin\\web\\Jasper\\report.pdf"));
exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);*/
/*try {
//  exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
JasperExportManager.exportReportToPdfFile(jasperPrint,"G:\\CBVMP\\admin\\web\\Jasper\\report.pdf");
} catch (JRException ex) {
Logger.getLogger(ReportDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/     } catch (JRException ex) {
            Logger.getLogger(ReportPdfServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
