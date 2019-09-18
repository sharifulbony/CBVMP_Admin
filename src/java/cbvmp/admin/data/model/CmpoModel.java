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

/**
 *
 * @author tanbir
 */

@Entity
@Table(name="GEN_CMPO_LIST")
public class CmpoModel implements Serializable{
    @Id
    @Column(name="CMPO_NO")
    private Integer id;
    @Column(name="CMPO_NAME")
    private String cmpoName;
    @Column(name="CMPO_ID")
    private String cmpoId;
    @Column(name="IS_ACTIVE")
    boolean Active;
    @Column(name="CMPO_SEQ")
    Integer cmpoSeq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCmpoName() {
        return cmpoName;
    }

    public void setCmpoName(String cmpoName) {
        this.cmpoName = cmpoName;
    }

    public String getCmpoId() {
        return cmpoId;
    }

    public void setCmpoId(String cmpoId) {
        this.cmpoId = cmpoId;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public Integer getCmpoSeq() {
        return cmpoSeq;
    }

    public void setCmpoSeq(Integer cmpoSeq) {
        this.cmpoSeq = cmpoSeq;
    }
    
}
