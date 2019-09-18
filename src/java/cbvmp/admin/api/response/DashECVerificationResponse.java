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
 * @author rahat
 */

@XmlRootElement
public class DashECVerificationResponse {

    @XmlElement(name = "cmpo")
    private ArrayList<String> cmpoList;
    @XmlElement(name = "total_nid")
    private ArrayList<Integer> totalNid;
    @XmlElement(name = "thumb_not_found")
    private ArrayList<Integer> thumbNot;
    @XmlElement(name = "nid_ok")
    private ArrayList<Integer> nidOk;
    @XmlElement(name = "nid_not_ok")
    private ArrayList<Integer> nidNotOk;
    @XmlElement(name = "color_code")
    private ArrayList<String> colorCode;

    public ArrayList<String> getCmpoList() {
        return cmpoList;
    }

    public void setCmpoList(ArrayList<String> cmpoList) {
        this.cmpoList = cmpoList;
    }

    public ArrayList<Integer> getTotalNid() {
        return totalNid;
    }

    public void setTotalNid(ArrayList<Integer> totalNid) {
        this.totalNid = totalNid;
    }

    public ArrayList<Integer> getThumbNot() {
        return thumbNot;
    }

    public void setThumbNot(ArrayList<Integer> thumbNot) {
        this.thumbNot = thumbNot;
    }

    public ArrayList<Integer> getNidOk() {
        return nidOk;
    }

    public void setNidOk(ArrayList<Integer> nidOk) {
        this.nidOk = nidOk;
    }

    public ArrayList<Integer> getNidNotOk() {
        return nidNotOk;
    }

    public void setNidNotOk(ArrayList<Integer> nidNotOk) {
        this.nidNotOk = nidNotOk;
    }

    public ArrayList<String> getColorCode() {
        return colorCode;
    }

    public void setColorCode(ArrayList<String> colorCode) {
        this.colorCode = colorCode;
    }

    
    
    

}
