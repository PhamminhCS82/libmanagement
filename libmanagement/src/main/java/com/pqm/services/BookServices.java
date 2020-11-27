/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.mycompany.libmanagement.JdbcUtils;
import com.pqm.pojo.Authors;
import com.pqm.pojo.Books;
import com.pqm.pojo.Category;
import com.pqm.pojo.Publisher;
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
    public static List<Books> getBooks(String kw) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * FROM books";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Books> books = new ArrayList<>();
        while(rs.next())
        {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int cateId = rs.getInt(3);
            int authId = rs.getInt(4);
            int publisherId = rs.getInt(5);
            int publishYear = rs.getInt(6);
            String describe = rs.getString(7);
            Category cat = CategoryServices.getCategoryById(cateId);
            Authors auth = AuthorServices.getAuthorById(authId);
            Publisher publish = PublisherServices.getPublisherById(publisherId);
            Books b = new Books(id ,name, auth, describe, publish, cat, publishYear);
            books.add(b);
        }
        return books;
    }
}
