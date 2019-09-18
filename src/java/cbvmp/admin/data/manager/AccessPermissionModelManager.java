/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.AccessPermissionModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tanbir
 */
public class AccessPermissionModelManager extends ModelManager {

    public AccessPermissionModel get(Long id) {
        return (AccessPermissionModel) getEntity(AccessPermissionModel.class, id);
    }

    public void update(AccessPermissionModel acl) {
        updateEntity(acl);
    }

    public void updateOrSave(Integer menuId, Integer roleId, boolean isAllowed) {
        Transaction tx = null;
        List<AccessPermissionModel> acl = new ArrayList();
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_update_or_save_acl");
            qu.setParameter("p1", roleId);
            qu.setParameter("p2", menuId);
            qu.setParameter("p3", isAllowed);
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

    }

    public List<AccessPermissionModel> getAclByRole(Integer id) {
//        Session session = HibernateUtil.getSession("CBVMP_MASTER");
//        List list = session.createQuery("from " + AccessPermissionModel.class.getName() + " c where c.roleId =" + id + " order by c.id").list();
////        session.close();
//        return list;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        session.beginTransaction();
        List list = session.createQuery("from " + AccessPermissionModel.class.getName() + " c where c.roleId =" + id + " order by c.id").list();
        session.getTransaction().commit();
        //session.close();
        return list;

    }

}
