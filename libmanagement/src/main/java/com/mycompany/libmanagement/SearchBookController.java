/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.pojo.Book;
import com.pqm.services.BookServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author user
 */
public class SearchBookController implements Initializable{
    @FXML TextArea txtDescribe;
    @FXML TableView<Book> tbBooks;
    @FXML TextField txtKeyword;
    @FXML ComboBox cbKeyword;
    private void loadBooks(){
        TableColumn clId = new TableColumn("Mã sách");
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn clName = new TableColumn("Tên sách");
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn clPublisher = new TableColumn("Nhà xuất bản");
        clPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        
        TableColumn clAuthor = new TableColumn("Tác giả");
        clAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        TableColumn clCategory = new TableColumn("Thể loại");
        clCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        TableColumn clYear = new TableColumn("Năm xuất bản");
        clYear.setCellValueFactory(new PropertyValueFactory<>("year"));
         
        TableColumn clLocation = new TableColumn("Vị trí");
        clLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        tbBooks.getColumns().addAll(clId,clName,clPublisher
                ,clAuthor,clCategory,clYear, clLocation);
    }
    
    private void loadData(String kw, int indexCat) throws SQLException{
        tbBooks.getItems().clear();
        tbBooks.setItems(BookServices.getBooks(kw, indexCat));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDescribe.setWrapText(true);
        String[] list = {"Tên sách", "Tác giả", "Nhà xuất bản", "Thể loại"};
        cbKeyword.getItems().addAll(Arrays.asList(list));
        try {
            loadBooks();
            loadData("", -1);
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtKeyword.textProperty().addListener(et -> {
            try {
                loadData(txtKeyword.getText()
                        , cbKeyword.getSelectionModel().getSelectedIndex());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tbBooks.setRowFactory(evt -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(et -> {
                Book b = tbBooks.getSelectionModel().getSelectedItem();
                txtDescribe.setText(b.getDescribe());
            });
            return row;
        });
    }  
}
