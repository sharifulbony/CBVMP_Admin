/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.deregistration;

import cbvmp.admin.data.manager.BulkDeregModelManager;
import cbvmp.admin.data.manager.BulkEntryDetailsModelManager;
import cbvmp.admin.data.manager.CmpoModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.BulkDeregModel;
import cbvmp.admin.data.model.BulkEntryDetailsModel;
import cbvmp.admin.data.model.CmpoModel;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.BulkMsisdnRequest;
import cbvmp.admin.util.security.CSVHandler;
import cbvmp.admin.util.security.FileHandler;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author tanbir
 */
@WebServlet(name = "BulkDeregCreate", urlPatterns = {"/bulk/dereg/create"})
public class BulkDeregCreateServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 2048 * 1024;
    private int maxMemSize = 3072 * 1024;
    private File file;

//    private static final String SERVER_IP = "172.29.2.29";
     private static final String SERVER_IP = "172.29.1.29";
    private static final String USER = "ruposh";
    private static final int PORT = 22;
    private static final String PASS = "Admin123";

    @Override
    public void init() {
        // Get the file location where it would be stored.
//            filePath = "/var/log/bulk/";

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/deregistration/create.jsp").forward(request, response);

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
        java.io.PrintWriter out = response.getWriter();

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");

//        if (!isMultipart) {
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet upload</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<p>No file uploaded</p>");
//            out.println("</body>");
//            out.println("</html>");
//            return;
//        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
//       

//        factory.setRepository(new File("172.16.24.202:/var/log/bulk/"));
//        factory.setRepository(new File("D://agri/"));
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        UserSessionManager us = (UserSessionManager) request.getSession().getAttribute("userSession");
        try {
            String batchId = null;
            String cmpoNo = null;
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    cmpoNo = item.getString();
//                    out.println(item.getFieldName());
//                    out.println(item.getString());

                }

            }

            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            batchId = new BigInteger(40, sr).toString(32);

            FileHandler fileHandler = new FileHandler();
            boolean isUploaded = fileHandler.uploadFile((FileItem) fileItems.get(0), batchId);

//            boolean isUploaded = true;
//BulkMsisdnRequest msisdnList = null;
            if (isUploaded) {
                //fileHandler.copyFileToRemote(fileHandler.uploadedFileName);
                //String uploadFileName = "c:\\temp\\D20160802161117B1.csv";
                String uploadFileName = fileHandler.getUploadedFileName();

               

                BulkMsisdnRequest msisdnList = CSVHandler.processUploadedBulkRequest(uploadFileName);
//                out.println("Total entry :" + msisdnList.getTotalCount());
//                out.println("Batch id : " + batchId);
//                out.println("Total valid msisdn list:" + msisdnList.getTotalValidNumber());
//                out.println("Total invalid msisdn list:" + msisdnList.getTotalInValidNumber());
//                Date d = new Date();
//                for (int i = 0; i < msisdnList.getTotalInValidNumber(); i++) {
//                    out.println("invalid entry : " + msisdnList.getInvalidMsisdnList().get(i));
//
//                }
//                for (int i = 0; i < msisdnList.getTotalValidNumber(); i++) {
//                    out.println(" valid : " + msisdnList.getValidMsisdnList().get(i));
//                }

//                if (msisdnList.getTotalCount() <= 100) {
                BulkDeregModel bulkModel = new BulkDeregModel();
                UserModel uBmodel = new UserModelManager().get(new Long(us.userModel.getId()));
                //bulkModel.setId(new Long(1));
                CmpoModel cmpo = new CmpoModelManager().get(Integer.parseInt(cmpoNo));
                bulkModel.setCmpoNo(cmpo);
                bulkModel.setTotalMsisdn(msisdnList.getTotalCount());
                bulkModel.setValidMsisdn(msisdnList.getTotalValidNumber());
                bulkModel.setTotalExecuted(0);
                bulkModel.setBatchId(batchId);
                bulkModel.setFileName(fileHandler.uploadedFileName);
                bulkModel.setCreatedAt(new Date());
                bulkModel.setCreatedById(uBmodel);
                bulkModel.setCreatedLogonNo(us.getLoginNo());

                BulkDeregModelManager bulkManager = new BulkDeregModelManager();
                bulkManager.save(bulkModel);
                //out.print("saved");

                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, us.getUser(), "Bulk deregistraion attempt: ", "successful", "login no: " + us.getLoginNo().toString(), "0", "batch id: " + batchId);
                logger.info(generateRequestLogString);

                BulkEntryDetailsModelManager bulkDetailManager = new BulkEntryDetailsModelManager();

                List<String> msisdn = new ArrayList();
                msisdn = msisdnList.getValidMsisdnList();

                for (String list : msisdn) {
                    BulkEntryDetailsModel bulkDetailsModel = new BulkEntryDetailsModel();
                    bulkDetailsModel.setReqNo((bulkModel.getId()));
                    bulkDetailsModel.setMsisdn(list);
                    bulkDetailsModel.setStatus(0);
                    //bulkDetailsModel.setRefSimNo(list);
                    bulkDetailsModel.setExecuteTime(null);
                    bulkDetailsModel.setIsExecute(0);
                    bulkDetailManager.save(bulkDetailsModel);

                }
                request.getSession().setAttribute("msisdnList", msisdnList);
                response.sendRedirect(request.getContextPath() + "/bulk/dereg/view");
                //out.print("entered"); 

//                } else {
//                    String message = "Bulk request too big";
//                    request.getSession().setAttribute("message", message);
//                    response.sendRedirect(request.getContextPath() + "/bulk/dereg/create");
//
//                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex);
        }
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
