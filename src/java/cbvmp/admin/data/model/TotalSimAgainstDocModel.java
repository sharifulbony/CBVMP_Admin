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
        name = "sp_get_total_sim_count_against_doc",
        query = "CALL DASH_TOT_SIM_COUNT_DOC(?,:p1,:p2,:p3)",
        callable = true,
        resultClass = TotalSimAgainstDocModel.class
)
public class TotalSimAgainstDocModel implements Serializable {

    @Id
    @Column(name = "ROWCOUNT")
    private Integer id;
    @Column(name = "DOCUMENT_TYPE")
    private String documentType;
    @Column(name = "SIM_COUNT")
    private Integer simCount;
    @Column(name = "COLOR_CODE")
    private String colorCode;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getSimCount() {
        return simCount;
    }

    public void setSimCount(Integer simCount) {
        this.simCount = simCount;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    

}
