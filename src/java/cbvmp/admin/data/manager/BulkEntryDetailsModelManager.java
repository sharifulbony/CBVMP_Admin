/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;
import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.BulkEntryDetailsModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.impl.Log4jLoggerAdapter;
/**
 *
 * @author rahat
 */

public class BulkEntryDetailsModelManager extends ModelManager {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    public BulkEntryDetailsModel get(Long id) {
        return (BulkEntryDetailsModel) getEntity(BulkEntryDetailsModel.class, id);
    }

    public List<BulkEntryDetailsModel> listAll(Integer id) {
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        session.beginTransaction();
        Query qu = session.createQuery("from " + BulkEntryDetailsModel.class.getName() + " c where c.reqNo="+id+" order by c.id desc ");
//        qu.setFirstResult(start);
//        qu.setMaxResults(length);
//        List list = (ArrayList<BulkEntryDetailsModel>)session.createQuery("from " + BulkEntryDetailsModel.class.getName() + " c where c.reqNo="+id+" order by c.id desc ").list();
        List list = (ArrayList<BulkEntryDetailsModel>)qu.list();
        System.out.println("list all function called.....");
        session.getTransaction().commit();
        return list;
    }

    public void update(BulkEntryDetailsModel bulkDetails) {
        updateEntity(bulkDetails);
    }

    public void save(BulkEntryDetailsModel bulkDetails) {
        saveEntity(bulkDetails);
    }

}
