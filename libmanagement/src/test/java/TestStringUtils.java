
import com.pqm.services.StringUtils;
import org.junit.jupiter.api.Assertions;
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
public class TestStringUtils {
    @Test
    public void testRemoveAccent(){
        String s = "Phạm Quáng Mình";
        Assertions.assertEquals("Pham Quang Minh", StringUtils.removeAccent(s));
    }
    @Test
    public void testCreateIdUser(){
        String s1 = "Phạm   Quáng  ";
        String s2 = "  Mình  ";
        String s3 = StringUtils.createUserId(s1, s2);
        Assertions.assertEquals("minh.pq", StringUtils.removeAccent(s3));
    }
    @Test
    public void testStandardizedString(){
        String s = "pham quang   minh  ";
        Assertions.assertEquals("Pham Quang Minh", StringUtils.standardizedString(s));
    }
    
}
