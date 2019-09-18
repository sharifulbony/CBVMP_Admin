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
        name = "sp_get_acl_list",
        query = "CALL GET_ACL_LIST(?,:p1,:p2)",
        callable = true,
        resultClass = GetAccessControlListModel.class
)
public class GetAccessControlListModel implements Serializable {
    
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="ALIAS")
    private String alias;
    @Column(name="URL")
    private String url;
    @Column(name="PARENT_ID")
    private Integer parentId;
    @Column(name="IS_ALLOWED")
    private boolean allowed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
    
    
    
    
    
    
    
}
