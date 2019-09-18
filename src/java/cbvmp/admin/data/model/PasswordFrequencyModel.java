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
 * @author rahat
 */
@Entity
@NamedNativeQueries({
    
    @NamedNativeQuery(
            name = "sp_check_password_freq",
            query = "CALL GEN_PASS_FREQ(?,:p1)",
            callable = true,
            resultClass = PasswordFrequencyModel.class
    )

})

public class PasswordFrequencyModel {
    
    @Id 
    
    @Column(name = "OLD_PASS")
    private String oldPassword;
    @Column(name = "NEW_PASS")
    private String newPassword;

    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
       
}

