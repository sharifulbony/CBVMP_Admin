/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.ReportModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ReportModelManager extends ModelManager{
    public ReportModel get(Long id){
        return (ReportModel) getEntity(ReportModel.class, id);
    }
    public List<ReportModel> viewReport(int start,int length,int simCount,String NID,int docType/*,String orderBy,String orderDir*/){
       
        Transaction tx = null;
        List<ReportModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_report_test");
            System.out.println("*****"+start);
            qu.setParameter("p1",start );
            qu.setParameter("p2",length );
            qu.setParameter("p3",simCount );
            qu.setParameter("p4", NID);
            qu.setParameter("p5", docType);
//            qu.setParameter("p6", orderBy);
//            qu.setParameter("p7", orderDir);
            //list = listAll(PolicyModel.class);
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
