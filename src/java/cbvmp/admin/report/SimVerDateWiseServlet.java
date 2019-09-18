/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report;


import cbvmp.admin.report.manager.SimVerDateWiseModelManager;
import cbvmp.admin.report.model.SimVerDateWiseModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
@WebServlet(name = "SimVerDateWiseServlet", urlPatterns = {"/report/svdate"})
public class SimVerDateWiseServlet extends HttpServlet {
        public int lastPageIndex;
        String dTo;
        String dFrom;
        String groupByTimeParam;
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
            out.println("<title>Servlet SimVerDateWiseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SimVerDateWiseServlet at " + request.getContextPath() + "</h1>");
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
    
     private void generateReport(HttpServletRequest request,
            HttpServletResponse response) {

        /**
         * Variable Declaration.
         */ 
        String reportPath, savedPath;        
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource ;       
        Map reportParameters;
        JasperPrint jp = null;

        try {    
           // reportPath = request.getSession().getServletContext().getRealPath("/Jasper") + "\\SimVerDateWise.jrxml";
            //   reportPath = request.getSession().getServletContext().getRealPath("/Jasper") + "\\SimVerDateWise.jrxml";
       
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream input = classLoader.getResourceAsStream("cbvmp/jasper/jrxml/SimVerDateWise.jrxml");
            reportPath = "SimVerDateWise.jrxml";  
            
            

            jasperDesign = JRXmlLoader.load(input);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            /**
             * Get report DataSource.
             */
          
           
             groupByTimeParam =request.getParameter("groupByTime");
            
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            
            
             dFrom =  request.getParameter("dateFrom");
             dTo =  request.getParameter("dateTo");
             Date dateFrom =  dateFormat.parse(dFrom);
            Date dateTo =  dateFormat.parse(dTo);

            SimVerDateWiseModelManager simVerDateWiseManager = new SimVerDateWiseModelManager();

            List<SimVerDateWiseModel> list = null;
            
            list = simVerDateWiseManager.viewReport(groupByTimeParam, dateFrom, dateTo);

            /**** for grpby ***/
            String group= null;
            String groupHead=null;
            switch (groupByTimeParam) {
                case "dd-mm-yyyy":
                    group="Daily Report";
                    groupHead="Date";
                    break;
                case "IW \" of \" yyyy" :
                    group="Weekly Report";
                    groupHead="Week No";
                    break;
                case "MONTH yyyy":
                    group="Monthly Report";
                    groupHead="Month";
                    break;
                case "q \" of \" yyyy":
                    group="Quarterly Report";
                    groupHead="Quarter No";
                    break;
                case "yyyy":
                    group="Yearly Report";
                    groupHead="Year";
                    break;
                    
                default:
                    group="Report error";
                    break;
            }
           

            reportParameters = new HashMap();
            reportParameters.put("reportPath", reportPath);
            reportParameters.put("dateFrom",request.getParameter("dateFrom"));
            reportParameters.put("dateTo",request.getParameter("dateTo"));
            reportParameters.put("group",group);
            reportParameters.put("groupHead",groupHead);
            
            
            reportSource = new JRBeanCollectionDataSource(list);

            
             jp = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);
           
            



            //***For showing in html with pagination
            
            
            String reportName = "Sim_Verification_Date_Wise";
            request.getSession().setAttribute("name", reportName);
            HtmlExporter exporter = new HtmlExporter();
            String message = null;
            lastPageIndex = 0;
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

            //StringBuffer sbuffer = new StringBuffer();
            HashMap<String, JasperPrint> jpMap = new HashMap();
            jpMap.put("SIMVERDATE", jp);
            System.out.println("jmap" + jpMap.toString());
            
            request.getSession(false).setAttribute("dFrom", dFrom);
            request.getSession(false).setAttribute("dTo", dTo);
            request.getSession(false).setAttribute("groupByTimeParam", groupByTimeParam);
            
            
            if (jp.getPages().size() > 0) {
                System.out.println("jp: " + jp.getPages().size());
                lastPageIndex = jp.getPages().size() - 1;

                request.getSession().setAttribute("jpMap", jpMap);
                request.getSession().setAttribute("lastPageIndex", lastPageIndex);
                request.getSession().setAttribute("currentPageIndex", 0);
                response.sendRedirect(request.getContextPath() + "/report/svdate");
            } else {

                message = "No data found";
                request.getSession().setAttribute("jpMap", null);
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/report/svdate");
            }
            //       }
        } catch (Exception e) {
            System.out.println("Error***************");
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        String page = request.getParameter("page");
        //int index =  Integer.parseInt(page);
        if (page != null && (Integer.parseInt(page) <= lastPageIndex)) {
            request.getSession(false).setAttribute("dFrom", dFrom);
            request.getSession(false).setAttribute("dTo", dTo);
            request.getSession(false).setAttribute("groupByTimeParam", groupByTimeParam);
            request.getSession().setAttribute("currentPageIndex", Integer.parseInt(page));
            request.getRequestDispatcher("/view/report/simVerDateSearch.jsp").forward(request, response);
        } else if (page != null && (Integer.parseInt(page) > lastPageIndex)) {
            request.getSession(false).setAttribute("dFrom", dFrom);
            request.getSession(false).setAttribute("dTo", dTo);
            request.getSession(false).setAttribute("groupByTimeParam", groupByTimeParam);
            request.getSession().setAttribute("currentPageIndex", 0);
            request.getRequestDispatcher("/view/report/simVerDateSearch.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/view/report/simVerDateSearch.jsp").forward(request, response);
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
      //  processRequest(request, response);
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
