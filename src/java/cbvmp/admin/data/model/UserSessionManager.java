package cbvmp.admin.data.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rahat
 */
//public class UserSessionManager implements HttpSessionBindingListener{
    public class UserSessionManager implements HttpSessionBindingListener{


    public static Map<String, HttpSession> logins = Collections.synchronizedMap(new HashMap<String, HttpSession>());
    private Integer loginNo;
    private Integer userId;
    private Integer userCmpoId;
    private Integer userCategoryId;
    private String user;
    private String pass;
    private Date loginTime;
    private Integer lock;
    private long lastActiveTime=0;
    private String passwordStrength;
    private Integer maxInactiveTime;
    private Integer maxSessionTimeout;
    private boolean alreadyLoggedIn=false;
    public Integer loginTryCount=0;
    public UserInfoModel userModel;
    public UserLockModel userLock;
    public PolicyInfoModel policymodel;
    public boolean dateExp;
    
    /*--------VARIABLES FOR REPORT (START)---------*/
    private Integer cmpoNo;
    private Integer docType;
    private String dateFrom;
    private String dateTo;
    private String groupByTimeParam;
    private String limit;

    
    public String getLimit() {
        return limit;
    }

    /*--------VARIABLES FOR REPORT (END)---------*/
    public void setLimit(String limit) {    
        this.limit = limit;
    }

    public String getGroupByTimeParam() {
        return groupByTimeParam;
    }

    public void setGroupByTimeParam(String groupByTimeParam) {
        this.groupByTimeParam = groupByTimeParam;
    }
    


    

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }
    

    public Integer getCmpoNo() {
        return cmpoNo;
    }

    public Integer getLoginTryCount() {
        return loginTryCount;
    }

    public void setLoginTryCount(Integer loginTryCount) {
        this.loginTryCount = loginTryCount;
    }

    public void setCmpoNo(Integer cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

   
    public Integer getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(Integer loginNo) {
        this.loginNo = loginNo;
    }

    public long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserCmpoId() {
        return userCmpoId;
    }

    public void setUserCmpoId(Integer userCmpoId) {
        this.userCmpoId = userCmpoId;
    }

    public Integer getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(Integer userCategoryId) {
        this.userCategoryId = userCategoryId;
    }
    
    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPasswordStrength() {
        return passwordStrength;
    }

    public void setPasswordStrength(String passwordStrength) {
        this.passwordStrength = passwordStrength;
    }

    public Integer getMaxInactiveTime() {
        return maxInactiveTime;
    }

    public void setMaxInactiveTime(Integer maxInactiveTime) {
        this.maxInactiveTime = maxInactiveTime;
    }

    public Integer getMaxSessionTimeout() {
        return maxSessionTimeout;
    }

    public void setMaxSessionTimeout(Integer maxSessionTimeout) {
        this.maxSessionTimeout = maxSessionTimeout;
    }

    public boolean getAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
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
    

    
    @Override
    public boolean equals(Object other) {
        return (other instanceof UserSessionManager) && (user != null) ? user.equals(((UserSessionManager) other).user) : (other == this);
    }

    @Override
    public int hashCode() {
        return (user != null) ? (this.getClass().hashCode() + user.hashCode()) : super.hashCode();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("In value bound");
        
        if (logins.containsKey(getUser())) 
        {
            HttpSession session = logins.get(getUser());
            if (session != null) 
            {
                //session.invalidate();
                System.out.println("Already logged In");
                alreadyLoggedIn=true;
                System.out.println(alreadyLoggedIn);                        
            } 
        }
        else 
        {
                logins.put(getUser(), event.getSession());
                System.out.println("User logged In");
            
        }
        
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

        if(!alreadyLoggedIn)
        {
            logins.remove(getUser(),event.getSession());
        }
        //alreadyLoggedIn=false;
        //logins.remove(getUser(),event.getSession());
        System.out.println("In value unbound:"+alreadyLoggedIn);
    }

}

