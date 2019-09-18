/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class DataTableColumn {
    
    @XmlElement(name="data")
    private String data; 
    
    @XmlElement(name="name")
    private String name; 
    
    @XmlElement(name="searchable")
    private boolean searchable; 
    
    @XmlElement(name="ordable")
    private boolean orderable; 
    
    @XmlElement(name="search")
    private DataTableSearch search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public DataTableSearch getSearch() {
        return search;
    }

    public void setSearch(DataTableSearch search) {
        this.search = search;
    }
    
    
    
    
}
