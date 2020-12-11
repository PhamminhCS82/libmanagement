/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.pqm.pojo.User;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
                    + ", email, address, phone)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public static boolean isIdAndPasswordCorrect(String loginId, String password) throws SQLException, NoSuchAlgorithmException{
        Connection conn = JdbcUtils.getConnection();
        StringUtils md5 = new StringUtils();
        password = md5.convertHashToString(password);
        String sql = "SELECT * FROM manager WHERE id = ? AND password = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, loginId);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            User u = new User(rs.getString("id"), rs.getString("password"));             
            if(u.getLoginId().equals(loginId) && u.getPassword().equals(password))
                return true;
        }
        return false;
    }
    
    public static ObservableList<User> getUsers(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * FROM libmanage.users";
        if(kw != null && !kw.trim().isEmpty())
        {
                sql += " WHERE userId like ? OR surname like ? "
                        + "OR firstname like ? OR sex like ? OR dateofbirth like ?";     
        }
        PreparedStatement stm = conn.prepareStatement(sql);
        if (kw != null && !kw.trim().isEmpty())
        {
            stm.setString(1, String.format("%%%s%%", kw.trim()));
            stm.setString(2, String.format("%%%s%%", kw.trim()));
            stm.setString(3, String.format("%%%s%%", kw.trim()));
            stm.setString(4, String.format("%%%s%%", kw.trim()));
            stm.setString(5, String.format("%%%s%%", kw.trim()));
        }
        ResultSet rs = stm.executeQuery();
        ObservableList<User> users = FXCollections.observableArrayList();
        while(rs.next())
        {
            User u = new User(rs.getInt("id") ,rs.getString("userId")
                    , rs.getString("surname"), rs.getString("firstname")
                    , rs.getString("sex"), rs.getString("dateofbirth")
                    , rs.getString("position"),rs.getString("department")
                    , rs.getString("email"), rs.getString("address"), rs.getString("phone")
                    , rs.getDate("createddate"), rs.getDate("expirieddate"));
            users.add(u);
        }
        return users;
    }
    
    public static boolean deleteUser(int id) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        int kq = stm.executeUpdate();
        
        return kq > 0;
    }

}
