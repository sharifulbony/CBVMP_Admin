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
            name = "sp_update_user_lock",
            query = "CALL CHECK_USER_LOCK(?,:p1,:p2)",
            callable = true,
            resultClass = UserLockModel.class
    )

})

public class UserLockModel {

    @Id
    @Column(name = "V_IS_ERROR")
    Integer isError;
    @Column(name = "V_ERR_CODE")
    String errCode;
    @Column(name = "V_ERR_DESC")
    String errDesc;

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

