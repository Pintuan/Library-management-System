/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.regex.Pattern;

/**
 *
 * @author AlmarDave
 */
public class Verifier {
    
    boolean key = false;
    public boolean pWordVerifier(String Password)
    {
        if (Password.contains(".")&&Password.contains("[0-9]")&&Password.contains("[a-z]")) {
            key = true;
        }
        return key;
    }
    public boolean eMailVerifier(String Email)
    {
        if (Pattern.matches("[a-z|A-Z|0-9]@[yahoo|gmail|outlook].com", String.valueOf(Email))) {
             key = true;
        }
        return key;
    }
    public boolean cNoVerfier(int ContactNo)
    {
        if (Pattern.matches("[+]639[0-9]{9}", String.valueOf(ContactNo))) {
             key = true;
        }
        return key;
    }
    
}
