/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.AccessControlListModel;
import cbvmp.admin.data.model.UserRoleMapModel;
import cbvmp.admin.data.model.UserRoleMapProcModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tanbir
 */
public class UserRoleMapModelManager extends ModelManager{
    
    
    public void update(UserRoleMapModel userRoleMapModel,boolean hasAccess){
        Transaction tx = null;
        List<UserRoleMapModel> roleMap = new ArrayList();
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_insert_user_role_map");
            qu.setParameter("p1", userRoleMapModel.getUserId());
            qu.setParameter("p2", userRoleMapModel.getRoleId());
            qu.setParameter("p3", hasAccess);
            roleMap =(ArrayList)qu.list();
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
    
    public void saveOrUpdate(UserRoleMapModel userRoleMapModel){
        saveOrUpdateEntity(userRoleMapModel);
    }
    
    
    public List<UserRoleMapProcModel> getRoleListByUserId(Long id, Integer cmpo){
        Transaction tx = null;
        List<UserRoleMapProcModel> roleMap = new ArrayList();
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_get_user_role");
            qu.setParameter("p1", id);
            qu.setParameter("p2", cmpo);
            roleMap =(ArrayList)qu.list();
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
        return roleMap;
    }
    
}
