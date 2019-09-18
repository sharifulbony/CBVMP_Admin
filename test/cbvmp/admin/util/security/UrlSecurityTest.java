/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.util.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tanbir
 */
public class UrlSecurityTest {
    
    public UrlSecurityTest() {
    }

    /**
     * Test of parseUrlParams method, of class UrlSecurity.
     */
    @Test
    public void testParseUrlParams() {
        System.out.println("parseUrlParams");
        String url = "/17";
        Integer expResult = 17;
        Integer result = UrlSecurity.parseUrlParams(url);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testUrlPattern(){
        String url = "/build/css/custom.min.css";
        Pattern p = Pattern.compile("/build/*");
        Matcher m = p.matcher(url);
        boolean result = m.find();
        boolean expected = true;
        
        assertEquals(expected, result);
    }
    
}
