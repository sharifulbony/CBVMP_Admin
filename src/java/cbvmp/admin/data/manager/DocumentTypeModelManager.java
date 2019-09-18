/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.model.DocumentTypeModel;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DocumentTypeModelManager  extends ModelManager {
    public List<DocumentTypeModel> listAll (){
        //System.out.println("done");
        return listAll(DocumentTypeModel.class);
        
    }
}
