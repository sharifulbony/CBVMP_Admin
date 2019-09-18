/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.TotalSimAgainstDocModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tanbir
 */
public class TotalSimAgainstDocModelManager extends ModelManager {

    public List<TotalSimAgainstDocModel> geTotalSim(Integer cmpoNo) {
        Transaction tx = null;
        List<TotalSimAgainstDocModel> acl = new ArrayList();
        Session session = HibernateUtil.getSession("CBVMP_REPORT");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_get_total_sim_count_against_doc");
            qu.setParameter("p1", "");
            if (cmpoNo == 7) {
                qu.setParameter("p2", "");
            } else {
                qu.setParameter("p2", cmpoNo);
            }

            qu.setParameter("p3", "");
            acl = (ArrayList) qu.list();
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
        return acl;

    }
}
