/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.services.StringUtils;
import com.pqm.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author user
 */

public class LoginController {
    @FXML private TextField txtLoginId;
    @FXML private PasswordField txtPassword;   
    public void loginHandler(ActionEvent evt) throws SQLException, IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            if(txtLoginId != null && txtPassword != null){
                if (UserServices.isIdAndPasswordCorrect(txtLoginId.getText(), txtPassword.getText())) {
                    alert.setContentText("Đăng nhập thành công");
                    alert.show();
                    Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("managerlibmenu.fxml"));
                    Parent backParent = loader.load();
                    Scene scene = new Scene(backParent);
                    stage.setScene(scene);
                } else {
                    alert.setContentText("Đăng nhập thất bại");
                    alert.show();
                }
            }
            else{
                alert.setContentText("Chưa nhập tài khoản hoặc mật khẩu");
                alert.show();
            }
        } catch (NoSuchAlgorithmException ex) {
            alert.setContentText("Đăng nhập thất bại");
            alert.show();
        }
    }
    
    public void backSceneHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("managerlibmenu.fxml"));
        Parent backParent = loader.load();
        Scene scene = new Scene(backParent);
        stage.setScene(scene);
    }
}
