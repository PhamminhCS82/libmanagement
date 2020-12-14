package com.mycompany.libmanagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pqm.pojo.User;
import com.pqm.services.StringUtils;
import com.pqm.services.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private int id;

    public void setUser(User u) {
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
        id = u.getId();
        txtPhoneNum.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPhoneNum.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void updateUserProfileHandler(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String email = txtEmail.getText().trim();
        String address = txtAddress.getText().trim();
        if (email.isEmpty() || StringUtils.emailPattern(email)) {
            User update = new User(id, email, address, txtPhoneNum.getText());
            if (UserServices.userProfileUpdateById(update)) {
                alert.setContentText("Đã lưu");
            } else {
                alert.setContentText("Thất bại");
            }
            alert.show();
        }
        else
        {
            alert.setContentText("Email nhập sai");
            alert.show();
        }

    }
}
