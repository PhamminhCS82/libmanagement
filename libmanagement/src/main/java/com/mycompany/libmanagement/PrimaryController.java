package com.mycompany.libmanagement;

import com.pqm.pojo.Books;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PrimaryController implements Initializable{
    
    @FXML
    private TableView<Books> tbBooks;
    private void loadBooks(){
        TableColumn clName = new TableColumn("Tên sách");
        TableColumn clPublisher = new TableColumn("Nhà xuất bản");
        TableColumn clAuthor = new TableColumn("Tác giả");
        TableColumn clCategory = new TableColumn("Thể loại");
        TableColumn clYear = new TableColumn("Năm xuất bản");
        TableColumn clDescribe = new TableColumn("Thông tin");
        this.tbBooks.getColumns().addAll(clName);
        this.tbBooks.getColumns().addAll(clPublisher);
        this.tbBooks.getColumns().addAll(clAuthor);
        this.tbBooks.getColumns().addAll(clCategory);
        this.tbBooks.getColumns().addAll(clYear);
        this.tbBooks.getColumns().addAll(clDescribe);
        //this.tbBooks.setItems(FXCollections.observableArrayList(""));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBooks();
    }
}
