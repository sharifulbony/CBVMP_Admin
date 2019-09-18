package cbvmp.admin.data.manager;


import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.IsLoginCheckModel;
import cbvmp.admin.data.model.UserInfoModel;
import cbvmp.admin.data.model.UserLockModel;
import cbvmp.admin.util.security.CustomeEncryption;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



/**
 *
 * @author rahat
 */
public class UserInfoModelManager {
    
    
    
     public IsLoginCheckModel getIsLogin(String schema,String username)
    {
        IsLoginCheckModel loginModel = null;
        Session session = HibernateUtil.getSession(schema);
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_check_is_login");
            qu.setParameter("p1", username);
            
             if(!qu.list().isEmpty()){
                loginModel =(IsLoginCheckModel) qu.list().get(0);
                 System.out.println("log check "+loginModel.getIs_login());
                //logger.debug("Validation Password: "+cmpoModel.getPassword()+" "+cmpoModel.getSchemaCode());
            }
//            ArrayList<IsLoginCheckModel>elements = new ArrayList(qu.list());
//            if(!elements.isEmpty()){
//                loginModel =elements.get(0);
//                
//                System.out.println("log check "+loginModel.getIs_login());
//                //logger.debug("Validation Password: "+cmpoModel.getPassword()+" "+cmpoModel.getSchemaCode());
//            }
            tx.commit();
        } catch (Exception e) {
            //logger.error(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
//        finally {
//            session.close();
//        }
        return loginModel;
    }
    
    
    
    
    public UserInfoModel verifyChangePasswordRequest(String schema,String username)
    {
       UserInfoModel cmpoModel = null;
        Session session = HibernateUtil.getSession(schema);
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_verify_user");
            qu.setParameter("p1", username);
            if(!qu.list().isEmpty()){
                cmpoModel =(UserInfoModel) qu.list().get(0);
                //logger.debug("Validation Password: "+cmpoModel.getPassword()+" "+cmpoModel.getSchemaCode());
            }
            tx.commit();
        } catch (Exception e) {
            //logger.error(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
//        finally {
//            session.close();
//        }
        return cmpoModel;
    }

    public UserInfoModel verifyUserNamePassword(String schema, String username, String password) {

        UserInfoModel userInfo = null;
        Session session = HibernateUtil.getSession(schema);
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_verify_user");
            qu.setParameter("p1", username);
            if (!qu.list().isEmpty()) {
                userInfo = (UserInfoModel) qu.list().get(0);
                //logger.debug("Validation Password: "+cmpoModel.getPassword()+" "+cmpoModel.getSchemaCode());
                if (!CustomeEncryption.validatePassword(password, userInfo.getPassword())) {
                    userInfo = null;
                }

            }
            tx.commit();
        } catch (Exception e) {
            //logger.error(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
//        finally {
//            session.close();
//        }
        return userInfo;
    }
    
    public UserLockModel updateUserLockFlag(String schema,String username,Integer success)
    {
        UserLockModel userLock = null;
        Session session = HibernateUtil.getSession(schema);
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query qu = session.getNamedQuery("sp_update_user_lock");
            qu.setParameter("p1", username);
            qu.setParameter("p2", success);
            ArrayList<UserLockModel> elements = new ArrayList(qu.list());
            //userLock =(UserLockModel) qu.list().get(0);
            if(!elements.isEmpty()){
                //System.out.println("****************");
                userLock =elements.get(0);
                //System.out.println("Error is : "+userLock.getIsError()+"Error desc "+userLock.getErrDesc());
            }
            tx.commit();
        } catch (Exception e) {
            //logger.error(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
//        finally {
//            session.close();
//        }
        return userLock;
    }
}
