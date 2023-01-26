/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verifier;

/**
 *
 * @author AlmarDave
 */
public class TestMail {
    public void send(int Code) {
        Mailer.send("ramosalmardave45@gmail.com", "Theadora07", "delacruzramosdave@yahoo.com", "Subscription to the Library", "this is my email");
    }
    
}
