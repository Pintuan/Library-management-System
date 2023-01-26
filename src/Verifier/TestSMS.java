/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verifier;

import iTexmoSMS.iTexmoDriver;

/**
 *
 * @author AlmarDave
 */
public class TestSMS {
    public static void main(String[] args) {
        String MobileNo = "09756454470";
        String Message = "Message ko to";
        String ApiCode = "TR-ALMAR454470_18485";
        
        int returnCode = new iTexmoDriver().sendMsgTo(MobileNo, Message, ApiCode);
        if (returnCode == 0) {
            System.out.println("Message Sent.");
        }
        else {
            System.err.println("Error . Return Code: "+returnCode);
        }
    }
}
