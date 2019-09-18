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
        name = "sp_sim_ver_cmpo_wise",
        query = "CALL SIM_VER_CMPO_WISE(?,:p1)",
        callable = true,
        resultClass = SimVerCmpoWiseModel.class
)

public class SimVerCmpoWiseModel implements Serializable {
    @Id
    @Column(name = "ROW_COUNT")
    String rowCount;
    @Column(name = "CMPO_NO")
    Integer cmpoNo;
    @Column(name = "CMPO_NAME")
    String cmpoName;
    @Column(name = "V_CONSENT_QTY")
    Integer consentQty;
    @Column(name = "V_UNCONSENT_QTY")
    Integer unconsentQty;

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getCmpoNo() {
        return cmpoNo;
    }

    public void setCmpoNo(Integer cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

    public String getCmpoName() {
        return cmpoName;
    }

    public void setCmpoName(String cmpoName) {
        this.cmpoName = cmpoName;
    }

    public Integer getConsentQty() {
        return consentQty;
    }

    public void setConsentQty(Integer consentQty) {
        this.consentQty = consentQty;
    }

    public Integer getUnconsentQty() {
        return unconsentQty;
    }

    public void setUnconsentQty(Integer unconsentQty) {
        this.unconsentQty = unconsentQty;
    }

   
    
}
