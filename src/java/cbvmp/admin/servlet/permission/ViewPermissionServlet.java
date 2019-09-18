/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.permission;

import cbvmp.admin.data.model.PermissionModel;
import cbvmp.admin.servlet.AclTestServlet;
import cbvmp.admin.util.security.Module;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.LinkedList;
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
@WebServlet(name = "ViewPermission", urlPatterns = {"/permission/view"}, initParams = {
    @WebInitParam(name = "servletPackage", value = "cbvmp.admin.servlet")})

@Module(name = "View", url = "/permission/view")
public class ViewPermissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {

//        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            LinkedList<PermissionModel> modelList = new LinkedList(); 
            
            URL location = AclTestServlet.class.getProtectionDomain().getCodeSource().getLocation();
            File folder = new File(location.getFile() + "/cbvmp/admin/servlet/");
            File[] listOfFiles = folder.listFiles();
            for (File listOfFile : listOfFiles) {
                
                
                if (listOfFile.isDirectory()) {
//                    out.print(listOfFile.getName() + " ");
                    PermissionModel pmModel = new PermissionModel();
                    LinkedList<String> actionList = new LinkedList();
                    pmModel.setModule(listOfFile.getName());
                    File[] actions = listOfFile.listFiles();
                    for (File action : actions) {
                        String className = action.getName().replaceFirst("[.][^.]+$", "");
                        Class<?> clazz = Class.forName(getInitParameter("servletPackage") + "." + listOfFile.getName() + "." + className);
                        Module module = clazz.getAnnotation(Module.class);
                        actionList.add(module.name());
//                        out.print(module.name() + " ");
                    }
                    pmModel.setActions(actionList);
                    modelList.add(pmModel);
                    
                }
//                out.println();
            }
            
            
            req.setAttribute("permissions", "12345");
            req.getRequestDispatcher("/view/permission/view.jsp").forward(req, response);
            

        } catch (ClassNotFoundException ex) {
//            out.println(ex.getMessage());
        } finally {
//            out.close();
        }

    }

}
