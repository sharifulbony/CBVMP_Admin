/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author SIT
 */

@Entity
@NamedNativeQueries({
     @NamedNativeQuery(
            name = "sp_get_password_strength_inactive_timeout_session_timeout",
            query = "CALL GET_PASS_STR_LOGOUT_POLICY_VAL(?)",
            callable = true,
            resultClass = PolicyInfoModel.class
    )

})

public class PolicyInfoModel {
    
    @Id
    @Column(name = "V_PASS_STRENGTH")
    private String passwordStrength;
    @Column(name = "V_MAX_INACTIVE_TIME")
    private String maxInactiveTime;
    @Column(name = "V_MAX_SESS_LOGOUT_TIME")
    private String maxSessionTime;
    @Column(name = "V_IS_ERROR")
    Integer isError;
    @Column(name = "V_ERR_CODE")
    String errCode;
    @Column(name = "V_ERR_DESC")
    String errDesc;

    public String getPasswordStrength() {
        return passwordStrength;
    }

    public void setPasswordStrength(String passwordStrength) {
        this.passwordStrength = passwordStrength;
    }

    public String getMaxInactiveTime() {
        return maxInactiveTime;
    }

    public void setMaxInactiveTime(String maxInactiveTime) {
        this.maxInactiveTime = maxInactiveTime;
    }

    public String getMaxSessionTime() {
        return maxSessionTime;
    }

    public void setMaxSessionTime(String maxSessionTime) {
        this.maxSessionTime = maxSessionTime;
    }

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }
    
}
