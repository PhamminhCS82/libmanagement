package com.mycompany.libmanagement;

import com.pqm.pojo.Books;
import com.pqm.pojo.Category;
import com.pqm.services.BookServices;
import com.pqm.services.CategoryServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable{
    
    @FXML
    private TableView<Books> tbBooks;
    @FXML
    ComboBox<Category> cbCate;
    private void loadBooks(){
        TableColumn clName = new TableColumn("Tên sách");
        clName.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn clPublisher = new TableColumn("Nhà xuất bản");
        clPublisher.setCellValueFactory(new PropertyValueFactory("publisher_id"));
        TableColumn clAuthor = new TableColumn("Tác giả");
        clAuthor.setCellValueFactory(new PropertyValueFactory("author_id"));
        TableColumn clCategory = new TableColumn("Thể loại");
        clCategory.setCellValueFactory(new PropertyValueFactory("category"));
        TableColumn clYear = new TableColumn("Năm xuất bản");
        clYear.setCellValueFactory(new PropertyValueFactory("publish_year"));
        TableColumn clDescribe = new TableColumn("Thông tin");
        clDescribe.setCellValueFactory(new PropertyValueFactory("describe"));
        this.tbBooks.getColumns().addAll(clName,clPublisher,clAuthor,clCategory,clCategory,clYear,clDescribe);
        
    }
    
    private void loadData(String kw) throws SQLException{
        tbBooks.getItems().clear();
        tbBooks.setItems(FXCollections.observableArrayList(BookServices.getBooks(kw)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbCate.getItems().addAll(CategoryServices.getCategories());
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            
////            loadBooks();
////            loadData("");
//        } catch (SQLException ex) {
//            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
