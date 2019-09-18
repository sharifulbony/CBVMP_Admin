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
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author tanbir
 */
@Entity
@Table(name = "GEN_ACL")
@NamedNativeQuery(
        name = "sp_update_or_save_acl",
        query = "CALL UPDATE_ACL_LIST(?,:p1,:p2,:p3)",
        callable = true,
        resultClass = AccessPermissionModel.class
)
public class AccessPermissionModel implements Serializable {

    @Id
    @Column(name = "ID")
    Long id;
    
    @Column(name = "MENU_ID")
    Integer menuId;
    @Column(name = "ROLE_ID")
    Integer roleId;
    @Column(name = "IS_ALLOWED")
    Boolean allowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

}
