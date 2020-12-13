/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.pqm.pojo.Book;
import com.pqm.pojo.BorrowDetails;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;
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
    
    public static boolean addBorrowBookDetail(int idBook, int idUser){
        Connection conn = JdbcUtils.getConnection();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 30);
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO borrow(id, users_id, books_id, startdate, enddate) "
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, UUID.randomUUID().toString());
            stm.setInt(2, idUser);
            stm.setInt(3, idBook);
            stm.setDate(4, date);
            stm.setDate(5, new Date(calendar.getTimeInMillis()));
            stm.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void deleteBorrowDetail(int idUser) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        Date date = new Date(System.currentTimeMillis());
        String sql = "DELETE FROM borrow WHERE users_id =? AND startdate =? AND returndate is null";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, idUser);
        stm.setDate(2, date);
        stm.executeUpdate();
    }
    
    public static boolean isUserReturnBook(int id) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        int i = -1;
        String sql = "SELECT users_id FROM borrow WHERE users_id =? AND returndate is null";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        while(rs.next())
              i = rs.getInt("users_id");
        return i < 0;
    }
    
    public static ObservableList<Book> getBookStillNotReturn(int id) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "call libmanage.userbookstillnotreturn(?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        ObservableList<Book> books = FXCollections.observableArrayList();
        while(rs.next()){
            Book b = new Book(rs.getString("id"),rs.getString("name"),rs.getString("category"),rs.getString("authors")
            ,rs.getString("publisher"),rs.getDate("startdate"),rs.getDate("enddate"));
            books.add(b);
        }
        return books;
    }
    
    public static boolean returnBook(String id){
        Connection conn = JdbcUtils.getConnection();
        String sql = "UPDATE borrow SET returndate =? WHERE id =?";
        try { 
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, new Date(System.currentTimeMillis()));
            stm.setString(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ObservableList<BorrowDetails> getBorrowReturnList(){
        ObservableList<BorrowDetails> borrow = FXCollections.observableArrayList();
        Connection conn = JdbcUtils.getConnection();
        String sql = "call libmanage.bookreturnandfinetable()";
        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                double fine = rs.getDouble("fine");
                if(fine < 0)
                    fine = 0;
                borrow.add(new BorrowDetails(rs.getString("books.name"), rs.getString("users.surname"), rs.getString("firstname")
                        , rs.getDate("startdate"), rs.getDate("enddate"), rs.getDate("returndate"), fine));
            }
        }catch(SQLException ex){
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return borrow;
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
