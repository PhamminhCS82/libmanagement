/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.pqm.pojo.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UserServices {
    public static boolean addUser(User u) { 
        Connection conn = JdbcUtils.getConnection();
        try {
            
            conn.setAutoCommit(false);
            String sql = "INSERT INTO users(userId, surname, firstname, sex, dateofbirth"
                    + ", position, department, createddate, expirieddate"
                    + ", email, address, phone, loginId, password)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, u.getUserId());
            stm.setString(2, u.getSurname());
            stm.setString(3, u.getFirstname());
            stm.setString(4, u.getSex());
            stm.setString(5, u.getDateOfBirth());
            stm.setString(6, u.getPosition());
            stm.setString(7, u.getDepartment());
            stm.setDate(8, u.getCreatedDate());
            stm.setDate(9, u.getExpiriedDate());
            stm.setString(10, u.getEmail());
            stm.setString(11, u.getAddress());
            stm.setString(12, u.getPhoneNumber());
            stm.setString(13, u.getLoginId());
            stm.setString(14, u.getPassword());
            stm.executeUpdate();
            conn.commit();
            
            return true;
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }        
        return false;
    }

}
