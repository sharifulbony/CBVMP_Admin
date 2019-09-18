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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ReportCsvServlet", urlPatterns = {"/report/csv"})
public class ReportCsvServlet extends HttpServlet {

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
            out.println("<title>Servlet ReportCsvServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportCsvServlet at " + request.getContextPath() + "</h1>");
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
        JasperPrint jasperPrint = null;
  //jp = csvExportService.getRawData(empIds);
   jasperPrint = (JasperPrint) request.getSession().getAttribute("currentJp");
//   for (int i = 0; i < 100; i++) {
//       if(jasperPrint  ==null){
//            System.out.println("the size of:::::::"+jasperPrint.toString());
//       }
//        }	

    String name =(String) request.getSession().getAttribute("name");
  response.setContentType("text/csv");
  response.setHeader("Content-Disposition", "attachment; filename="+name+".csv" );
  OutputStream out = response.getOutputStream();
  JRCsvExporter exporterCSV = new JRCsvExporter();
  exporterCSV.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
  exporterCSV.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, out);
        try {
            exporterCSV.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(ReportCsvServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

  out.flush();
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
