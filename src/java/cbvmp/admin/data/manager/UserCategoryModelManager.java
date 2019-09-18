/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.UserCategoryModel;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author tanbir
 */
public class UserCategoryModelManager extends ModelManager {

    public List<UserCategoryModel> listAll(Integer userCategory) {
        if (userCategory == 1) {
            return listAll(UserCategoryModel.class);
        } else {
            Session session = HibernateUtil.getSession("CBVMP_MASTER");
            session.beginTransaction();
            List list = session.createQuery("from " + UserCategoryModel.class.getName() + " c where c.categoryId=" + userCategory + " order by c.id desc").list();
            session.getTransaction().commit();
            //session.close();
            return list;
        }
    }

    public List<UserCategoryModel> listAllForMenu() {
        return listAll(UserCategoryModel.class);
    }

}
