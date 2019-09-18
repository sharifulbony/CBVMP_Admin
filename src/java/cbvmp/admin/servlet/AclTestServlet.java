/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet;

import cbvmp.admin.util.security.Module;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanbir
 */
@WebServlet(name = "ModuleName", urlPatterns = {"/module/edit/", "/module/delete/"}, initParams = {
    @WebInitParam(name = "sevrletPackage", value = "cbvmp.admin.servlet")})
public class AclTestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String getAction(HttpServletRequest request) {
        return request.getRequestURI().split("/")[3];

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = getAction(request);

        PrintWriter out = response.getWriter();
        try {
            out.println("Requested Action:" + action);
            /* TODO output your page here. You may use following sample code. */
            URL location = AclTestServlet.class.getProtectionDomain().getCodeSource().getLocation();
            File folder = new File(location.getFile() + "/cbvmp/admin/servlet");
            File[] listOfFiles = folder.listFiles();
            
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String className = listOfFile.getName().replaceFirst("[.][^.]+$", "");
                    Class<?> clazz = Class.forName(getInitParameter("sevrletPackage") + "." + className);
                    WebServlet webServlet = clazz.getAnnotation(WebServlet.class);
                    out.print(webServlet.name() + " ");
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method m : methods) {
//                        m.setAccessible(true);
                        Module moduleAction = m.getAnnotation(Module.class);
                        if (moduleAction != null) {
                            out.print(moduleAction.name() + "(" + moduleAction.url() + ")");
                        }
                    }
                }
                out.println();
            }

        } catch (ClassNotFoundException ex) {
            out.println(ex.getMessage());
        } finally {
            out.close();
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
        processRequest(request, response);
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

//    @Module(name = "Edit", url = "/module/edit")
    private void editRequest() {

    }

//    @Module(name = "Delete", url = "/module/delete")
    private void deleteRequest() {

    }

}
