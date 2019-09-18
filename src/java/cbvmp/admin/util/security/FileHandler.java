/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.util.security;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.fileupload.FileItem;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tanbir
 */
public class FileHandler {

    private String filePath;
    private File file;
    //private File sourceLocation, targetLocation;
    public String uploadedFileName;
    
     private static final String SERVER_IP = "172.29.1.51";
    private static final String USER = "root";
    private static final int PORT = 22;
    private static final String PASS = "Admin123";

    public FileHandler() {
//        this.filePath = "c://temp/";
        //URL url = new URL("file://172.16.24.202/var/log/bulk/");
        //String real= file.ge
//        this.filePath = "/var/log/bulk/";

//        String fromPath = "/var/log/bulk/";
//        String fromPath = "c://temp";
//        String toPath = "//172.16.24.202//";
//        sourceLocation = new File(fromPath);
//        targetLocation = new File(toPath);
//        this.filePath = fromPath ;
//        this.filePath ="d://agri/";
    }

//    public boolean downloadFilefromRemote() throws JSchException
//    {
//        String destination = "root@172.16.24.196";
//        String destUser = destination.substring(0, destination.indexOf('@'));
//        destination = destination.substring(destination.indexOf('@') + 1);
//        //System.out.println(destUser + " \n" + destination);
//        JSch jsch = new JSch();
//        Session destSession = null;
//        destSession = jsch.getSession(destUser, destination, 22);
//        destSession.setPassword("123321");
//        destSession.setConfig("StrictHostKeyChecking", "no");
//        destSession.connect();
//        
//        
//         
//        return destSession.isConnected();
//
//    }
    public void copyFileToRemote(String fileName) throws JSchException, IOException {
        String source = "root@172.16.24.202";
        String user = source.substring(0, source.indexOf('@'));
        source = source.substring(source.indexOf('@') + 1);
        //System.out.println(user + " \n" + source);

        JSch jsch = new JSch();
        Session session = null;
        session = jsch.getSession(user, source, 22);
        session.setPassword("123321");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        if (session.isConnected()) {
            System.out.println(source + " connected");
            String destination = "root@172.16.24.196";
            String destUser = destination.substring(0, destination.indexOf('@'));
            destination = destination.substring(destination.indexOf('@') + 1);
            //System.out.println(destUser + " \n" + destination);
            JSch jschDest = new JSch();
            Session destSession = null;
            destSession = jsch.getSession(destUser, destination, 22);
            destSession.setPassword("123321");
            destSession.setConfig("StrictHostKeyChecking", "no");
            destSession.connect();
            if (destSession.isConnected()) {
                String name = fileName;
                System.out.println(destination + " connected");
                Channel channel = session.openChannel("exec");
                ((ChannelExec) channel).setCommand("scp /var/log/bulk/ " + name + " " + "root@172.16.24.196:/var/log/bulk/");
                channel.setInputStream(null);
                ((ChannelExec) channel).setErrStream(System.err);

                //nnel.getInputStream();
                channel.connect();
                System.out.println("channel connect-status" + " " + channel.isConnected());

//	        byte[] tmp=new byte[1024];
//	        while(true){
//	          while(in.available()>0){
//	            int i=in.read(tmp, 0, 1024);
//	            if(i<0)break;
//	            System.out.print(new String(tmp, 0, i));
//	          }
                channel.disconnect();
                if (channel.isClosed()) {
                    System.out.println("channel connect-status: " + channel.isConnected());

                    // break;
                }

//                try {
//                    Thread.sleep(1000);
//                } catch (Exception ee) {
//                }
                //}
            }
            destSession.disconnect();
            if (!destSession.isConnected()) {
                System.out.println(destination + " " + "disconnceted");
            }

        }
        session.disconnect();
        if (!session.isConnected()) {
            System.out.println(source + " " + "disconnceted");
        }
    }

    public boolean uploadFile(FileItem fileItem, String batchId) throws Exception {

        FileItem fi = fileItem;
        if (!fi.isFormField()) {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy_MM_dd_HH:mm");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);

            //this.uploadedFileName = filePath + "//" + "D" + strDate + "B" + batchId + ".csv";
            this.uploadedFileName = "D" + strDate + "_B" + batchId + ".csv";

            file = new File(uploadedFileName);
//            file = new File(fileName);          

            // file.
            //file.
//            System.out.println("fi items.........." + fi.getInputStream());
//            fi.write(file);
            Channel channel = null;
            Session session = null;
            try {
                JSch jsch = new JSch();
                session = jsch.getSession(USER, SERVER_IP, PORT);
//            session = jsch.getSession(USER, SERVER_IP, PORT);
                session.setConfig("PreferredAuthentications", "password");

                session.setPassword(PASS);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                channel = session.openChannel("sftp");

                ChannelSftp sftp = (ChannelSftp) channel;
                sftp.connect();
                sftp.cd("/var/log/bulk/");
//                factory.setRepository(new File("172.16.24.202:/var/log/bulk/"));

                sftp.put(fi.getInputStream(), file.getName());                
//                System.out.println("sftp........"+sftp.getExtInputStream());
                
                sftp.exit();
                channel.disconnect();
                session.disconnect();
            } catch (JSchException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SftpException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //copyFileToRemote(uploadedFileName);
//            if (file.exists()) {
//                fi.delete();
//            }
        }
        return true;

    }

    public String getUploadedFileName() {
//        return file.getAbsolutePath();
        return file.getName();
    }

}
