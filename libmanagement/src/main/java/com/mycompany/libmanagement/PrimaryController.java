package com.mycompany.libmanagement;

import com.pqm.pojo.Books;
import com.pqm.services.BookServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable{
    @FXML TextField txtName;
    @FXML TableView<Books> tbBooks;
    @FXML TextField txtAuthors;
    @FXML TextArea txtDescribe;
    @FXML TextField txtCategory;
    @FXML TextField txtPublisher;
    @FXML TextField txtPublishYear;
    @FXML TextField txtLocation;
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
                
        tbBooks.getColumns().addAll(clId,clName,clPublisher
                ,clAuthor,clCategory,clYear);
    }
    
    private void loadData(String kw, int indexCat) throws SQLException{
        tbBooks.getItems().clear();
        tbBooks.setItems(BookServices.getBooks(kw, indexCat));
    }
    
    public void addBooksHandler(ActionEvent evt) {
        Books q = new Books(txtName.getText(),txtAuthors.getText(),txtDescribe.getText()
                ,txtPublisher.getText(),txtCategory.getText(),txtLocation.getText() ,(txtPublishYear.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (BookServices.addBook(q) == true) {
            alert.setContentText("SUCCESSFUL");
            try {
                loadData("", -1);
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alert.setContentText("FAILED");
        }
        
        alert.show();
        System.out.println(q.getName() + " " + q.getAuthor());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                Books b = tbBooks.getSelectionModel().getSelectedItem();
                txtName.setText(b.getName());
                txtAuthors.setText(b.getAuthor());
                txtCategory.setText(b.getCategory());
                txtPublisher.setText(b.getPublisher());
                txtPublishYear.setText(b.getYear());
                txtLocation.setText(b.getLocation());
                txtDescribe.setText(b.getDescribe());
            });
            return row;
        });
    }
}
