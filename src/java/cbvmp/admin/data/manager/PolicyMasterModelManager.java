/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.model.PolicyMasterModel;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PolicyMasterModelManager extends ModelManager {
    
    public PolicyMasterModel get (Long id){
        return (PolicyMasterModel) getEntity(PolicyMasterModel.class, id);
    }
    
    public void save(PolicyMasterModel policyMasterModel){
        saveEntity(policyMasterModel);
    }
    
    public void update(PolicyMasterModel policyMasterModel){
        updateEntity(policyMasterModel);
    }
    
    public List<PolicyMasterModel> listAll (){
        return listAll(PolicyMasterModel.class);
    }
}
