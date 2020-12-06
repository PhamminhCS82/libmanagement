/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.pqm.pojo.Book;
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
 * @author pminh
 */
public class BookServices {
    public static ObservableList<Book> getBooks(String kw, int indexCat) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * FROM libmanage.books";
        if(kw != null && !kw.trim().isEmpty())
        {
            ComboCat d = ComboCat.getValueByIndex(indexCat);
            if(d != null)
                sql += " WHERE " + d + " like ?";
            else
                sql += " WHERE name like ?";
            
        }
        PreparedStatement stm = conn.prepareStatement(sql);
        if (kw != null && !kw.trim().isEmpty())
        {
            stm.setString(1, String.format("%%%s%%", kw.trim()));
        }
        ResultSet rs = stm.executeQuery();
        ObservableList<Book> books = FXCollections.observableArrayList();
        while(rs.next())
        {
            Book b = new Book(rs.getInt("id") ,rs.getString("name")
                    , rs.getString("authors"), rs.getString("describe")
                    , rs.getString("publisher"), rs.getString("category")
                    , rs.getString("location"),rs.getString("publish_year"),rs.getDate("dayadded"));
            books.add(b);
        }
        return books;
    }
    public static boolean addBook(Book b) { 
        Connection conn = JdbcUtils.getConnection();
        try {
            
            conn.setAutoCommit(false);
            String sql = "INSERT INTO books(name, books.describe, publisher, authors, location, category, publish_year, dayadded)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, b.getName());
            stm.setString(2, b.getDescribe());
            stm.setString(3, b.getPublisher());
            stm.setString(4, b.getAuthor());
            stm.setString(5, b.getLocation());
            stm.setString(6, b.getCategory());
            stm.setString(7, b.getYear());
            stm.setDate(8, b.getDayAdded());
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
    public static boolean deleteBook(int bookId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String sql = "DELETE FROM books WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, bookId);
        
        int kq = stm.executeUpdate();
        
        return kq > 0;
    }
    enum ComboCat{
        name(0), authors(1), publisher(2), category(3);
        private final int index;
        ComboCat(int index){
            this.index = index;
        }
        public int getIndex(){
            return this.index;
        }
        public static ComboCat getValueByIndex(int index){
            for(ComboCat d: ComboCat.values()){
                if(d.index == index)
                    return d;
            }
            return null;
        }
    }
}
