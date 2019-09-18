/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data;

import cbvmp.admin.data.model.PolicyInfoModel;
import cbvmp.admin.data.model.UserInfoModel;
import cbvmp.admin.data.model.UserLockModel;
import cbvmp.admin.data.model.UserSessionManager;

/**
 *
 * @author rahat
 */
public class BaseClass {

    public UserSessionManager userSession;
    public UserInfoModel userModel;
    public UserLockModel userLock;
    public PolicyInfoModel policymodel;
    public boolean dateExp;

    public BaseClass() {
        userSession = null;
        userModel = null;
        userLock = null;
        policymodel = null;
        dateExp = false;
    }

    public UserSessionManager getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionManager userSession) {
        this.userSession = userSession;
    }

    public UserInfoModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserInfoModel userModel) {
        this.userModel = userModel;
    }

    public UserLockModel getUserLock() {
        return userLock;
    }

    public void setUserLock(UserLockModel userLock) {
        this.userLock = userLock;
    }

    public PolicyInfoModel getPolicymodel() {
        return policymodel;
    }

    public void setPolicymodel(PolicyInfoModel policymodel) {
        this.policymodel = policymodel;
    }

    public boolean isDateExp() {
        return dateExp;
    }

    public void setDateExp(boolean dateExp) {
        this.dateExp = dateExp;
    }
    

}
