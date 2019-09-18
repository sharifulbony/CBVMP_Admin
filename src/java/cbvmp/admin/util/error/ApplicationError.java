/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.util.error;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tanbir
 */
public class ApplicationError {

    // Application ERROR codes
    public static final String EXP010201 = "EXP010201";  // for requered parameter validation
    public static final String EXP010202 = "EXP010202";
    public static final String EXP010203 = "EXP010203";
    public static final String EXP010204 = "EXP010204";
    public static final String EXP010205 = "EXP010205";
    public static final String EXP010206 = "EXP010206";
    public static final String EXP010207 = "EXP010207";
    public static final Integer RQEXP404 = 404;

    // ERROR code descriptions
    public static final String EXP010202_DESC = "Access Denied for this functionality !!!";
    public static final String EXP010203_DESC = "Access token expired!!!";
    public static final String EXP010204_DESC = "Request token malformed";
    public static final String EXP010205_DESC = "Reset password!!!";
    public static final String EXP010206_DESC = "Username or Password mismatch";
    public static final String EXP010207_DESC = "Date format not matched";
    public static final String EXP010208_DESC = "Maximum try limit exits!";
    public static final String RQEXP404_DESC = "Request method not allowed";
    

    public static String validationError(String parameter) {
        return parameter.toLowerCase() + " is required ";
    }

    public static <T> List validateRequired(T target, Class<T> targetClass) throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> errorDesc = new ArrayList<String>();
        StringBuilder errors = new StringBuilder();
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            XmlElement annotation = field.getAnnotation(XmlElement.class);
            if (annotation != null && annotation.required()) {
                field.setAccessible(true);
                if (field.get(target) == null) {
//                    if (errors.length() != 0) {
//                        errors.append(System.getProperty("line.separator"));
//                    }
//                    String message = String.format("%s: required field '%s' is null.",
//                            targetClass.getSimpleName(),
//                            annotation.name());
                    String message = String.format("required field '%s' is null.",annotation.name());
                    errors.append(message);
                    break;
                }

                if (field.get(target).getClass() == String.class && field.get(target).toString().length() == 0) {
                    String message = String.format("required field '%s' is empty.",annotation.name());
                    errors.append(message);
                    break;
                }
                /*if (field.get(target).getClass() == Integer.class && Integer.parseInt(field.get(target).toString()) == 0) {
                    String message = String.format("required field '%s' is ZERO.",annotation.name());
                    errors.append(message);
                    break;
                    
                }*/
            }
        }
        if (errors.length() != 0) {
            errorDesc.add(ApplicationError.EXP010201);
            errorDesc.add(errors.toString());
        }
        return errorDesc;

    }
    
    public static boolean isValidDate(String inDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(inDate.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }  

}
