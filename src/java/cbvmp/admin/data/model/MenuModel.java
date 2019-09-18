/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tanbir
 */

@Entity
@Table(name = "GEN_MENU")
public class MenuModel implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    
    @Basic
    @Column(name="ALIAS")
    private String alias;
    
    @Basic
    @Column(name="URL")
    private String url;
    
    @Basic
    @Column(name="PARENT_ID")
    private Long parentId;
    
    @Column(name="USER_CAT_ID")
    private Integer userCategory;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(Integer userCategory) {
        this.userCategory = userCategory;
    }
    
}
