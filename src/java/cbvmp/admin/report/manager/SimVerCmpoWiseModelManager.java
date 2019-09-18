/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.report.model.SimVerCmpoWiseModel;
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
public class SimVerCmpoWiseModelManager {
    public List<SimVerCmpoWiseModel> viewReport(String cmpoNo){
       
        Transaction tx = null;
        List<SimVerCmpoWiseModel> list = new ArrayList();
       // cmpo=null;
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            
            Query qu = session.getNamedQuery("sp_sim_ver_cmpo_wise");
            qu.setParameter("p1",cmpoNo);
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
