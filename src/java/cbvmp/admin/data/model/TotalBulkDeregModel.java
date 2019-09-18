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
 * @author tanbir
 */

@Entity
@NamedNativeQuery(
        name = "sp_get_bulk_dereg",
        query = "CALL DASH_BULK_DEREG(?,:p1)",
        callable = true,
        resultClass = TotalBulkDeregModel.class
)
public class TotalBulkDeregModel implements Serializable{
    
    @Id
    @Column(name="ROWCOUNT")
    private Integer id;
    @Column(name="CMPO")
    private String cmpo;
    @Column(name="V_TOTAL_VALID_MSISDN")
    private Integer totalValidMsisdn;
    @Column(name="V_TOTAL_EXECUTED")
    private Integer totalCountExcuted;
    @Column(name="COLOR_CODE")
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

    public Integer getTotalValidMsisdn() {
        return totalValidMsisdn;
    }

    public void setTotalValidMsisdn(Integer totalValidMsisdn) {
        this.totalValidMsisdn = totalValidMsisdn;
    }

    public Integer getTotalCountExcuted() {
        return totalCountExcuted;
    }

    public void setTotalCountExcuted(Integer totalCountExcuted) {
        this.totalCountExcuted = totalCountExcuted;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    
    
    
}
