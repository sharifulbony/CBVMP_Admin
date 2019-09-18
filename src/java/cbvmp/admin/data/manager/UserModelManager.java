/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.UserModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.util.List;
import org.hibernate.Session;
import org.slf4j.impl.Log4jLoggerAdapter;


/**
 *
 * @author tanbir
 */


public class UserModelManager extends ModelManager{
    
    //Log4jLoggerAdapter logger = SingletoneLogger.getLogger(UserModelManager.class);
    public UserModel get(Long id){
        return (UserModel) getEntity(UserModel.class, id);
    }
    public List<UserModel> listAll(Integer cmpoId){
        if(cmpoId != 7){
            Session session = HibernateUtil.getSession("CBVMP_MASTER");
            session.beginTransaction();
            List list = session.createQuery("from " + UserModel.class.getName()+ " c where c.cmpoNo = "+cmpoId+" order by c.id desc").list();
            session.getTransaction().commit();
            //session.close();
            return list;
        }else{
            return listAll(UserModel.class);
        }
        
    }
    
    public void update(UserModel userModel){
        updateEntity(userModel);
    }
    
    public String save(UserModel userModel){
        return saveEntity(userModel);
    }
    
}
