/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.UserLoginInfoModel;
import cbvmp.admin.util.log.SingletoneLogger;
import java.util.List;
import org.hibernate.Session;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author SIT
 */

public class UserLoginInfoModelManager extends ModelManager {

    //Log4jLoggerAdapter logger = SingletoneLogger.getLogger(UserLoginInfoModelManager.class);

    public UserLoginInfoModel get(Integer id) {
        return (UserLoginInfoModel) getEntity(UserLoginInfoModel.class, id);
    }

    public List<UserLoginInfoModel> listAll() {
        return listAll(UserLoginInfoModel.class);

    }

    public void update(UserLoginInfoModel userLog) {
        updateEntity(userLog);
    }

    public void save(UserLoginInfoModel userLog) {
        saveEntity(userLog);
    }

}
