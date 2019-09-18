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
        name = "sp_dash_otp_info",
        query = "CALL DASH_OTP_INFO(?,:p1)",
        callable = true,
        resultClass = DashOtpInfoModel.class
)
public class DashOtpInfoModel {

    @Id
    @Column(name = "ROW_COUNT")
    private Integer id;
    @Column(name = "CMPO_NAME")
    private String cmpo;
    @Column(name = "V_CONSENT_QTY")
    private Integer consentQty;
    @Column(name = "V_UNCONSENT_QTY")
    private Integer unconsentQty;
    @Column(name = "COLOR_CODE")
    private String colorCode;
    @Column(name = "CMPO_NO")
    private String cmpoNo;

    public Integer getId() {
        return id;
    }

    public String getCmpo() {
        return cmpo;
    }

    public Integer getConsentQty() {
        return consentQty;
    }

    public Integer getUnconsentQty() {
        return unconsentQty;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getCmpoNo() {
        return cmpoNo;
    }

    public void setCmpoNo(String cmpoNo) {
        this.cmpoNo = cmpoNo;
    }

}
