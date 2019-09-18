/**
 * This class is used to get Data from DB.
 */
package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cbvmp.admin.report.MsisdnAgainstDocIdServlet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 

import cbvmp.admin.data.model.ReportModel;
import cbvmp.admin.report.manager.MsisdnAgainstDocIdModelManager;
import cbvmp.admin.report.model.MsisdnAgainstDocIdModel;

public class JasperData {

  

  public List getData(String par,Integer pageno) throws SQLException 
  {

    //Connection dc = null;
   
    //JasperDataBean objBean;
    //List<JasperDataBean> dataList = new ArrayList<JasperDataBean>(0);
    MsisdnAgainstDocIdModelManager msisdnAgainstNidModelManager = new MsisdnAgainstDocIdModelManager();
    
    List<MsisdnAgainstDocIdModel> list = msisdnAgainstNidModelManager.viewReport(1, "123", "1");
    
    //System.out.println("Size************::"+list.size());
    
//    for(int i=0;i<list.size();i++)
//    {
//        /*System.out.println(list.get(i).getDate());
//        System.out.println(list.get(i).getEmail());*/
//        
//        objBean = new JasperDataBean();
//        objBean.setAddDate(list.get(i).getCmpoName());
//        objBean.setName(list.get(i).getDocType());
//        objBean.setEmail(list.get(i).getMSISDN());
//        objBean.setWebsite("Record_Status");
//        objBean.setSubject("Record_Status");
//        objBean.setMessage("Record_Status");
//        objBean.setRecordStatus("Record_Status");
//        dataList.add(objBean);
//        
//        //System.out.println("checkId:"+list.get(i).getMENU_NAME());
//    
//    }
//    
    /*dc = DataConnectionPoolImpl.getConnection();
    Statement st = dc.createStatement();
    String queryadd="NAME_COL LIKE '"+par+"%'";
    String query="Select * from REPORT where "+queryadd;
    System.out.println("Query::"+query);
    ResultSet rs = st.executeQuery(query);
    while(rs.next())
    {
        objBean = new JasperDataBean();
        objBean.setAddDate(rs.getString(1));
        objBean.setName(rs.getString(2));
        objBean.setEmail(rs.getString(3));
        objBean.setWebsite(rs.getString(4));
        objBean.setSubject(rs.getString(5));
        objBean.setMessage(rs.getString(6));
        objBean.setRecordStatus("Record_Status");
        dataList.add(objBean);
            
    }*/
    
    
   // return dataList;
   return list;
  }
}