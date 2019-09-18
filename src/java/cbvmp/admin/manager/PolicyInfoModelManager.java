/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.PolicyInfoModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SIT
 */
public class PolicyInfoModelManager {
      public PolicyInfoModel getPolicyInfo(String schema)
    {
       PolicyInfoModel policyModel = null;
        Session session = HibernateUtil.getSession(schema);
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_get_password_strength_inactive_timeout_session_timeout");
            //qu.setParameter("p1", username);
            if(!qu.list().isEmpty()){
                policyModel =(PolicyInfoModel) qu.list().get(0);
                //logger.debug("Validation Password: "+cmpoModel.getPassword()+" "+cmpoModel.getSchemaCode());
            }
            tx.commit();
        } catch (Exception e) {
            //logger.error(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
//        finally {
//            session.close();
//        }
        return policyModel;
    }
    
}
