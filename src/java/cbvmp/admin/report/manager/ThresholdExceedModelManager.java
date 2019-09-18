/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.report.model.ThresholdExceedModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ThresholdExceedModelManager {
     public List<ThresholdExceedModel> viewReport(int docTypeNo,int  lowerLimit,int higherLimit){
       
        Transaction tx = null;
        List<ThresholdExceedModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_doc_wise_msisddn_thrs_exceed");
          
            qu.setParameter("p1",docTypeNo );
            qu.setParameter("p2",lowerLimit );
            qu.setParameter("p3",higherLimit );
            list = qu.list();
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
