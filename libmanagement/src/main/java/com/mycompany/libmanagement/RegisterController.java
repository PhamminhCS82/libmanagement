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
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public void addUserHandler(ActionEvent evt) {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String userId = " ";
        String surname = txtSurname.getText().trim();
        String firstname = txtFirstname.getText().trim();
        String sex = txtSex.getText().trim();
        String position = txtPosition.getText().trim();
        String department = txtDepartment.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        if (!surname.isEmpty() && !firstname.isEmpty() && !sex.isEmpty()
                && txtDateOfBirth.getValue() != null && !position.isEmpty()
                && !department.isEmpty()) {
            userId = StringUtils.createUserId(surname, firstname);
            if (email.isEmpty() || StringUtils.emailPattern(email)) {
                if (calendar.get(Calendar.YEAR) - txtDateOfBirth.getValue().getYear() < 18) {
                    alert.setContentText("Ngày sinh không hợp lệ hoặc quá trẻ để đăng kí");
                    alert.show();
                } else {
                    User u = new User(StringUtils.removeAccent(userId), StringUtils.standardizedString(surname), StringUtils.standardizedString(firstname),
                            sex.replaceAll("\\s+", " "), txtDateOfBirth.getValue().toString(), position.replaceAll("\\s+", " "),
                            department.replaceAll("\\s+", " "), email, address.replaceAll("\\s+", " "), txtPhoneNum.getText());

                    if (UserServices.addUser(u) == true) {
                        alert.setContentText("SUCCESSFUL");
                        Stage stage = (Stage) txtAddress.getScene().getWindow();
                        stage.close();
                    } else {
                        alert.setContentText("FAILED");
                    }
                }

                alert.show();
            } else {
                alert.setContentText("Email sai định dạng");
                alert.show();
            }
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
