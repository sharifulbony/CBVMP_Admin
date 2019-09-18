/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report;

import cbvmp.admin.data.manager.CmpoModelManager;
import cbvmp.admin.data.model.CmpoModel;
import cbvmp.admin.report.manager.SimVerCmpoWiseModelManager;
import cbvmp.admin.report.model.SimVerCmpoWiseModel;
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
@WebServlet(name = "SimVerOperWiseServlet", urlPatterns = {"/report/svop"})
public class SimVerOperWiseServlet extends HttpServlet {

    public int lastPageIndex;
    String cmpoParam;

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
            out.println("<title>Servlet SimVerOperWiseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SimVerOperWiseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        JasperPrint jp = null;

        try {
           // reportPath = request.getSession().getServletContext().getRealPath("/Jasper") + "\\SimVerOperWise.jrxml";
           ClassLoader classLoader = getClass().getClassLoader();
            InputStream input = classLoader.getResourceAsStream("cbvmp/jasper/jrxml/SimVerOperWise.jrxml");
            reportPath = "SimVerOperWise.jrxml";
            

            jasperDesign = JRXmlLoader.load(input);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            /**
             * Get report DataSource.
             */
            
            
            cmpoParam = request.getParameter("cmpo");
            if(cmpoParam.isEmpty()){
                cmpoParam= null;
            
            }
           
           /*************for retrieving cmpoModel name************************/
            String cmpoName =null;
             //int cmpoNo= Integer.BYTES;
            CmpoModelManager cmpoModelManager = new CmpoModelManager();
            List <CmpoModel> cmpolist = cmpoModelManager.listAll(7);
            for(CmpoModel cm : cmpolist ){
                if(cmpoParam==null){
                    cmpoName="All Operator";
                }
                else if(cm.getId()==Integer.parseInt(cmpoParam)){
                    cmpoName = cm.getCmpoName();
                }
            }
            
            
             /************end for cmpo********/
           String reportName = "Sim_Verification_Operator_Wise";
            request.getSession().setAttribute("name", reportName);
            SimVerCmpoWiseModelManager simOperManager = new SimVerCmpoWiseModelManager();

            List<SimVerCmpoWiseModel> list = null;

            list = simOperManager.viewReport(cmpoParam);

            reportSource = new JRBeanCollectionDataSource(list);
            
            
            reportParameters = new HashMap();
            reportParameters.put("reportPath", reportPath);
            reportParameters.put("cmpoName",cmpoName);

            jp = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);

            //***For showing in html with pagination
            HtmlExporter exporter = new HtmlExporter();
            String message = null;
            lastPageIndex = 0;
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

            //StringBuffer sbuffer = new StringBuffer();
            HashMap<String, JasperPrint> jpMap = new HashMap();
            jpMap.put("SIMVEROP", jp);
            System.out.println("jmap" + jpMap.toString());
            
            
            request.getSession(false).setAttribute("cmpoParam", cmpoParam);

            if (jp.getPages().size() > 0) {
                System.out.println("jp: " + jp.getPages().size());
                lastPageIndex = jp.getPages().size() - 1;

                request.getSession().setAttribute("jpMap", jpMap);
                request.getSession().setAttribute("lastPageIndex", lastPageIndex);
                request.getSession().setAttribute("currentPageIndex", 0);
                response.sendRedirect(request.getContextPath() + "/report/svop");
            } else {

                message = "No data found";
                request.getSession().setAttribute("jpMap", null);
                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/report/svop");
            }
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
        //int index =  Integer.parseInt(page);
        if (page != null && (Integer.parseInt(page) <= lastPageIndex)) {
            request.getSession().setAttribute("cmpoParam", cmpoParam);
            request.getSession().setAttribute("currentPageIndex", Integer.parseInt(page));
            request.getRequestDispatcher("/view/report/simVerOperSearch.jsp").forward(request, response);
        } else if (page != null && (Integer.parseInt(page) > lastPageIndex)) {
            request.getSession().setAttribute("cmpoParam", cmpoParam);
            request.getSession().setAttribute("currentPageIndex", 0);
            request.getRequestDispatcher("/view/report/simVerOperSearch.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/view/report/simVerOperSearch.jsp").forward(request, response);
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
