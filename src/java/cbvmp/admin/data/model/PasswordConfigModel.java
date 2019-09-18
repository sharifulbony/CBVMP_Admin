/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "PASSWORD_CONFIG")
public class PasswordConfigModel {
    @Id
    @Column(name = "PASS_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_config_seq")
    @SequenceGenerator(name = "password_config_seq", 
                   sequenceName = "PASS_CONFIG_NO_SEQ",
                   allocationSize = 1)
    private Long passNO;
    
    
    @Column(name = "PASS_STRENGTH")
    private String passStrength;
    @Column(name = "PASS_START")
    private Date passStart;
    @Column(name = "PASS_END")
    private Date passEnd;

    public Long getPassNO() {
        return passNO;
    }

    public void setPassNO(Long passNO) {
        this.passNO = passNO;
    }

    public String getPassStrength() {
        return passStrength;
    }

    public void setPassStrength(String passStrength) {
        this.passStrength = passStrength;
    }

    public Date getPassStart() {
        return passStart;
    }

    public void setPassStart(Date passStart) {
        this.passStart = passStart;
    }

    public Date getPassEnd() {
        return passEnd;
    }

    public void setPassEnd(Date passEnd) {
        this.passEnd = passEnd;
    }

    

    

  

  
    
    
}
