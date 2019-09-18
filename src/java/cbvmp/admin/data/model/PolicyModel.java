/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

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
        name = "sp_edit_policy",
        query = "CALL EDIT_POLICY_MASTER(?)",
        callable = true,
        resultClass = PolicyModel.class
)
public class PolicyModel implements Serializable {

    @Id
    @Column(name = "POLICY_NO")
    String policyNo;
    @Column (name = "POLICY_TYPE_NO")
    Long PolicyTypeNo;
    @Column(name = "POLICY_TYPE")
    String policyType;
    @Column(name = "POLICY_VALUE")
    String policyVal;

    
    @Column(name = "POLICY_START")
    String policyStart;
    @Column(name = "POLICY_END")
    String policyEnd;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }
    public Long getPolicyTypeNo() {
        return PolicyTypeNo;
    }

    public void setPolicyTypeNo(Long PolicyTypeNo) {
        this.PolicyTypeNo = PolicyTypeNo;
    }
    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPolicyVal() {
        return policyVal;
    }

    public void setPolicyVal(String policyVal) {
        this.policyVal = policyVal;
    }

    public String getPolicyStart() {
        return policyStart;
    }

    public void setPolicyStart(String policyStart) {
        this.policyStart = policyStart;
    }

    public String getPolicyEnd() {
        return policyEnd;
    }

    public void setPolicyEnd(String policyEnd) {
        this.policyEnd = policyEnd;
    }

     
}
