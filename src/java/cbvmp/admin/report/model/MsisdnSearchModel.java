/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;



/**
 *
 * @author farhan
 */
@Entity
@NamedNativeQueries({
  
        @NamedNativeQuery(
        name = "sp_msisdn_search",
        query = "CALL  msisdn_search(?,:p1,:p2)",
        callable = true,
        resultClass = MsisdnSearchModel.class
        )
//        ,
//        @NamedNativeQuery(
//        name = "sp_msisdn_search_history_citycell",
//        query = "CALL msisdn_search_history_citycell (?,:p1,:p2)",
//        callable = true,
//        resultClass = MsisdnSearchModel.class
//        )
}      
)


public class MsisdnSearchModel {

    @Id
    @Column(name = "doc_type_no")
    Integer docTypeNo;
    // @Column(name = "ROW_COUNT")
    // String rowCount;
    @Column(name = "doc_type")
    String docType;

    @Column(name = "otp_no")
    String otpNo;

    @Column(name = "document_id")
    String documentId;

    @Column(name = "reg_date")
    String regDate;

    @Column(name = "create_time")
    String createTime;

    @Column(name = "imsi")
    String imsi;

    @Column(name = "cmpo_trn_id")
    String cmpoTrnId;

    @Column(name = "is_corporate")
    String isCorporate;

    @Column(name = "purpose_no")
    String purposeNo;

    @Column(name = "is_registered")
    String isRegistered;

    public String getOtpNo() {
        return otpNo;
    }

    public void setOtpNo(String otpNo) {
        this.otpNo = otpNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Integer getDocTypeNo() {
        return docTypeNo;
    }

    public void setDocTypeNo(Integer docTypeNo) {
        this.docTypeNo = docTypeNo;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getCmpoTrnId() {
        return cmpoTrnId;
    }

    public void setCmpoTrnId(String cmpoTrnId) {
        this.cmpoTrnId = cmpoTrnId;
    }

    public String getIsCorporate() {
        return isCorporate;
    }

    public void setIsCorporate(String isCorporate) {
        this.isCorporate = isCorporate;
    }

    public String getPurposeNo() {
        return purposeNo;
    }

    public void setPurposeNo(String purposeNo) {
        this.purposeNo = purposeNo;
    }

    public String getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    
    
}
