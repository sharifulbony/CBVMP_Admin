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
        name = "sp_get_user_role",
        query = "CALL GET_USER_ROLE(?,:p1,:p2)",
        callable = true,
        resultClass = UserRoleMapProcModel.class
)
public class UserRoleMapProcModel implements Serializable {
    
    @Id
    @Column(name="ID")
    Long id;
    
    @Column(name="NAME")
    String name;
    
    @Column(name="USER_ID")
    Long userId;
    
    @Column(name="IS_ALLOWED")
    boolean allowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
    
    
    
    
    
}
