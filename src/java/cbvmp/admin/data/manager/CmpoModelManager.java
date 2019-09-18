/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.CmpoModel;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author tanbir
 */
public class CmpoModelManager extends ModelManager{
    
    public CmpoModel get(Integer id){
        return (CmpoModel) getEntity(CmpoModel.class, id);
    }
    
    public List<CmpoModel> listAll(Integer cmpoId){
        
//        return listAll(CmpoModel.class);
        if(cmpoId != 7 ){
            Session session = HibernateUtil.getSession("CBVMP_MASTER");
            session.beginTransaction();
            List list = session.createQuery("from " + CmpoModel.class.getName() + " c where c.id = "+cmpoId+" order by c.id desc").list();
            session.getTransaction().commit();
            //session.close();
            return list;
        }else{
            return listAll(CmpoModel.class);
        }
    }
    
}
