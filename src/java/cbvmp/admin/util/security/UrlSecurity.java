/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.util.security;

/**
 *
 * @author tanbir
 */
public class UrlSecurity {
    
    public static Integer parseUrlParams(String url){
        String[] params = url.split("/");
        Integer  value = Integer.parseInt(params[params.length-1]);
        return value;
        
    }
    
    
}
