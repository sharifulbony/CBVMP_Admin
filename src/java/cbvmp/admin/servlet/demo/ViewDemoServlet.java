/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.demo;

import cbvmp.admin.util.security.Module;
import java.io.IOException;
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

@WebServlet(name = "ViewDemo", urlPatterns = {"/demo/view"}, initParams = {
    @WebInitParam(name = "servletPackage", value = "cbvmp.admin.servlet")})
@Module(name="Demo",url="demo/view")
public class ViewDemoServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse response) throws IOException {
        
    }
}
