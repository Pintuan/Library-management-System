/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Main.LoginForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlmarDave
 */
public class Distributor {
    void startConnection()
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB_LMS;integratedSecurity=true","DESKTOP-T51D5JD\\AlmarDave","");
            System.out.println("connection Successful, you may now use MS SQL server in your program");
        } catch (SQLException ex) {
            System.out.println("ERROR OCCURED " + ex);
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Distributor d = new Distributor();
        d.startConnection();
    }
}
