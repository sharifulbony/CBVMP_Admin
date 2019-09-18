/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "GEN_DOC_TYPE")
public class DocumentTypeModel {
    @Id
    @Column(name = "DOC_TYPE_NO")
    Integer docTypeNo;
    @Column(name = "DOC_TYPE")
    String docType;

    public Integer getDocTypeNo() {
        return docTypeNo;
    }

    public void setDocTypeNo(Integer docTypeNo) {
        this.docTypeNo = docTypeNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
  

}
