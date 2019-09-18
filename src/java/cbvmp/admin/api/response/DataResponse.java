/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */

@XmlRootElement
public class DataResponse{
    
    @XmlElement(name="nid_no")
    private String nidNo;
    @XmlElement(name="sim_count")
    private String simCount;
    @XmlElement(name="doctype_no")
    private String docTypNo;

    public String getNidNo() {
        return nidNo;
    }

    public void setNidNo(String nidNo) {
        this.nidNo = nidNo;
    }

    public String getSimCount() {
        return simCount;
    }

    public void setSimCount(String simCount) {
        this.simCount = simCount;
    }

    public String getDocTypNo() {
        return docTypNo;
    }

    public void setDocTypNo(String docTypNo) {
        this.docTypNo = docTypNo;
    }
    
    
    
    
}
