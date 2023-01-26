/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.toedter.calendar.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author AlmarDave
 */
public class Generator {
    
    Connection con;
    public String getAccountID()
    {
        String code;
        do {
            code = String.valueOf(Math.random() * (1020853914 - 0000000000));
        } while (isNotAvailable(code,"ACCOUNT","AccID"));
        
    return code;
}
    
    public int getAge(JDateChooser date)
    {
        LocalDate d = LocalDate.now();
        int age = d.getYear() - date.getDate().getYear();
        return age;
    }
    
    public String getReturnDate()
    {
        int d,m,y;
        String date,year = "", day = "",month = "";
        char a[] = new char[9];
        java.util.Date sdate = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh");  
        String strDate = dateFormat.format(sdate);  
        System.out.println("Converted String: " + strDate);  
        
        for (int k = 0; k < a.length; k++) {
            if (k < 4) {
                year += a[k];
            }
            else if (k < 6) {
                month += a[k];
            }
            else if (k < 8) {
                day += a[k];
            }
        }
        d = Integer.valueOf(day);
        m = Integer.valueOf(month);
        y = Integer.valueOf(year);
        if ((d + 3) > 28 && m == 2) {
            d = d + 3;d = d - 28;m++;
        }
        else if ((d + 3) > 31 && m == 12) {
            d = d + 3;d = d - 31;m = 1;y++;
        }
        else if ((d+ 3)> 31 && m < 8 && (m % 2) == 1) {
             d = d + 3;d = d - 31;m++;
        }
        else if ((d+ 3)> 30 && m < 8 && (m % 2) == 0) {
             d = d + 3;d = d - 30;m++;
        }
        else if ((d+ 3)> 31 && m >= 8 && (m % 2) == 0) {
            d = d + 3;d = d - 31;m++;
        }
        else if ((d+ 3)> 30 && m >= 8 && (m % 2) == 1) {
            d = d + 3;d = d - 30;m++;
        }
        date = y +"-"+ m +"-"+ d;
        return date;
    }
    
    public String getReturnKey()
    {
        String key;
        do {
            key = String.valueOf((Math.random() * (10000000 - 99999999)));
        } while (isNotAvailable(key,"Manifest","RetKey"));
        
        return key;
    }
    
    public int setBookID()
    {
        int id = 0000000000;
        return id;
    }
    
    public String getBorrowID()
    {
        String code;
        do {
        code = String.valueOf(Math.random() * (1000000 - 999999));
        } while (isNotAvailable(code,"manifest","BorID"));
        
        return code;
    }
    
    String getActivationCode()
    {
        String code;
        code = String.valueOf(Math.random() * (100000 - 999999));
        return code;
    }
    
    private boolean isNotAvailable(String code, String table,String field)
    {
        boolean flag = false;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB_LMS;integratedSecurity=true","DESKTOP-VTJ87TV\\AlmarDave","");
            PreparedStatement s = con.prepareStatement("select * from "+table+" where "+field+" = "+code);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() + "\n" + ex.getLocalizedMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }
    
}
