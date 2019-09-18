/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import cbvmp.admin.data.model.PasswordConfigModel;
import cbvmp.admin.data.model.PolicyModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class PasswordConfigModelManager extends ModelManager{
    public PasswordConfigModel get(Long id){
        return (PasswordConfigModel) getEntity(PasswordConfigModel.class, id);
    }
    public void save(PasswordConfigModel passwordConfigModel){
        saveEntity(passwordConfigModel);
    }
    
    public void update(PasswordConfigModel passwordConfigModel){
        updateEntity(passwordConfigModel);
    }
    public List<PasswordConfigModel> listAll (){
        //System.out.println("done");
        return listAll(PasswordConfigModel.class);
        
    }
}
