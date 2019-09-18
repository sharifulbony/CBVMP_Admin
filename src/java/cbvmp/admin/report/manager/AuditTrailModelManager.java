/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.report.model.AuditTrailModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class AuditTrailModelManager {
//    public List<AuditTrailModel> viewReport(String cmpoNo,Date dateFrom, Date dateTo, String grpBy){
//       
//        Transaction tx = null;
//        List<AuditTrailModel> list = new ArrayList();
//        
//        Session session = HibernateUtil.getSession("CBVMP_REPORT");
//        try {
//            
//            tx = session.getTransaction();
//            tx.begin();
//            
//            Query qu = session.getNamedQuery("sp_audit_trail");
//            qu.setParameter("p1", cmpoNo);
//            qu.setParameter("p2", dateFrom);
//            qu.setParameter("p3", dateTo);
//            qu.setParameter("p4", grpBy);
//            list = qu.list();
//            
//            for (int i=0; i<list.size(); i++)
//            {
//                System.out.println(list.get(i).getGrpByDate());
//            }
//            //System.out.println(list.size());
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } 
////        finally {
////            session.close();
////        }
//        return list;
//    }
    public List<AuditTrailModel> viewReport(String cmpoNo,Date fromDate, Date toDate, String grpBy){
       
        Transaction tx = null;
        List<AuditTrailModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_audit_trail");
          
            qu.setParameter("p1",cmpoNo );
            qu.setParameter("p2",fromDate );
            qu.setParameter("p3",toDate );
            qu.setParameter("p4", grpBy);
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
