/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 */
//@author pminh 
public class JdbcUtils {
    private static Connection conn;
    private static final String user = "root", password = "123456789"; //sua lai user va password de phu hop voi MySQL tren may 
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/libmanage", user, password);
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(JdbcUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
