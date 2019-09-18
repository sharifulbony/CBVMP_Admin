/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.api.request;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class DataTableRequest {
    @XmlElement(name="draw")
    Integer draw;
    @XmlElement(name="start")
    Integer start;
    @XmlElement(name="length")
    Integer length;
    @XmlElement(name="columns")
    List<DataTableColumn> columns;
    @XmlElement(name="order")
    List<DataTableOrder> order;
    @XmlElement(name="search")
    DataTableSearch search;
    
    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<DataTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTableColumn> columns) {
        this.columns = columns;
    }

    public List<DataTableOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DataTableOrder> order) {
        this.order = order;
    }

    public DataTableSearch getSearch() {
        return search;
    }

    public void setSearch(DataTableSearch search) {
        this.search = search;
    }
    
}
