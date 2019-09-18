/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author tanbir
 */
@Entity
@Table(name = "GEN_CMPO_USER")
public class UserModel implements Serializable {

    @Id
    @Column(name = "ID")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",
            sequenceName = "CMPO_USER_SEQ",
            allocationSize = 1)

    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "IS_PASS_CHANGE_REQ")
    private boolean passChangeRequired;

    @Column(name = "IS_ADMIN")
    private boolean Admin;

    @Column(name = "IS_ACTIVE")
    private boolean Active;

    @Column(name = "LOGIN_ATTEMPTS")
    private Integer loginAttepmts;

    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @Column(name = "PASS_EXPIRE_TIME")
    private Date passExpireTime;

    @Column(name = "LOCK_FLAG")
    private Integer lock;

    @ManyToOne
    @JoinColumn(name = "CMPO_NO")
    private CmpoModel cmpoNo;

    @Column(name = "USER_CAT_ID")
    private Integer userCatId;
    
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private UserModel createdById;

    @Column(name = "LOGIN_NO")
    private Integer loginNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isPassChangeRequired() {
        return passChangeRequired;
    }

    public void setPassChangeRequired(boolean passChangeRequired) {
        this.passChangeRequired = passChangeRequired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public Integer getLoginAttepmts() {
        return loginAttepmts;
    }

    public void setLoginAttepmts(Integer loginAttepmts) {
        this.loginAttepmts = loginAttepmts;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getPassExpireTime() {
        return passExpireTime;
    }

    public void setPassExpireTime(Date passExpireTime) {
        this.passExpireTime = passExpireTime;
    }

    public CmpoModel getCmpoNo() {
        return cmpoNo;
    }

    public void setCmpoNo(CmpoModel cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

    public Integer getUserCatId() {
        return userCatId;
    }

    public void setUserCatId(Integer userCatId) {
        this.userCatId = userCatId;
    }

    public UserModel getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserModel createdById) {
        this.createdById = createdById;
    }
    public Integer getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(Integer loginNo) {
        this.loginNo = loginNo;
    }

}
