/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.services;

import com.mycompany.libmanagement.JdbcUtils;
import com.pqm.pojo.Publisher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pminh
 */
public class PublisherServices {
    public static Publisher getPublisherById(int id) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        String sql = "SELECT * FROM category WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        while(rs.next())
        {
             return new Publisher(rs.getInt("id"),rs.getString("name"));
        }
        return null;
    }
}
