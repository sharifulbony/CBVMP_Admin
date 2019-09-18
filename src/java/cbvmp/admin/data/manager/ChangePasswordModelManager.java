/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.ChangePasswordModel;
import cbvmp.admin.data.model.PasswordFrequencyModel;
import cbvmp.admin.util.security.CustomeEncryption;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SIT
 */
public class ChangePasswordModelManager {
    
     public ChangePasswordModel changePassword(String schema,String userID, String oldPass, String newPass,boolean isForce) {
        Transaction tx = null;
        ChangePasswordModel list = new ChangePasswordModel();
        Session session = HibernateUtil.getSession(schema);
      

        try {
            
            String newPassword = CustomeEncryption.encryptPassword(newPass);
            //String oldPassword = CustomeEncryption.encryptPassword(oldPass);
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_change_password_req");
            //qu.setParameter("p1", cmpoNo);
            qu.setParameter("p1", userID);
            qu.setParameter("p2", oldPass);
            //qu.setParameter("p4", passSalt);
            qu.setParameter("p3", newPassword);
            qu.setParameter("p4", isForce);

            list = (ChangePasswordModel) qu.list().get(0);
         //   System.out.println("loggedin");
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
        return list;
    }
     
    public List<PasswordFrequencyModel> checkPasswordFreq(String schema,String userID) {
        Transaction tx = null;
        List<PasswordFrequencyModel> list = new ArrayList<PasswordFrequencyModel>();
        Session session = HibernateUtil.getSession(schema);
      

        try {
            
            //String newPassword = CustomeEncryption.encryptPassword(newPass);
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_check_password_freq");
            qu.setParameter("p1", userID);
        
            

            list = qu.list();
         //   System.out.println("loggedin");
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
        return list;
    } 
     
     

    public ChangePasswordModel verifyUserNamePassword(String cbvmp_master, String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
