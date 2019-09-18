/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.PolicyModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class PolicyModelManager extends ModelManager{
    
    public PolicyModel get(Long id){
        return (PolicyModel) getEntity(PolicyModel.class, id);
    }
    public List<PolicyModel> viewPolicy(){
       
        Transaction tx = null;
        List<PolicyModel> list = new ArrayList();
        
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_edit_policy");
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
