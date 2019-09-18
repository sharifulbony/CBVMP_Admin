/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.BlackListDeleteModel;
import cbvmp.admin.data.model.BlackListModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rahat
 */
public class BlackListModelManager extends ModelManager  {
    public BlackListModel get(Long id){
        return (BlackListModel) getEntity(BlackListModel.class, id);
    }
    
    public String save(BlackListModel blackModel){
        return saveEntity(blackModel);
    }
    
    public List<BlackListModel> listAll (){
        return listAll(BlackListModel.class);
    }  
  
    public BlackListDeleteModel deleteBlackDoc(
            Integer docTypeNo,
            String docId,
            Integer createdBy,
            Integer loginNo
            ) 
    {
        Transaction tx = null;
        BlackListDeleteModel blackDelList = null;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_delete_black_doc");
            qu.setParameter("p1", docTypeNo);
            qu.setParameter("p2", docId);
            qu.setParameter("p3", createdBy);
            qu.setParameter("p4", loginNo);
           
            blackDelList = (BlackListDeleteModel) qu.list().get(0);
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
        return blackDelList;
    }
    
}
