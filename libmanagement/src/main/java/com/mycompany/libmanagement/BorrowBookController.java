package com.mycompany.libmanagement;

import com.pqm.pojo.Book;
import com.pqm.services.BookServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BorrowBookController implements Initializable{
    @FXML
    private TableView tbBook;
    @FXML
    private TableView tbBorrow;
    @FXML
    private ComboBox cbKeyword;
    @FXML
    private TextField txtKeyword;
    public ObservableList<Book> listB = FXCollections.observableArrayList();
    public void loadBook(TableView tb){
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
        tb.getColumns().addAll(clName,clPublisher
                ,clAuthor,clCategory,clYear);
    }
    private void loadData(String kw, int indexCat) throws SQLException{
        tbBook.getItems().clear();
        tbBook.setItems(BookServices.getBooks(kw, indexCat));
    }
    
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] list = {"Tên sách", "Tác giả", "Nhà xuất bản", "Thể loại"};
        cbKeyword.getItems().addAll(Arrays.asList(list));
        try {
            loadBook(tbBorrow);
            loadBook(tbBook);
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
        tbBook.setRowFactory(tv -> { 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không thể mượn thêm!");
            TableRow<Book> row = new TableRow<>(); 
            row.setOnMouseClicked(event -> { 
                if (event.getClickCount() == 2 && (! row.isEmpty())) {
                    if(tbBorrow.getItems().size() < 5){
                        Book rowData = row.getItem();
                        listB.add(rowData);
                        tbBorrow.setItems(listB);
                    }
                    else
                        alert.show();
                }
            }); 
        return row ; 
        }); 
    }
    
}