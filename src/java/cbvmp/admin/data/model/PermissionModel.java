/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.model;

import java.util.LinkedList;

/**
 *
 * @author tanbir
 */
public class PermissionModel {
    
    String module;
    LinkedList<String> actions;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public LinkedList<String> getActions() {
        return actions;
    }

    public void setActions(LinkedList<String> actions) {
        this.actions = actions;
    }
    
    
}
