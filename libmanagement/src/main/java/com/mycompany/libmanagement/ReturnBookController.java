/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import com.pqm.pojo.Book;
import com.pqm.pojo.User;
import com.pqm.services.BookServices;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class ReturnBookController {
    @FXML
    private TableView<Book> tbBook;
    public void loadBooks(User u) throws SQLException{       
        TableColumn clName = new TableColumn("Tên sách");
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn clPublisher = new TableColumn("Nhà xuất bản");
        clPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        
        TableColumn clAuthor = new TableColumn("Tác giả");
        clAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        TableColumn clCategory = new TableColumn("Thể loại");
        clCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        TableColumn clStartDate = new TableColumn("Ngày mượn");
        clStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        
        TableColumn clEndDate = new TableColumn("Ngày hết hạn");
        clEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
         
        TableColumn clAction = new TableColumn();
        clAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Hoàn trả");
            btn.setOnAction(evt -> {
                
                Button bt = (Button) evt.getSource();
                TableCell c = (TableCell) bt.getParent();
                Book b = (Book) c.getTableRow().getItem();
                
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Xác nhận trả sách ?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        try {
                            if (BookServices.returnBook(b.getBorrowId()))
                            {
                                this.loadData(u.getId());
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("Thành công");
                                alert.show();
                                Stage stage = (Stage) tbBook.getScene().getWindow(); 
                                stage.close(); 
                            }
                        } catch (SQLException ex) {
                            alert.setContentText("Trả sách thất bại!!");
                            alert.show();
                        }
                    }
                });
                
            });
            
            cell.setGraphic(btn);
            return cell;
        });
        
        tbBook.getColumns().addAll(clName,clPublisher
                ,clAuthor,clCategory, clAction);
        tbBook.getItems().clear();
        tbBook.setItems(BookServices.getBookStillNotReturn(u.getId()));
    }
    
    private void loadData(int id) throws SQLException{
        tbBook.getItems().clear();
        tbBook.setItems(BookServices.getBookStillNotReturn(id));
    }
}
