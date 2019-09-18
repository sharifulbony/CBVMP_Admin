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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rahat
 */

@Entity
@Table(name = "BULK_REQ_DETAIL")
public class BulkEntryDetailsModel implements Serializable {

    @Id
    @Column(name = "DETAIL_NO")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bulk_detail_sec")
    @SequenceGenerator(name = "bulk_detail_sec",
            sequenceName = "BULK_REQ_DETAIL_SEQ",
            allocationSize = 1)

    private Long id;

    @Column(name = "REQ_NO")
    private Long reqNo;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "REF_SIM_INFO_NO")
    private String refSimNo;
    @Column(name = "EXECUTE_TIME")
    private Date executeTime;
    @Column(name = "IS_EXECUTE")
    private Integer isExecute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReqNo() {
        return reqNo;
    }

    public void setReqNo(Long reqNo) {
        this.reqNo = reqNo;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRefSimNo() {
        return refSimNo;
    }

    public void setRefSimNo(String refSimNo) {
        this.refSimNo = refSimNo;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getIsExecute() {
        return isExecute;
    }

    public void setIsExecute(Integer isExecute) {
        this.isExecute = isExecute;
    }
    
    

}
