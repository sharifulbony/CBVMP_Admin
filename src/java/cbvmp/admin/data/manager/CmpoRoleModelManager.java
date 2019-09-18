/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.CmpoModel;
import cbvmp.admin.data.model.CmpoRoleModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tanbir
 */
public class CmpoRoleModelManager extends ModelManager {

    public CmpoRoleModel get(Long id) {
        return (CmpoRoleModel) getEntity(CmpoRoleModel.class, id);
    }

    public String save(CmpoRoleModel cmpoRoleModel) {
        return saveEntity(cmpoRoleModel);
    }

//    public void save(CmpoRoleModel cmpoRoleModel) {
//        List<CmpoRoleModel> list;
//        Transaction tx = null;
//        Session session = HibernateUtil.getSession("CBVMP_MASTER");
//        try {
//            
//            tx = session.getTransaction();
//            tx.begin();
//            Query qu = session.getNamedQuery("sp_insert_user_role");
//            qu.setParameter("p1", cmpoRoleModel.getName());
//            qu.setParameter("p2", cmpoRoleModel.isActive());
//            list =(ArrayList)qu.list();
//            System.out.println("Cmpo Role saved");
//            //for (int i=0;i<list.size();i++) System.out.println(list.get(i));
//
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        
//    }
    public void update(CmpoRoleModel cmpoRoleModel) {
        updateEntity(cmpoRoleModel);
    }

    public void delete(CmpoRoleModel cmpoRoleModel) {
        deleteEntity(cmpoRoleModel);
    }

    public void delete(Class clazz, Long id) {
        deleteEntity(clazz, id);
    }

    public List<CmpoRoleModel> listAll() {
        return listAll(CmpoRoleModel.class);
    }

    public List<CmpoRoleModel> listAll(Integer cmpoId) {
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        session.beginTransaction();
        List list = session.createQuery("from " + CmpoRoleModel.class.getName() + " c where c.cmpoId=" + cmpoId + " order by c.id desc").list();
        session.getTransaction().commit();
        return list;

    }

}
