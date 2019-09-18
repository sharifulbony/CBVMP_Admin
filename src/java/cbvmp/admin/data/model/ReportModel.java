/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Administrator
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "sp_report_test",
            query = "CALL REPORT_TEST(?,:p1,:p2,:p3,:p4,:p5)",
            callable = true,
            resultClass = ReportModel.class
    )
})

//@Entity
//@NamedNativeQueries({
//    @NamedNativeQuery(
//            name = "sp_verify_user",
//            query = "CALL GEN_USER_SALT(?,:p1)",
//            callable = true,
//            resultClass = UserInfoModel.class
//    )
//
//})

public class ReportModel {

    
    @Id
    @Column(name = "NID")
    String NID;
    @Column(name = "SIM_COUNT")
    Long simCount;
    @Column(name =  "DOC_TYPE_NO")
    Long docTypeNo;

    
    @Column(name = "COUNT_NO")
    Long countNo;
    @Column (name = "V_COUNT")
    Integer columnCount;

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public Long getSimCount() {
        return simCount;
    }

    public void setSimCount(Long simCount) {
        this.simCount = simCount;
    }
    public Long getDocTypeNo() {
        return docTypeNo;
    }

    public void setDocTypeNo(Long docTypeNo) {
        this.docTypeNo = docTypeNo;
    }

    public Long getCountNo() {
        return countNo;
    }

    public void setCountNo(Long countNo) {
        this.countNo = countNo;
    }
    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }
    
}
