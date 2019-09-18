/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.AccessPermissionModel;
import cbvmp.admin.data.model.MenuModel;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author tanbir
 */
public class MenuModelManager extends ModelManager {

    public List<MenuModel> listAll() {
        return listAll(MenuModel.class);
    }

    public void save(MenuModel menuModel) {
        saveEntity(menuModel);
    }

    public List<MenuModel> listAllParent() {
//        Session session = HibernateUtil.getSession("CBVMP_MASTER");
//        List list = session.createQuery("from " + MenuModel.class.getName() + " c where c.parentId is NULL order by c.id").list();
//        //session.close();
//        return list;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        session.beginTransaction();
        List list = session.createQuery("from " + MenuModel.class.getName() + " c where c.parentId is NULL order by c.id").list();
        session.getTransaction().commit();
        //session.close();
        return list;

    }
}
