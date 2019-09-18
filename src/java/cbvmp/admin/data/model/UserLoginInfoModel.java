/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author SIT
 */
@Entity
@Table(name="USER_LOGIN_INFO")
public class UserLoginInfoModel  implements Serializable{
    
    @Id
    @Column(name="LOGIN_NO")
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_no")
    @SequenceGenerator(name = "log_no", 
                   sequenceName = "LOGIN_INFO_SEQ",
                   allocationSize = 1) 
    
    private Integer login_no;
    @Column(name="USER_ID")
    private Integer id;
    @Column(name="LOGIN_TIME")
    private Date login_time;
    @Column(name="LOGOUT_TIME")
    private Date logout_time;
    @Column(name="IP_ADDR")
    private String ip_addr;
    @Column(name="AGENT_INFO")
    private String agent_info;
    @Column(name="IS_LOGIN")
    private Integer is_login;
    @Column(name="IS_SESS_TIME_OUT")
    private Integer is_sess_time_out;
    @Column(name="APP_VERSION")
    private String app_version;
    @Column(name="NODE_IP")
    private String node_ip;

    public Integer getLogin_no() {
        return login_no;
    }

    public void setLogin_no(Integer login_no) {
        this.login_no = login_no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public Date getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(Date logout_time) {
        this.logout_time = logout_time;
    }

    public String getIp_addr() {
        return ip_addr;
    }

    public void setIp_addr(String ip_addr) {
        this.ip_addr = ip_addr;
    }

    public String getAgent_info() {
        return agent_info;
    }

    public void setAgent_info(String agent_info) {
        this.agent_info = agent_info;
    }

    public Integer getIs_login() {
        return is_login;
    }

    public void setIs_login(Integer is_login) {
        this.is_login = is_login;
    }

    public Integer getIs_sess_time_out() {
        return is_sess_time_out;
    }

    public void setIs_sess_time_out(Integer is_sess_time_out) {
        this.is_sess_time_out = is_sess_time_out;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getNode_ip() {
        return node_ip;
    }

    public void setNode_ip(String node_ip) {
        this.node_ip = node_ip;
    }
    
}
