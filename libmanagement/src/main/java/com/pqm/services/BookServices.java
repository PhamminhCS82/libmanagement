/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.mycompany.libmanagement.JdbcUtils;
import com.pqm.pojo.Books;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pminh
 */
public class BookServices {
    public static ObservableList<Books> getBooks(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * FROM libmanage.books";
        if(kw != null &&  !kw.trim().isEmpty())
            sql += "WHERE name like ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        if (kw != null && !kw.trim().isEmpty())
            stm.setString(1, String.format("%%%s%%", kw.trim()));
        ResultSet rs = stm.executeQuery();
        ObservableList<Books> books = FXCollections.observableArrayList();
        while(rs.next())
        {
            Books b = new Books(rs.getInt("id") ,rs.getString("name")
                    , rs.getString("authors"), rs.getString("describe"),
                    rs.getString("publisher"), rs.getString("category"), rs.getString("location"),rs.getString("publish_year"));
            books.add(b);
        }
        return books;
    }
    public static boolean addBook(Books q) { 
        Connection conn = JdbcUtils.getConnection();
        try {
            
            conn.setAutoCommit(false);
            String sql = "INSERT INTO books(name, describe, publisher, authors, location, category, publish_year)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, q.getName());
            stm.setString(2, q.getDescribe());
            stm.setString(3, q.getPublisher());
            stm.setString(4, q.getAuthor());
            stm.setString(5, q.getLocation());
            stm.setString(6, q.getCategory());
            stm.setString(7, q.getYear());
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
