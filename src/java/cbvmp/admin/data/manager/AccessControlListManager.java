/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.AccessControlListModel;
import cbvmp.admin.data.model.GetAccessControlListModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tanbir
 */
public class AccessControlListManager{

    HashMap<String, AccessControlListModel> aclMap;
    
    public HashMap<String,AccessControlListModel> genrateAccessControlList(Integer userId){
       
        Transaction tx = null;
        aclMap = new HashMap();
        ArrayList<AccessControlListModel> acl;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_get_user_acl");
            qu.setParameter("p1", userId);
            acl =(ArrayList)qu.list();
            
            for(AccessControlListModel aclModel : acl){
                if(this.aclMap.get(aclModel.getUrl())== null){
                    this.aclMap.put(aclModel.getUrl(),aclModel);
                }else{
                    AccessControlListModel localAclModel = this.aclMap.get(aclModel.getUrl());
                    if(!localAclModel.isAllowed() && aclModel.isAllowed()){
                        this.aclMap.put(aclModel.getUrl(),aclModel);
                    }
                
                }
            }
            
            //for (int i=0;i<list.size();i++) System.out.println(list.get(i));

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
        return this.aclMap;
    
    }
    
    
    
    public List<GetAccessControlListModel> listAll(Integer roleId, Integer userCategory){
        Transaction tx = null;
        List<GetAccessControlListModel> acl = new ArrayList();
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_get_acl_list");
            qu.setParameter("p1", roleId);
            qu.setParameter("p2", userCategory);
            acl =(ArrayList)qu.list();
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
        return acl;
        
    }

}
