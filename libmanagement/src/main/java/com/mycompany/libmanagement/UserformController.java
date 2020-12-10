package com.mycompany.libmanagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pqm.pojo.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kiet Tat
 */
public class UserformController {
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
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhoneNum;
    //private TextField lbUserId;
    public void setUser(User u){
        String date = String.format("%s --> %s", u.getCreatedDate().toString(), u.getExpiriedDate().toString());
        String name = String.format("%s %s", u.getSurname(), u.getFirstname());
        lbUserId.setText(u.getUserId());
        lbUserName.setText(name);
        lbUserSex.setText(u.getSex());
        lbDateOfBirth.setText(u.getDateOfBirth());
        lbPosition.setText(u.getPosition());
        lbDepartment.setText(u.getDepartment());
        lbExpiriedDate.setText(date);
        txtEmail.setText(u.getEmail());
        txtAddress.setText(u.getAddress());
        txtPhoneNum.setText(u.getPhoneNumber());
    }   
}
