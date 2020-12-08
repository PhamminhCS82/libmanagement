package com.mycompany.libmanagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pqm.pojo.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kiet Tat
 */
public class UserformController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private Label lbUserId;
    @FXML
    private Label lbUserName;
    @FXML
    private Label lbUserSex;
    @FXML
    private Label lbDateOfBirth;
    @FXML
    private Label lbPosition;
    @FXML
    private Label lbDepartment;
    @FXML
    private Label lbExpiriedDate;
    @FXML
    //private TextField lbUserId;
    public void setUser(User u){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
