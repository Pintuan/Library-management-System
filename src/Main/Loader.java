/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author AlmarDave
 */
public class Loader {
    String connection = "jdbc:sqlserver://localhost:1433;databaseName=DB_LMS;integratedSecurity=true",username = "DESKTOP-VTJ87TV\\AlmarDave",pass = "";
    DefaultTableModel model = new DefaultTableModel();

    public String getAuthor(String Author)
    {
        try {
            Connection con = DriverManager.getConnection(this.connection);
            PreparedStatement s = con.prepareStatement("Select * From Author where AuthID = "+ Author);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                Author = rs.getString("AuthorName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Author;
    }
    public String getPublisher(String Publisher)
    {
        try {
            //fetch publisher name in the database
            Connection con = DriverManager.getConnection(this.connection);
            PreparedStatement s = con.prepareStatement("Select * From Publisher where PubID = "+ Publisher);//use of primary key from the book data
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                Publisher = rs.getString("PubName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Publisher;
    }
    
    //count of books in the library
    public int getBookCount() throws SQLException
    {
        int ctr = 0;
        ResultSet rs = getTable("BOOK");
        while (rs.next()) {
            ctr++;
        }
        return ctr;
    }
    //get the book count of a specific book
    public int getCount(String BookTitle) throws SQLException
    {
        int ctr = 0;
        ResultSet rs = getTable("BOOK where BookTitle = '"+BookTitle+"'");
        while (rs.next()) {
            ctr++;
        }
        return ctr;
    }
    public int countTableContent(String Table) throws SQLException
    {
        int ctr = 0;
        ResultSet rs = getTable(Table);
        while (rs.next()) {
            ctr++;
        }
        return ctr;
    }
    
    //count of books that is borrowed returned and lost
    public String getBookCount(String AccountID) throws SQLException
    {
        String bookCount;
        int ctr = 0;
        ResultSet rs = getTable("Manifest",("AccID = "+AccountID));
        while (rs.next()) {
            ctr++;
        }
        bookCount = String.valueOf(ctr);
        return bookCount;
    }
    
    //count of books that is borrowed but not yet returned or lost
    public String getPendingBookCount(String AccountID) throws SQLException
    {
        String bookCount;
        int ctr = 0;
        ResultSet rs = getTable("Manifest","AccID = "+AccountID+" AND borStat != 'returned'");
        while (rs.next()) {
            ctr++;
        }
        bookCount = String.valueOf(ctr);
        return bookCount;
    }
    
    public DefaultTableModel getTableContentModel(String table)
    {
        ResultSet rs;
        try {
            model.addColumn("Serial No.");model.addColumn("Title");model.addColumn("Genre");model.addColumn("Date Published");
            Connection con = DriverManager.getConnection(connection,username,pass);
            PreparedStatement s = con.prepareStatement("Select * from " + table +" where BookStat = 'Available'");
            rs = s.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[] {rs.getString("BOOKID"),rs.getString("BookTitle"),rs.getString("genre"),rs.getString("PubDate")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() + "\n" + ex.getLocalizedMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    public DefaultTableModel getTableContentModel(DefaultTableModel model,String Table,String Tag)
    {
        ResultSet rs;
        try {
            model.addColumn("Serial No.");model.addColumn("Title");model.addColumn("Genre");model.addColumn("Date Published");
            Connection con = DriverManager.getConnection(connection,username,pass);
            PreparedStatement s = con.prepareStatement("Select * from BOOK where genre like '%"+Tag+"%' and BookStat = 'Available'");
            rs = s.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[] {rs.getString("BOOKID"),rs.getString("BookTitle"),rs.getString("genre"),rs.getString("PubDate")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() + "\n" + ex.getLocalizedMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    private ResultSet getTable(String Table, String Condition)
    {
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection(connection,username,pass);
            PreparedStatement s = con.prepareStatement("Select * from "+Table+" where " + Condition);
            rs = s.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() + "\n" + ex.getLocalizedMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    private ResultSet getTable(String table)
    {
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection(connection,username,pass);
            PreparedStatement s = con.prepareStatement("Select * from "+table);
            rs = s.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() + "\n" + ex.getLocalizedMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
