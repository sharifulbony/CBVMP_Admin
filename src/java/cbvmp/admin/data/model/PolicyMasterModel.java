/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "SET_POLICY_MASTER")

public class PolicyMasterModel implements Serializable {

    //private PolicyTypesModel policyTypesModel;
//    @Id
//    @Column(name = "POLICY_NO")
//    private Long policyNO;
    @Id
    @Column(name = "POLICY_NO")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_master_seq")
    @SequenceGenerator(name = "policy_master_seq",
            sequenceName = "SET_POLICY_MASTER_SEQ",
            allocationSize = 1)

    private Long policyNO;

    @Column(name = "POLICY_TYPE_NO")
    private Long policyTypeNo;
    @Column(name = "POLICY_VALUE")
    private String policyValue;

    @Column(name = "IS_MULTI")
    private Integer isMulti;

    @Column(name = "POLICY_START")
    private Date policyStart;
    //private String policyStart;

    @Column(name = "POLICY_END")
    private Date policyEnd;

    @Column(name = "LOGIN_NO")
    Integer loginNo;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private UserModel createdById;
    //private String policyEnd;

    public Long getPolicyNO() {
        return policyNO;
    }

    public void setPolicyNO(Long policyNO) {
        this.policyNO = policyNO;
    }

    public Long getPolicyTypeNo() {
        return policyTypeNo;
    }

    public void setPolicyTypeNo(Long policyTypeNo) {
        this.policyTypeNo = policyTypeNo;
    }

    public String getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(String policyValue) {
        this.policyValue = policyValue;
    }

    public Integer getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(Integer isMulti) {
        this.isMulti = isMulti;
    }
//;

    public Date getPolicyStart() {
        return policyStart;
    }

    public void setPolicyStart(Date policyStart) {
        this.policyStart = policyStart;
    }

    public Date getPolicyEnd() {
        return policyEnd;
    }

    public void setPolicyEnd(Date policyEnd) {
        this.policyEnd = policyEnd;
    }

//    public String getPolicyStart() {
//        return policyStart;
//    }
//
//    public void setPolicyStart(String policyStart) {
//        this.policyStart = policyStart;
//    }
//
//    public String getPolicyEnd() {
//        return policyEnd;
//    }
//
//    public void setPolicyEnd(String policyEnd) {
//        this.policyEnd = policyEnd;
//    }
    public Integer getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(Integer loginNo) {
        this.loginNo = loginNo;
    }

    public UserModel getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserModel createdById) {
        this.createdById = createdById;
    }

}
