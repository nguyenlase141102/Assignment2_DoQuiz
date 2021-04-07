/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.valid;

/**
 *
 * @author ANH NGUYEN
 */
public class Validation {
    public static boolean checkEmpty(String value) {
        boolean result;
        return result = value.equals("") ? false : true;
    }

    public static boolean checkSpecialCharacter(String value) {        
        boolean result;
        result = !value.matches("^[a-zA-Z0-9 ]{0,300}$") ? false : true;
        return result;       
    }

}
