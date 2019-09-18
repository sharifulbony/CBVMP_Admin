package cbvmp.admin.data.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rahat
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "sp_verify_user",
            query = "CALL GEN_USER_SALT(?,:p1)",
            callable = true,
            resultClass = UserInfoModel.class
    )
})

public class UserInfoModel implements Serializable {

    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_PASS_CHANGE_REQ")
    private boolean passwdChangeRequired;
    @Column(name = "CMPO_NO")
    private Integer cmpoNo;
    @Column(name = "USER_CAT_ID")
    private Integer userCategoryId;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @Column(name = "PASS_EXPIRE_TIME")
    private String passExpireTime;
    @Column(name = "IS_ADMIN")
    private Integer isAdmin;

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswdChangeRequired() {
        return passwdChangeRequired;
    }

    public void setIsPasswdChangeRequired(boolean isPasswdChangeRequired) {
        this.passwdChangeRequired = isPasswdChangeRequired;
    }

    public Integer getCmpoNo() {
        return cmpoNo;
    }

    public void setCmpoNo(Integer cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassExpireTime() {
        return passExpireTime;
    }

    public void setPassExpireTime(String passExpireTime) {
        this.passExpireTime = passExpireTime;
    }

    public Integer getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(Integer userCategoryId) {
        this.userCategoryId = userCategoryId;
    }
    
}
