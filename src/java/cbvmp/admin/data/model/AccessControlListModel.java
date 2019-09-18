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
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author tanbir
 */
@Entity
@NamedNativeQuery(
        name = "sp_get_user_acl",
        query = "CALL GET_USER_ACL(?,:p1)",
        callable = true,
        resultClass = AccessControlListModel.class
)
public class AccessControlListModel implements Serializable {

    @Id
    @Column(name="ID")
    Long id;
    
    @Column(name = "USER_ID")
    Long userId;
    @Column(name = "ALIAS")
    String alias;
    @Column(name = "URL")
    String url;
    @Column(name = "IS_ALLOWED")
    Boolean allowed;
    @Column(name="MENU_ID")
    Long menuId;
    @Column(name="ROLE_ID")
    Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    
}
