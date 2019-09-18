/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

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
        name = "sp_dash_ec_verification",
        query = "CALL DASH_EC_VERIFICATION(?,:p1)",
        callable = true,
        resultClass = DashECVerificationModel.class
)
public class DashECVerificationModel {
     @Id
    @Column(name="ROW_COUNT")
    private Integer id;
    @Column(name="CMPO_NAME")
    private String cmpo;
    @Column(name="TOTAL_NID")
    private Integer  totalNid  ;
    @Column(name="THUMB_NOT_FOUND")
    private Integer  thumbNotFound  ;
    @Column(name="NID_OK")
    private Integer   nidOk ;
    @Column(name="NID_NOT_OK")
    private Integer nidNotOK   ;
    @Column(name = "COLOR_CODE")
    private String colorCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCmpo() {
        return cmpo;
    }

    public void setCmpo(String cmpo) {
        this.cmpo = cmpo;
    }

    public Integer getTotalNid() {
        return totalNid;
    }

    public void setTotalNid(Integer totalNid) {
        this.totalNid = totalNid;
    }

    public Integer getThumbNotFound() {
        return thumbNotFound;
    }

    public void setThumbNotFound(Integer thumbNotFound) {
        this.thumbNotFound = thumbNotFound;
    }

    public Integer getNidOk() {
        return nidOk;
    }

    public void setNidOk(Integer nidOk) {
        this.nidOk = nidOk;
    }

    public Integer getNidNotOK() {
        return nidNotOK;
    }

    public void setNidNotOK(Integer nidNotOK) {
        this.nidNotOK = nidNotOK;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    
    
}
