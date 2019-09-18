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
public class TotalLoginInfoResponse {
    @XmlElement(name="category")
    private ArrayList<String> category;
    @XmlElement(name="cmpo_id")
    private ArrayList<String> cmpoId;
    @XmlElement(name="color_code")
    private ArrayList<String> colorCode;
    @XmlElement(name="total_count")
    private ArrayList<Integer> totalCount;

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public ArrayList<String> getCmpoId() {
        return cmpoId;
    }

    public void setCmpoId(ArrayList<String> cmpoId) {
        this.cmpoId = cmpoId;
    }

    public ArrayList<String> getColorCode() {
        return colorCode;
    }

    public void setColorCode(ArrayList<String> colorCode) {
        this.colorCode = colorCode;
    }

    public ArrayList<Integer> getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(ArrayList<Integer> totalCount) {
        this.totalCount = totalCount;
    }
 
    
}
