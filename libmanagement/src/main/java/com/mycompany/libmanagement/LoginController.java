/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author user
 */

public class LoginController implements Initializable{
    @FXML private TextField txtLoginId;
    @FXML private TextField txtPassword;
    public void registerHandler(ActionEvent evt) throws IOException{
    }
    
    public void loginHandler(ActionEvent evt) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(UserServices.getUserByUserLoginIdAndPassword(txtLoginId.getText(), txtPassword.getText()) != null)
        {}
        else
            alert.setContentText("Đăng nhập thất bại");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
