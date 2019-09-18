/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.deregistration;

import cbvmp.admin.util.security.FileHandler;
import cbvmp.admin.util.security.UrlSecurity;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
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

/**
 *
 * @author rahat
 */
@WebServlet(name = "BulkDeregDownloadServlet", urlPatterns = {"/bulk/dereg/download/*"})
public class BulkDeregDownloadServlet extends HttpServlet {

//    private static final String SERVER_IP = "172.29.2.29";
     private static final String SERVER_IP = "172.29.1.29";
    private static final String USER = "ruposh";
    private static final int PORT = 22;
    private static final String PASS = "Admin123";

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
//            out.println("<title>Servlet BulkDeregDownloadServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet BulkDeregDownloadServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
//       String fileName = UrlSecurity.parseUrlParams(request.getPathInfo());
//        String name =id.toString();
        // String name = "/var/log/bulk/D20160824014928Ba9gn3mu3.csv";
        String name = request.getParameter("filename");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + name);

        OutputStream out = null;
        Channel channel = null;
        Session session = null;
        try {
            JSch jsch = new JSch();
//            Session session = jsch.getSession("root", "172.16.24.202", 22);
            session = jsch.getSession(USER, SERVER_IP, PORT);
            session.setConfig("PreferredAuthentications", "password");

            session.setPassword(PASS);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");

            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.connect();
            sftp.cd("/var/log/bulk/");
      
            BufferedInputStream in = new BufferedInputStream(sftp.get(name));

            //OutputStream out = response.getOutputStream();
            out = response.getOutputStream();
//        FileInputStream in = new FileInputStream(sftp.get(name));
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            channel.disconnect();
            session.disconnect();
        } catch (JSchException ex) {
            Logger.getLogger(BulkDeregDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SftpException ex) {
            Logger.getLogger(BulkDeregDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
//        boolean connect = fileHandler.downloadFilefromRemote();
//        channel.disconnect();
//        session.disconnect();
//        OutputStream out = response.getOutputStream();
//        FileInputStream in = new FileInputStream(name);
//        byte[] buffer = new byte[4096];
//        int length;
//        while ((length = in.read(buffer)) > 0) {
//            out.write(buffer, 0, length);
//        }
//        in.close();
//        out.flush();
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
