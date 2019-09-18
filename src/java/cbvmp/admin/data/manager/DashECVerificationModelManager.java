/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.DashECVerificationModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author Administrator
 */
public class DashECVerificationModelManager extends ModelManager {
    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

  

    public List<DashECVerificationModel> getEcVerified(Integer cmpo) {
        Transaction tx = null;
        List<DashECVerificationModel> ecList = new ArrayList();
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_dash_ec_verification");
            
            if (cmpo == 7) {
                qu.setParameter("p1", "");
            } else {
                qu.setParameter("p1", cmpo);
            }

            ecList = (ArrayList) qu.list();
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

        
        return ecList;
        
    }

    
    
}
