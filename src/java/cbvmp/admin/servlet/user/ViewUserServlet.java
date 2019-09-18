/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.user;
import cbvmp.admin.data.manager.AccessPermissionModelManager;
import cbvmp.admin.data.model.AccessPermissionModel;
import cbvmp.admin.util.security.UrlSecurity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanbir
 */

@WebServlet(name="ViewUser" ,urlPatterns = {"/cmpo/user"})
public class ViewUserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/view/user/view.jsp").forward(request, response);
    }

    private boolean isChekboxSelected(String value) {
        return value != null;

    }
    
}
