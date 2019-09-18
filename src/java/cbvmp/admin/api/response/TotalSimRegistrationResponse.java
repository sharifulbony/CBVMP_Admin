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
public class TotalSimRegistrationResponse {
    @XmlElement(name="sim_count")
    private ArrayList<Integer> dataList;
    @XmlElement(name="cmpo")
    private ArrayList<String> cmpoList;
    @XmlElement(name="color_code")
    private ArrayList<String> colorCode;

    public ArrayList<Integer> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<Integer> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<String> getCmpoList() {
        return cmpoList;
    }

    public void setCmpoList(ArrayList<String> cmpoList) {
        this.cmpoList = cmpoList;
    }

    public ArrayList<String> getColorCode() {
        return colorCode;
    }

    public void setColorCode(ArrayList<String> colorCode) {
        this.colorCode = colorCode;
    }
    
    
 
}
