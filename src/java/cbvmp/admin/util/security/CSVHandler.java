/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.util.security;

import cbvmp.admin.util.log.SingletoneLogger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author tanbir
 */
public class CSVHandler {

    private static final String SERVER_IP = "172.29.1.51";
    private static final String USER = "root";
    private static final int PORT = 22;
    private static final String PASS = "Admin123";

    public static BulkMsisdnRequest processUploadedBulkRequest(String fileUrl) throws FileNotFoundException, IOException {

        Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");
        System.out.println("entered before file read.........");

        BufferedInputStream in = null;

        BulkMsisdnRequest bulkRequest = new BulkMsisdnRequest();
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
            Vector<ChannelSftp.LsEntry> vfiles = sftp.ls("*");
            System.out.println("number of files.........." + vfiles.size());
//        String fileName = "D2016_12_21_16:32_Bapj0fv05.csv";
            for (ChannelSftp.LsEntry entry : vfiles) {
                System.out.println("file list.........." + entry.getFilename().toString());
                if (fileUrl.equals(entry.getFilename().toString())) {
                    System.out.println("file found.........." + entry.getFilename());
                    in = new BufferedInputStream(sftp.get(entry.getFilename()));
                    String line = "";
//                    BulkMsisdnRequest bulkRequest = new BulkMsisdnRequest();
                    ArrayList<String> validMsisdnList = new ArrayList();
                    ArrayList<String> invalidMsisdnList = new ArrayList();
                    Integer validCount = 0;
                    Integer invalidCount = 0;
                    Integer totalCount = 0;
                    String[] msisdn = null;

                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        line = new String(buffer, 0, length);

                        msisdn = line.split(",");
//

                        totalCount++;
                        if (msisdn.length > 0 && msisdn[0] != null) {
                            msisdn[0] = msisdn[0].replace("\"", "");
                            System.out.println("msisdn " + msisdn[0]);
                            if (isValidMSISDN(msisdn[0])) {
                                validCount++;
                                validMsisdnList.add(msisdn[0]);
                                System.out.println("valid list entered");
                            } else {
                                invalidCount++;
                                invalidMsisdnList.add(msisdn[0]);
                            }
                        }
                    }
                    bulkRequest.setTotalCount(totalCount);
                    bulkRequest.setTotalValidNumber(validCount);
                    bulkRequest.setTotalInValidNumber(invalidCount);
                    bulkRequest.setValidMsisdnList(validMsisdnList);
                    bulkRequest.setInvalidMsisdnList(invalidMsisdnList);
//                    
                    break;
                }
            }

//                    BulkMsisdnRequest msisdnList = CSVHandler.processUploadedBulkRequest();
            sftp.exit();
            channel.disconnect();
            session.disconnect();
        } catch (JSchException ex) {
            Logger.getLogger(CSVHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SftpException ex) {
            Logger.getLogger(CSVHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
//        BufferedReader br = new BufferedReader(new FileReader(fileUrl));
        System.out.println("entered after file read.........");
//       

//        String cvsSplitBy = "~";
//        while ((line = in.toString()) != null) {
//
//            msisdn = line.split(",");
//
////msisdn = line.split(cvsSplitBy);
//            totalCount++;
//            if (msisdn.length > 0 && msisdn[0] != null) {
//                msisdn[0] = msisdn[0].replace("\"", "");
//                System.out.println("msisdn " + msisdn[0]);
//                if (isValidMSISDN(msisdn[0])) {
//                    validCount++;
//                    validMsisdnList.add(msisdn[0]);
//                    System.out.println("valid list entered");
//                } else {
//                    invalidCount++;
//                    invalidMsisdnList.add(msisdn[0]);
//                }
//            }
//
//        }
//        bulkRequest.setTotalCount(totalCount);
//        bulkRequest.setTotalValidNumber(validCount);
//        bulkRequest.setTotalInValidNumber(invalidCount);
//        bulkRequest.setValidMsisdnList(validMsisdnList);
//        bulkRequest.setInvalidMsisdnList(invalidMsisdnList);
        in.close();
        return bulkRequest;

    }

    public static boolean isValidMSISDN(String msisdn) {
        int length = msisdn.length();
//        System.out.println("msisdn now " + length);
        //Pattern p = Pattern.compile("^((880|0)\\d{10})?$");
        Pattern p = Pattern.compile("^((880)\\d{10})?$");
        Matcher m = p.matcher(msisdn);

        if (length == 13 && m.find()) {
            return true;
        } else {
            return false;
        }

    }
}
