package com.mycompany.libmanagement;

import com.pqm.pojo.Books;
import com.pqm.services.BookServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
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
    private void loadBooks(){
        TableColumn clId = new TableColumn("Mã sách");
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn clName = new TableColumn("Tên sách");
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn clPublisher = new TableColumn("Nhà xuất bản");
        clPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        
        TableColumn clAuthor = new TableColumn("Tác giả");
        clAuthor.setCellValueFactory(new PropertyValueFactory<>("authors"));
        
        TableColumn clCategory = new TableColumn("Thể loại");
        clCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        TableColumn clYear = new TableColumn("Năm xuất bản");
        clYear.setCellValueFactory(new PropertyValueFactory<>("publish_year"));
        
        TableColumn clLocation = new TableColumn("Vị trí");
        clLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        TableColumn clDescribe = new TableColumn("Thông tin");
        clDescribe.setCellValueFactory(new PropertyValueFactory<>("describe"));
        tbBooks.getColumns().addAll(clId,clName,clPublisher
                ,clAuthor,clCategory,clCategory,clYear,clDescribe, clLocation);
    }
    
    private void loadData(String kw) throws SQLException{
        tbBooks.getItems().clear();
        tbBooks.setItems(BookServices.getBooks(kw));
    }
    
    public void addBooksHandler(ActionEvent evt) {
        Books q = new Books(txtName.getText(),txtAuthors.getText(),txtDescribe.getText()
                ,txtPublisher.getText(),txtCategory.getText(),"Khu F" ,(txtPublishYear.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (BookServices.addBook(q) == true) {
            alert.setContentText("SUCCESSFUL");
            try {
                tbBooks.getItems().clear();
                tbBooks.setItems(FXCollections.observableArrayList(BookServices.getBooks("")));
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alert.setContentText("FAILED");
        }
        
        alert.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadBooks();
            loadData("");
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
