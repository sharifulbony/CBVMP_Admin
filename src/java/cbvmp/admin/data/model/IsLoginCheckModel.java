/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
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
            name = "sp_check_is_login",
            query = "CALL LOGIN_CHECK(?,:p1)",
            callable = true,
            resultClass = IsLoginCheckModel.class
    )
})

public class IsLoginCheckModel implements Serializable {

    @Id
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "IS_LOGIN")
    private Integer is_login;
    @Column(name = "LOGIN_NO")
    private Integer login_no;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIs_login() {
        return is_login;
    }

    public void setIs_login(Integer is_login) {
        this.is_login = is_login;
    }

    public Integer getLogin_no() {
        return login_no;
    }

    public void setLogin_no(Integer login_no) {
        this.login_no = login_no;
    }

}
