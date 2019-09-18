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
 * @author Administrator
 */
@XmlRootElement
public class DashOtpInfoResponse {

    @XmlElement(name = "cmpo")
    private ArrayList<String> cmpoList;
    @XmlElement(name = "consent_qty")
    private ArrayList<Integer> consent;
    @XmlElement(name = "unconsent_qty")
    private ArrayList<Integer> unconsent;
    @XmlElement(name = "color_code")
    private ArrayList<String> colorCode;

    public ArrayList<String> getCmpoList() {
        return cmpoList;
    }

    public void setCmpoList(ArrayList<String> cmpoList) {
        this.cmpoList = cmpoList;
    }

    public ArrayList<Integer> getConsent() {
        return consent;
    }

    public void setConsent(ArrayList<Integer> consent) {
        this.consent = consent;
    }

    public ArrayList<Integer> getUnconsent() {
        return unconsent;
    }

    public void setUnconsent(ArrayList<Integer> unconsent) {
        this.unconsent = unconsent;
    }

    public ArrayList<String> getColorCode() {
        return colorCode;
    }

    public void setColorCode(ArrayList<String> colorCode) {
        this.colorCode = colorCode;
    }

}
