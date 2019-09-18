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
 * @author rahat
 */

@Entity
@NamedNativeQuery(
        name = "sp_delete_black_doc",
        query = "CALL DELETE_BLACK_LIST_DOC(?,:p1,:p2,:p3,:p4)",
        callable = true,
        resultClass = BlackListDeleteModel.class
)
public class BlackListDeleteModel implements Serializable{
    
    @Id
    @Column(name="V_IS_ERROR")
    private Integer isError;
    @Column(name="V_ERR_DESC")
    private String errDesc;

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }
    
    
  

}