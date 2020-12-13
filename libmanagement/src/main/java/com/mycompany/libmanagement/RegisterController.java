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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtSex;
    @FXML
    private DatePicker txtDateOfBirth;
    @FXML
    private TextField txtPosition;
    @FXML
    private TextField txtDepartment;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhoneNum;
    @FXML
    private GridPane gridPane;

    public void addUserHandler(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String userId = " ";
        if (txtSurname.getText() != null && txtFirstname.getText() != null && txtSex.getText() != null
                && txtDateOfBirth.getValue() != null && txtPosition.getText() != null && txtDepartment.getText() != null) {
            userId = StringUtils.createUserId(txtSurname.getText(), txtFirstname.getText());
            User u = new User(StringUtils.removeAccent(userId), StringUtils.standardizedString(txtSurname.getText()), StringUtils.standardizedString(txtFirstname.getText()), txtSex.getText(),
                    txtDateOfBirth.getValue().toString(), txtPosition.getText(), txtDepartment.getText(),
                    txtEmail.getText(), txtAddress.getText(), txtPhoneNum.getText());

            if (UserServices.addUser(u) == true) {
                alert.setContentText("SUCCESSFUL");
                Stage stage = (Stage) txtAddress.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("FAILED");
            }

            alert.show();
        } else {
            alert.setContentText("Chưa nhập đủ thông tin bắt buộc!!!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtPhoneNum.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPhoneNum.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
