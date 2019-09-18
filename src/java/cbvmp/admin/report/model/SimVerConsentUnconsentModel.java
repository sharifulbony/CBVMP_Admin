/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.model;

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
        name = "sp_sim_ver_consent_unconsent",
        query = "CALL SIM_VER_CONSENT_UNCONSENT(?,:p1,:p2,:p3,:p4)",
        callable = true,
        resultClass = SimVerConsentUnconsentModel.class
)

public class SimVerConsentUnconsentModel {

    @Id
    @Column(name = "ROW_COUNT")
    String rowCount;
    @Column(name = "DATE_GRP_BY")
    String dateGrpBy;
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

    public String getDateGrpBy() {
        return dateGrpBy;
    }

    public void setDateGrpBy(String dateGrpBy) {
        this.dateGrpBy = dateGrpBy;
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
