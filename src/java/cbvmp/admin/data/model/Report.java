/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

/**
 *
 * @author rahat
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.annotations.NamedNativeQuery;

@NamedNativeQuery(
        name = "sp_report_test",
        query = "call SHOWREPORTS(?,:p1,:p2)",
        callable = true,
        resultClass = Report.class)
@Entity
//@SecondaryTable(name="GEN_MENUS")
public class Report implements Serializable {

    @Id
    /* private String DATE_COL;
    private String NAME_COL;
    private String EMAIL;
    private String WEBSITE; 
    private String SUBJECT;
    private String MESSAGE;*/

    //@Column(table="GEN_MENUS")
    /*private String MENU_NAME;
    private String NAME;*/
    @Column(table = "DOCUMENT_ID")
    private String docId;
    @Column(table = "RETAIL_ID")
    private String retailId;
    @Column(table = "MSISDN")
    private String msisdn;
    @Column(table = "CMPO_TRN_ID")
    private String cmpoTrnId;
    @Column(table = "EC_SESS_ID")
    private String ecSessId;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getRetailId() {
        return retailId;
    }

    public void setRetailId(String retailId) {
        this.retailId = retailId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCmpoTrnId() {
        return cmpoTrnId;
    }

    public void setCmpoTrnId(String cmpoTrnId) {
        this.cmpoTrnId = cmpoTrnId;
    }

    public String getEcSessId() {
        return ecSessId;
    }

    public void setEcSessId(String ecSessId) {
        this.ecSessId = ecSessId;
    }

    

}
