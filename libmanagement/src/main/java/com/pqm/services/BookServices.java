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

/**
 *
 * @author pminh
 */
public class BookServices {
    public static List<Books> getBooks() throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * FROM libmanage.books";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Books> books = new ArrayList<>();
        while(rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int publishYear = rs.getInt("publish_year");
            String describe = rs.getString("describe"); 
            String auth = rs.getString("authors");
            String publish = rs.getString("publisher");
            String cat = rs.getString("category");
//            String location = rs.getString("location");
            Books b = new Books(id ,name, auth, describe, publish, cat, publishYear);
            books.add(b);
        }
        return books;
    }
}
