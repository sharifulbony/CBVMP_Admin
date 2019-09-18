/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author tanbir
 */
//@NamedNativeQuery(
//        name = "sp_insert_user_role",
//        query = "CALL INS_ROLE(?,:p1,:p2)",
//        callable = true,
//        resultClass = CmpoRoleModel.class
//)
//@Entity
//@Table(name = "GEN_ROLE")
//public class CmpoRoleModel implements Serializable {
//
//    @Id
//    @Column(name = "ID")
//    private Long id;
//    @Column(name = "NAME")
//    private String name;
//    @Column(name="IS_ACTIVE")
//    private boolean active;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//}
@Entity
@Table(name = "GEN_ROLE")
public class CmpoRoleModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cmpo_role_id_seq")
    @SequenceGenerator(name = "cmpo_role_id_seq",
            sequenceName = "GEN_ROLE_SEQ",
            allocationSize = 1)

    Long id;
    @Column(name = "NAME")
    String name;
    @Column(name = "CMPO_ID")
    Integer cmpoId;
    @Column(name = "IS_ACTIVE")
    Boolean active;
    @Column(name = "LOGIN_NO")
    Integer loginNo;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private UserModel createdById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCmpoId() {
        return cmpoId;
    }

    public void setCmpoId(Integer cmpoId) {
        this.cmpoId = cmpoId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(Integer loginNo) {
        this.loginNo = loginNo;
    }

    public UserModel getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserModel createdById) {
        this.createdById = createdById;
    }

}
