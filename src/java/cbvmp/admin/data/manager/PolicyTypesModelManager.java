/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.PolicyTypesModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Administrator
 */
public class PolicyTypesModelManager extends ModelManager{
    public PolicyTypesModel get (Long id) throws Exception {
        return (PolicyTypesModel) getEntity(PolicyTypesModel.class, id);
    }
   // public Long getPolicyId()
    
    public List<PolicyTypesModel> listAll (){
        //System.out.println("done");
        
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        List list = null;
        session.beginTransaction();
        list =(ArrayList<PolicyTypesModel>) session.createQuery("from " + PolicyTypesModel.class.getName() + " c where c.id !=1 order by c.id desc").list();
        session.getTransaction().commit();
        return list;
        
    }
}
