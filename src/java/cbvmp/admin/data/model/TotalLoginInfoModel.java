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
        name = "sp_get_login_info",
        query = "CALL DASH_LOGIN_INFO(?,:p1,:p2,:p3)",
        callable = true,
        resultClass = TotalLoginInfoModel.class
)
public class TotalLoginInfoModel implements Serializable{
    
    @Id
    @Column(name="ROWCOUNT")
    private Integer id;
    @Column(name="CATEGORY")
    private String category;
    @Column(name="CMPO_ID")
    private String cmpoId;
    @Column(name="COLOR_CODE")
    private String colorCode;
    @Column(name="TOT_COUNT")
    private Integer totalCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCmpoId() {
        return cmpoId;
    }

    public void setCmpoId(String cmpoId) {
        this.cmpoId = cmpoId;
    }
    
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    
}
