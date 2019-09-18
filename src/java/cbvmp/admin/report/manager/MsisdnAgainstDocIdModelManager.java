/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.Report;
import cbvmp.admin.data.model.ReportModel;
import cbvmp.admin.report.model.MsisdnAgainstDocIdModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class MsisdnAgainstDocIdModelManager  {
     public List<MsisdnAgainstDocIdModel> viewReport(int docTypeNo,String docId,String cmpoNo){
       
        Transaction tx = null;
        List<MsisdnAgainstDocIdModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_msisdn_agn_doc_id");
          
            qu.setParameter("p1",docTypeNo );
            qu.setParameter("p2",docId );
            qu.setParameter("p3",cmpoNo );
            list = qu.list();
            System.out.println("checkkkkkkkkkkkkkkkk****************"+list.size());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
//        finally {
//            session.close();
//        }
        return list;
    
    } 

    
}
