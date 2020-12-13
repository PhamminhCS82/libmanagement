
import com.pqm.pojo.User;
import com.pqm.services.JdbcUtils;
import com.pqm.services.StringUtils;
import com.pqm.services.UserServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pminh
 */
public class TestUserServices {
    private static Connection conn;

    @BeforeAll
    public static void setUp() {
        conn = JdbcUtils.getConnection();
    }

    @AfterAll
    public static void tearDown() {
        try {
            JdbcUtils.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TestBookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testAddUser(){
        String surname = "Pham   Quang ";
        String firstname = "Minh   ";
        String userId = StringUtils.createUserId(surname, firstname);
        User u = new User(StringUtils.removeAccent(userId), StringUtils.standardizedString(surname), StringUtils.standardizedString(firstname), "Nam", "2000-06-11", "Sinh viên", "Khoa CNTT", "", "", "");
        Assertions.assertTrue(UserServices.addUser(u));
    }
    @Test
    public void testDeleteUserSuccessful(){
        int id = 12;
        try {
            Assertions.assertTrue(UserServices.deleteUser(id));
        } catch (SQLException ex) {
            Logger.getLogger(TestUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
