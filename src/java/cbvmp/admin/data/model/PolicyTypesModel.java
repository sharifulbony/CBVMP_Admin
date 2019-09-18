/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "GEN_POLICY_TYPES")
public class PolicyTypesModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "POLICY_TYPE_NO")
    private Long policyTypeNO;

    @Basic
    @Column(name = "POLICY_TYPE")
    private String policyType;

      

    public Long getPolicyTypeNO() {
        return policyTypeNO;
    }

    public void setPolicyTypeNO(Long policyTypeNO) {
        this.policyTypeNO = policyTypeNO;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
   

}
