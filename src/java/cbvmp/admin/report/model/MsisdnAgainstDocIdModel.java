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
        name = "sp_msisdn_agn_doc_id",
        query = "CALL MSISDN_AGN_DOC_ID(?,:p1,:p2,:p3)",
        callable = true,
        resultClass = MsisdnAgainstDocIdModel.class
)
public class MsisdnAgainstDocIdModel {
     
    @Id
    @Column(name = "ROW_COUNT")
    String rowCount;
    @Column(name = "MSISDN")
    String MSISDN;
    @Column(name = "DOC_TYPE")
    String docType;
    @Column(name =  "CMPO_NAME")
    String cmpoName;      
    @Column (name = "CMPO_NO")
    Integer cmpoNo;    

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }
   
    
    /*private String addDate;
    private String name;
    private String email;
    private String website;
    private String subject;
    private String message;
    private String recordStatus;*/

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getCmpoName() {
        return cmpoName;
    }

    public void setCmpoName(String cmpoName) {
        this.cmpoName = cmpoName;
    }

    public Integer getCmpoNo() {
        return cmpoNo;
    }

    public void setCmpoNo(Integer cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

  

    
}
