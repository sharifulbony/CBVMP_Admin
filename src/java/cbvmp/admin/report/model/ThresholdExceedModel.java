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
        name = "sp_doc_wise_msisddn_thrs_exceed",
        query = "CALL DOC_WISE_MSISDDN_THRS_EXCEED(?,:p1,:p2,:p3)",
        callable = true,
        resultClass = ThresholdExceedModel.class
)
public class ThresholdExceedModel {
    @Id
    @Column(name = "ROW_COUNT")
    String rowCount;
    @Column(name = "NID")
    String docId;
    @Column(name = "DOC_TYPE")
    String docType;
    @Column(name = "SIM_COUNT")
    Integer simCount;

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Integer getSimCount() {
        return simCount;
    }

    public void setSimCount(Integer simCount) {
        this.simCount = simCount;
    }
    
    
}
