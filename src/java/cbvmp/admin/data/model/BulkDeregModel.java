/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rahat
 */
@Entity
@Table(name = "BULK_REQ_LIST")
public class BulkDeregModel implements Serializable {

    @Id
    @Column(name = "REQ_NO")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bulk_req_sec")
    @SequenceGenerator(name = "bulk_req_sec",
            sequenceName = "BULK_LIST_SEQ",
            allocationSize = 1)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "CMPO_NO")
    private CmpoModel cmpoNo;
    @Column(name = "TOTAL_MSISDNS")
    private Integer totalMsisdn;
    @Column(name = "VALID_MSISDNS")
    private Integer validMsisdn;
    @Column(name = "BATCH_ID")
    private String batchId;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private UserModel createdById;

    @Column(name = "CREATE_LOGON_NO")
    private Integer createdLogonNo;

    @Column(name = "TOTAL_EXECUTED")
    private Integer totalExecuted;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmpoModel getCmpoNo() {
        return cmpoNo;
    }

    public void setCmpoNo(CmpoModel cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

    public Integer getTotalMsisdn() {
        return totalMsisdn;
    }

    public void setTotalMsisdn(Integer totalMsisdn) {
        this.totalMsisdn = totalMsisdn;
    }

    public Integer getValidMsisdn() {
        return validMsisdn;
    }

    public void setValidMsisdn(Integer validMsisdn) {
        this.validMsisdn = validMsisdn;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserModel createdById) {
        this.createdById = createdById;
    }

    public Integer getCreatedLogonNo() {
        return createdLogonNo;
    }

    public void setCreatedLogonNo(Integer createdLogonNo) {
        this.createdLogonNo = createdLogonNo;
    }

    public Integer getTotalExecuted() {
        return totalExecuted;
    }

    public void setTotalExecuted(Integer totalExecuted) {
        this.totalExecuted = totalExecuted;
    }
    

}
