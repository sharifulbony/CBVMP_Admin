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
public class ParentNodeResponse {

    @XmlElement(name = "title")
    String title;
    @XmlElement(name = "expand")
    boolean expand;
    
    @XmlElement(name="select")
    boolean select;
    
    @XmlElement(name="key")
    String key;
    
    @XmlElement(name = "children")
    ArrayList<ChildNodeResponse> children;
    
    public ParentNodeResponse(){
        this.children = new ArrayList<ChildNodeResponse>();
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public ArrayList<ChildNodeResponse> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ChildNodeResponse> children) {
        this.children = children;
    }

}
