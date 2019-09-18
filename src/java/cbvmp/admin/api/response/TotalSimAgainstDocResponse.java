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
public class TotalSimAgainstDocResponse {

    @XmlElement(name = "doc_type")
    private ArrayList<String> docType;
    @XmlElement(name = "sim_count")
    private ArrayList<Integer> simCount;
    @XmlElement(name="color_code")
    private ArrayList<String> colorCode;

    public ArrayList<String> getDocType() {
        return docType;
    }

    public void setDocType(ArrayList<String> docType) {
        this.docType = docType;
    }

    public ArrayList<Integer> getSimCount() {
        return simCount;
    }

    public void setSimCount(ArrayList<Integer> simCount) {
        this.simCount = simCount;
    }

    public ArrayList<String> getColorCode() {
        return colorCode;
    }

    public void setColorCode(ArrayList<String> colorCode) {
        this.colorCode = colorCode;
    }
    
}
