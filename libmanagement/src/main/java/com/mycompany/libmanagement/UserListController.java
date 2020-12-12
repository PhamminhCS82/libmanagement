/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.pojo.User;
import com.pqm.services.BookServices;
import com.pqm.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class UserListController implements Initializable{
    @FXML
    private TableView<User> tbUser;
    @FXML
    private TextField txtKeyword;

    private void loadUsers(){
        TableColumn clId = new TableColumn("Mã người dùng");
        clId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        
        TableColumn clSurname = new TableColumn("Họ");
        clSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        
        TableColumn clFirstname = new TableColumn("Tên");
        clFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        
        TableColumn clSex = new TableColumn("Giới tính");
        clSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        
        TableColumn clBirthDay = new TableColumn("Ngày sinh");
        clBirthDay.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        
        TableColumn clPosition = new TableColumn("Đối tượng");
        clPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
         
        TableColumn clAction = new TableColumn();
        clAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Xóa");
            btn.setOnAction(evt -> {
                
                Button bt = (Button) evt.getSource();
                TableCell c = (TableCell) bt.getParent();
                User u = (User) c.getTableRow().getItem();
                
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa thông tin thành viên này?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        try {
                            if (UserServices.deleteUser(u.getId()))
                                this.loadData("");
                        } catch (SQLException ex) {
                            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
            });
            
            cell.setGraphic(btn);
            return cell;
        });
        TableColumn clBorrowAction = new TableColumn();
        clBorrowAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Mượn sách");
            btn.setOnAction(evt -> {
                
                try {
                    Button bt = (Button) evt.getSource();
                    TableCell c = (TableCell) bt.getParent();
                    User u = (User) c.getTableRow().getItem();
                    if(BookServices.isUserReturnBook(u.getId())){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("borrowbook.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage borrowBookStage = new Stage();
                        borrowBookStage.setTitle("Danh mục sách trong thư viện");
                        BorrowBookController controller = loader.getController();
                        controller.getUserId(u);
                        borrowBookStage.setScene(scene);
                        borrowBookStage.initModality(Modality.APPLICATION_MODAL);
                        borrowBookStage.show();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Chưa trả sách!!!");
                        alert.show();
                    }
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cell.setGraphic(btn);
            return cell;
        });
        
        TableColumn clReturnAction = new TableColumn();
        clReturnAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Trả sách");
            btn.setOnAction(evt -> {
                
                try {
                    Button bt = (Button) evt.getSource();
                    TableCell c = (TableCell) bt.getParent();
                    User u = (User) c.getTableRow().getItem();
                    if(!BookServices.isUserReturnBook(u.getId())){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("returnbook.fxml"));
                        Scene scene = new Scene(loader.load());
                        ReturnBookController controller = loader.getController();
                        controller.loadBooks(u);
                        Stage borrowBookStage = new Stage();
                        borrowBookStage.setTitle("Danh mục sách chưa trả");
                        borrowBookStage.setScene(scene);
                        borrowBookStage.initModality(Modality.APPLICATION_MODAL);
                        borrowBookStage.show();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Không có sách nào để trả!!");
                        alert.show();
                    }
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cell.setGraphic(btn);
            return cell;
        });
        tbUser.getColumns().addAll(clId,clSurname,clFirstname
                ,clSex,clBirthDay,clPosition,clAction, clBorrowAction, clReturnAction);
    }
    
    private void loadData(String kw) throws SQLException{
        tbUser.getItems().clear();
        tbUser.setItems(UserServices.getUsers(kw));
    }
    
    public void refresh(ActionEvent evt) throws SQLException{
        loadData("");
    }
    
    public void addUserHandler(ActionEvent evt) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
        Scene scene = new Scene(loader.load());
        Stage registerStage = new Stage();
        registerStage.setTitle("Thêm thẻ thành viên");
        registerStage.setScene(scene);
        registerStage.initModality(Modality.APPLICATION_MODAL);
        registerStage.show();
    }
    
    public void backSceneHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("managerlibmenu.fxml"));
        Parent backParent = loader.load();
        Scene scene = new Scene(backParent);
        stage.setScene(scene);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtKeyword.textProperty().addListener(et -> {
            try {
                loadData(txtKeyword.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        try {
            loadUsers();
            loadData("");
        } catch (SQLException ex) {
            Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbUser.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("userform.fxml"));
                        Scene scene = new Scene(loader.load());
                        UserformController controller = loader.getController();
                        controller.setUser(rowData);
                        Stage borrowBookStage = new Stage();
                        borrowBookStage.setTitle("Thông tin thành viên");
                        borrowBookStage.setScene(scene);
                        borrowBookStage.initModality(Modality.APPLICATION_MODAL);
                        borrowBookStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
    }
}
