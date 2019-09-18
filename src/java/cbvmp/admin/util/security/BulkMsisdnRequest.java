/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.util.security;

import java.util.ArrayList;

/**
 *
 * @author tanbir
 */
public class BulkMsisdnRequest {

    private Integer totalCount;
    private Integer totalValidNumber;
    private Integer totalInValidNumber;
    private ArrayList<String> validMsisdnList;
    private ArrayList<String> invalidMsisdnList;

    public BulkMsisdnRequest() {
        totalCount = 0;
        totalValidNumber = 0;
        totalInValidNumber = 0;
        validMsisdnList = null;
        invalidMsisdnList = null;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalValidNumber() {
        return totalValidNumber;
    }

    public void setTotalValidNumber(Integer totalValidNumber) {
        this.totalValidNumber = totalValidNumber;
    }

    public Integer getTotalInValidNumber() {
        return totalInValidNumber;
    }

    public void setTotalInValidNumber(Integer totalInValidNumber) {
        this.totalInValidNumber = totalInValidNumber;
    }
    

    public ArrayList<String> getValidMsisdnList() {
        return validMsisdnList;
    }

    public void setValidMsisdnList(ArrayList<String> validMsisdnList) {
        this.validMsisdnList = validMsisdnList;
    }

    public ArrayList<String> getInvalidMsisdnList() {
        return invalidMsisdnList;
    }

    public void setInvalidMsisdnList(ArrayList<String> invalidMsisdnList) {
        this.invalidMsisdnList = invalidMsisdnList;
    }
    

}
