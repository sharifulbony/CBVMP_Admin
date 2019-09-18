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
@Table(name="GEN_CMPO_USER_ROLE")
@NamedNativeQuery(
        name = "sp_insert_user_role_map",
        query = "CALL INS_USR_ROLE_MAP(?,:p1,:p2,:p3)",
        callable = true,
        resultClass = UserRoleMapModel.class

)
public class UserRoleMapModel implements Serializable{
    
    @Id
    @Column(name="ID")
    private Long id;
    
    @Column(name="USER_ID")
    private Long userId;
    
    @Column(name="ROLE_ID")
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    
}
