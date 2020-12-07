/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.pojo.User;
import com.pqm.services.StringUtils;
import com.pqm.services.UserServices;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author user
 */
public class RegisterController implements Initializable{
    @FXML private TextField txtSurname;
    @FXML private TextField txtFirstname;
    @FXML private TextField txtSex;
    @FXML private DatePicker txtDateOfBirth;
    @FXML private TextField txtLoginId;
    @FXML private TextField txtPassword;
    @FXML private TextField txtPosition;
    @FXML private TextField txtDepartment;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhoneNum;
    public void addUserHandler(ActionEvent evt) {
        String userId = " ";
        if(txtSurname.getText() != null && txtFirstname.getText() != null)
            userId = StringUtils.createUserId(txtSurname.getText(), txtFirstname.getText());
        User u = new User(userId, txtSurname.getText(),txtFirstname.getText(),txtSex.getText()
                ,txtDateOfBirth.getValue().toString(),txtPosition.getText(),txtDepartment.getText()
                , txtEmail.getText(), txtAddress.getText() ,txtPhoneNum.getText()
                , txtLoginId.getText(), txtPassword.getText());
        System.out.println(txtLoginId.getText());
        System.out.println(txtPassword.getText());
//        System.out.println(txtDepartment.getText());
//        System.out.println(txtEmail.getText());
//        System.out.println(txtAddress.getText());
//        System.out.println(txtPhoneNum.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (UserServices.addUser(u) == true) {
            alert.setContentText("SUCCESSFUL");
        } else {
            alert.setContentText("FAILED");
        }
        
        alert.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
}
