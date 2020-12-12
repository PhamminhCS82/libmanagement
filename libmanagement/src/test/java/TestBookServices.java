
import com.pqm.pojo.Book;
import com.pqm.services.BookServices;
import com.pqm.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class TestBookServices {
    private static Connection conn;
    @BeforeAll
    public static void setUp(){
        conn = JdbcUtils.getConnection();
    }
    @AfterAll
    public static void tearDown(){
        try {
            JdbcUtils.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TestBookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testNoFilter(){
        try {
            ObservableList<Book> b1 = BookServices.getBooks(" ", -1);
            ObservableList<Book> b2 = BookServices.getBooks(null, -1);
            assertEquals(b1.size(), b2.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestBookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testFilter(){
        try {
            String kw = "      N    ";
            ObservableList<Book> kq = BookServices.getBooks(kw, -1);
            for(Book b: kq)
                assertTrue(b.getName().contains(kw.trim()));
        } catch (SQLException ex) {
            Logger.getLogger(TestBookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}