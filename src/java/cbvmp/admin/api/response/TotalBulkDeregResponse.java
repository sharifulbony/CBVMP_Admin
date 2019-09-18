/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api.response;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tanbir
 */
@XmlRootElement
public class TotalBulkDeregResponse {
    @XmlElement(name="cmpo")
    private ArrayList<String> cmpoList;
    @XmlElement(name="total_valid_msisdn")
    private ArrayList<Integer> totalValidMsisdn;
    @XmlElement(name="total_executed")
    private ArrayList<Integer> totalExecuted;
    @XmlElement(name="color_code")
    private ArrayList<String> colorCode;

    public ArrayList<String> getCmpoList() {
        return cmpoList;
    }

    public void setCmpoList(ArrayList<String> cmpoList) {
        this.cmpoList = cmpoList;
    }

    public ArrayList<Integer> getTotalValidMsisdn() {
        return totalValidMsisdn;
    }

    public void setTotalValidMsisdn(ArrayList<Integer> totalValidMsisdn) {
        this.totalValidMsisdn = totalValidMsisdn;
    }

    public ArrayList<Integer> getTotalExecuted() {
        return totalExecuted;
    }

    public void setTotalExecuted(ArrayList<Integer> totalExecuted) {
        this.totalExecuted = totalExecuted;
    }

    public ArrayList<String> getColorCode() {
        return colorCode;
    }

    public void setColorCode(ArrayList<String> colorCode) {
        this.colorCode = colorCode;
    }
    
    
}
