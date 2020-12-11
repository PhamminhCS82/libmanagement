/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.pojo.BorrowDetails;
import com.pqm.services.BookServices;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class FineTableController implements Initializable{
    @FXML
    private TableView<BorrowDetails> tbBorrow;
    public void loadBorrowDetails() throws SQLException{       
        TableColumn clName = new TableColumn("Tên sách");
        clName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        
        TableColumn clSurname = new TableColumn("Họ người mượn");
        clSurname.setCellValueFactory(new PropertyValueFactory<>("userSurname"));
        
        TableColumn clFirstname = new TableColumn("Tên người mượn");
        clFirstname.setCellValueFactory(new PropertyValueFactory<>("userFirstname"));
              
        TableColumn clStartDate = new TableColumn("Ngày mượn");
        clStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        
        TableColumn clEndDate = new TableColumn("Ngày hết hạn");
        clEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
         
        TableColumn clReturnDate = new TableColumn("Ngày trả");
        clReturnDate.setCellValueFactory(new PropertyValueFactory("returnDate"));
        
        TableColumn clFine = new TableColumn("Tiền phạt");
        clFine.setCellValueFactory(new PropertyValueFactory("fine"));
        tbBorrow.getColumns().addAll(clName,clSurname, clFirstname
                , clStartDate, clEndDate, clReturnDate, clFine);
        tbBorrow.getItems().clear();
        tbBorrow.setItems(BookServices.getBorrowReturnList());
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
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadBorrowDetails();
        } catch (SQLException ex) {
            Logger.getLogger(FineTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
