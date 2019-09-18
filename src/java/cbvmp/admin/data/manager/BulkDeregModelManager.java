/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.BulkDeregModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author rahat
 */
public class BulkDeregModelManager extends ModelManager {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    public BulkDeregModel get(Long id) {
        return (BulkDeregModel) getEntity(BulkDeregModel.class, id);
    }

    public List<BulkDeregModel> listAll(Integer id) {
        if(id!= 7){
            Session session = HibernateUtil.getSession("CBVMP_MASTER");
            session.beginTransaction();
            List list = (ArrayList<BulkDeregModel>)session.createQuery("from " + BulkDeregModel.class.getName() + " c where c.cmpoNo ="+id+" order by c.id desc").list();
            session.getTransaction().commit();
            return list;
        }else{
            return listAll(BulkDeregModel.class);
        }
        
    }

    public void update(BulkDeregModel bulkModel) {
        updateEntity(bulkModel);
    }

    public void save(BulkDeregModel bulkModel) {
        saveEntity(bulkModel);
    }

}
