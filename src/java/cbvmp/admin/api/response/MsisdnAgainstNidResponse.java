/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class MsisdnAgainstNidResponse extends GeneralResponse {
    @XmlElement(name="data")
    private List<MsisdnAgainstNidDataResponse> data;

    public List<MsisdnAgainstNidDataResponse> getData() {
        return data;
    }

    public void setData(List<MsisdnAgainstNidDataResponse> data) {
        this.data = data;
    }
    
}
