/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tanbir
 */
@XmlRootElement
public class ThresholdExceedResponse extends GeneralResponse{
    
    @XmlElement(name="data")
    private List<DataResponse> data;
    public List<DataResponse> getData() {
        return data;
    }
    public void setData(List<DataResponse> data) {
        this.data = data;
    }
    
    
}
