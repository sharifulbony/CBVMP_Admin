/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Administrator
 */
@Entity
@NamedNativeQuery(
        name = "sp_audit_trail",
        query = "CALL AUDIT_TRAIL(?,:p1,:p2,:p3,:p4)",
        callable = true,
        resultClass = AuditTrailModel.class
)
public class AuditTrailModel implements Serializable {

    
    
    @Id
    @Column(name = "ROW_COUNT")
    String rowCount;
    @Column(name = "CMPO_NAME")
    String cmpoName;
    
    @Column(name = "GRP_BY_DATE")
    String grpByDate;
    @Column(name =  "TOT_REG_QY")
    Integer registered;      
    @Column (name = "TOT_RE_REG_QY")
    Integer reRegistered ;    
    @Column (name = "TOT_DE_REG_QY")
    Integer deRegistered;  
    @Column (name = "TOT_MNP_DE_REG_QY")
    Integer mnpDeRegistered;  
    @Column (name = "TOT_SIM_TRNS_QY")
    Integer simTransfered;  
    @Column (name = "TOT_SIM_REP_QY")
    Integer simReplaced;  
    @Column (name = "TOT_MNP_RE_REG_QY")
    Integer mnpReRegistered;  
    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }
    public String getCmpoName() {
        return cmpoName;
    }

    public void setCmpoName(String cmpoName) {
        this.cmpoName = cmpoName;
    }

    public String getGrpByDate() {
        return grpByDate;
    }

    public void setGrpByDate(String grpByDate) {
        this.grpByDate = grpByDate;
    }

    public Integer getRegistered() {
        return registered;
    }

    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    public Integer getReRegistered() {
        return reRegistered;
    }

    public void setReRegistered(Integer reRegistered) {
        this.reRegistered = reRegistered;
    }

    public Integer getDeRegistered() {
        return deRegistered;
    }

    public void setDeRegistered(Integer deRegistered) {
        this.deRegistered = deRegistered;
    }

    public Integer getMnpDeRegistered() {
        return mnpDeRegistered;
    }

    public void setMnpDeRegistered(Integer mnpDeRegistered) {
        this.mnpDeRegistered = mnpDeRegistered;
    }

    public Integer getSimTransfered() {
        return simTransfered;
    }

    public void setSimTransfered(Integer simTransfered) {
        this.simTransfered = simTransfered;
    }

    public Integer getSimReplaced() {
        return simReplaced;
    }

    public void setSimReplaced(Integer simReplaced) {
        this.simReplaced = simReplaced;
    }

    public Integer getMnpReRegistered() {
        return mnpReRegistered;
    }

    public void setMnpReRegistered(Integer mnpReRegistered) {
        this.mnpReRegistered = mnpReRegistered;
    }
    
    
}
