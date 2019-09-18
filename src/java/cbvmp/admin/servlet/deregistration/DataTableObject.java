/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.servlet.deregistration;

import cbvmp.admin.data.model.BulkEntryDetailsModel;
import java.util.List;

/**
 *
 * @author rahat
 */
public class DataTableObject {

    int     draw;
    String  recordsTotal;
    String  recordsFiltered;
    List<BulkEntryDetailsModel> aadata;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(String recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public String getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(String recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<BulkEntryDetailsModel> getAadata() {
        return aadata;
    }

    public void setAadata(List<BulkEntryDetailsModel> aadata) {
        this.aadata = aadata;
    }


    
    

}
