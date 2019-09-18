/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.report.model.MsisdnSearchHistoryModel;
import cbvmp.admin.report.model.MsisdnSearchModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author farhan
 */
public class MsisdnSearchModelManager {
    
    public List<MsisdnSearchModel> searchMsisdn(Integer cmpoNo,String msisdn)
    {
        
        Transaction tx = null;
        List<MsisdnSearchModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            
            tx = session.getTransaction();
            tx.begin(); 
            Query qu = session.getNamedQuery("sp_msisdn_search");
          
            qu.setParameter("p1",cmpoNo );
            qu.setParameter("p2",msisdn );
        System.out.println("checkkkkkkkkkkkkkkkk****************"+list.size()); 
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
    
    public List<MsisdnSearchHistoryModel> searchMsisdnHistory(Integer cmpoNo,String msisdn)
    {
        
        Transaction tx = null;
        //List<MsisdnSearchHistoryModel> list = new ArrayList();
        List<MsisdnSearchHistoryModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        String s="";
        if(cmpoNo==1){
            s="sp_msisdn_search_history_gp";
        }else if(cmpoNo==2){
            s="sp_msisdn_search_history_bl";
        }else if(cmpoNo==3){
            s="sp_msisdn_search_history_rb";
        }else if(cmpoNo==4){
            s="sp_msisdn_search_history_ar";
        }else if(cmpoNo==5){
            s="sp_msisdn_search_history_tk";
        }else if(cmpoNo==6){
            s="sp_msisdn_search_history_ct";
        }
        
        try {
            
            tx = session.getTransaction();
            tx.begin(); 
            Query qu = session.getNamedQuery(s);
          
            qu.setParameter("p1",cmpoNo );
            qu.setParameter("p2",msisdn );
        
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
