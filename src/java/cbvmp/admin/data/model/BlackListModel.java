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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "BLACK_LIST_DOCUMENT")
public class BlackListModel implements Serializable {

    @Id
    @Column(name = "BLACK_LIST_NO")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "black_list_seq")
    @SequenceGenerator(name = "black_list_seq",
            sequenceName = "BLACK_NO_SEQ",
            allocationSize = 1)

    private Long blackNo;

    
    @ManyToOne
    @JoinColumn(name = "DOC_TYPE_NO")
    private DocumentTypeModel docTypeNo;

    @Column(name = "DOCUMENT_ID")
    private String documenId;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private UserModel createdBy;

    @Column(name = "LOGIN_NO")
    private Integer loginNo;

//    @Column(name = "CREATE_TIME")
//    private Date createTime ;

    public Long getBlackNo() {
        return blackNo;
    }

    public void setBlackNo(Long blackNo) {
        this.blackNo = blackNo;
    }

    public DocumentTypeModel getDocTypeNo() {
        return docTypeNo;
    }

    public void setDocTypeNo(DocumentTypeModel docTypeNo) {
        this.docTypeNo = docTypeNo;
    }
        
    public String getDocumenId() {
        return documenId;
    }

    public void setDocumenId(String documenId) {
        this.documenId = documenId;
    }

    public UserModel getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserModel createdBy) {
        this.createdBy = createdBy;
    }  

    public Integer getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(Integer loginNo) {
        this.loginNo = loginNo;
    }
    
}
