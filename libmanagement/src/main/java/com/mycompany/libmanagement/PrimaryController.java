package com.mycompany.libmanagement;

import com.pqm.pojo.Book;
import com.pqm.services.BookServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    TextField txtName;
    @FXML
    TableView<Book> tbBooks;
    @FXML
    TextField txtAuthors;
    @FXML
    TextArea txtDescribe;
    @FXML
    TextField txtCategory;
    @FXML
    TextField txtPublisher;
    @FXML
    TextField txtPublishYear;
    @FXML
    TextField txtLocation;
    @FXML
    TextField txtKeyword;
    @FXML
    ComboBox cbKeyword;
    @FXML
    Label lbDate;

    private void loadBooks() {
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

        TableColumn clAction = new TableColumn();
        clAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Xóa");
            btn.setOnAction(evt -> {

                Button bt = (Button) evt.getSource();
                TableCell c = (TableCell) bt.getParent();
                Book b = (Book) c.getTableRow().getItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        try {
                            if (BookServices.deleteBook(b.getId())) {
                                this.loadData("", -1);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            });

            cell.setGraphic(btn);
            return cell;
        });

        tbBooks.getColumns().addAll(clId, clName, clPublisher,
                clAuthor, clCategory, clYear, clAction);
    }

    private void loadData(String kw, int indexCat) throws SQLException {
        tbBooks.getItems().clear();
        tbBooks.setItems(BookServices.getBooks(kw, indexCat));
    }

    public void addBooksHandler(ActionEvent evt) {
        Book b = tbBooks.getSelectionModel().getSelectedItem();
        String nameBook = txtName.getText().trim();
        String authors = txtAuthors.getText().trim();
        String publisher = txtPublisher.getText().trim();
        String category = txtCategory.getText().trim();
        String yearPublish = txtPublishYear.getText().trim();
        String location = txtLocation.getText().trim();
        String describe = txtDescribe.getText().trim();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (nameBook.isEmpty() || authors.isEmpty() || publisher.isEmpty() || category.isEmpty()) {
            alert.setContentText("Không thể để trống tên sách, tác giả, nhà xuất bản, thể loại");
        } else {
            Book q = new Book(nameBook.replaceAll("\\s+", " "), authors.replaceAll("\\s+", " "), describe.replaceAll("\\s+", " "),
                    publisher.replaceAll("\\s+", " "), category.replaceAll("\\s+", " "), location.replaceAll("\\s+", " "), yearPublish.replaceAll("\\s+", " "));
            if (BookServices.addBook(q)) {
                alert.setContentText("SUCCESSFUL");
                try {
                    loadData("", -1);
                } catch (SQLException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                alert.setContentText("FAILED");
            }

            
        }
        alert.show();
    }

    public void upadteBooksHandler(ActionEvent evt) throws SQLException {
        if (!tbBooks.getSelectionModel().getSelectedCells().isEmpty()) {
            Book b = tbBooks.getSelectionModel().getSelectedItem();
            String nameBook = txtName.getText().trim();
            String authors = txtAuthors.getText().trim();
            String publisher = txtPublisher.getText().trim();
            String category = txtCategory.getText().trim();
            String yearPublish = txtPublishYear.getText().trim();
            String location = txtLocation.getText().trim();
            String describe = txtDescribe.getText().trim();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (nameBook.isEmpty() || authors.isEmpty() || publisher.isEmpty() || category.isEmpty()) {
                alert.setContentText("Không thể để trống tên sách, tác giả, nhà xuất bản, thể loại");
            } else {
                b.setCategory(category.replaceAll("\\s+", " "));
                b.setAuthor(authors.replaceAll("\\s+", " "));
                b.setName(nameBook.replaceAll("\\s+", " "));
                b.setPublisher(publisher.replaceAll("\\s+", " "));
                b.setDescribe(describe.replaceAll("\\s+", " "));
                b.setYear(yearPublish.replaceAll("\\s+", " "));
                b.setLocation(location.replaceAll("\\s+", " "));
                if (BookServices.updateBook(b)) {
                    alert.setContentText("Thành công");
                } else {
                    alert.setContentText("Thất bại");
                }
                loadData("", -1);
            }
            alert.show();
        }
    }

    public void clearTextHandler(ActionEvent evt) {
        txtAuthors.clear();
        txtCategory.clear();
        txtDescribe.clear();
        txtLocation.clear();
        txtName.clear();
        txtPublishYear.clear();
        txtPublisher.clear();
        lbDate.setText("");
    }

    public void backSceneHandler(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("managerlibmenu.fxml"));
        Parent backParent = loader.load();
        Scene scene = new Scene(backParent);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] list = {"Tên sách", "Tác giả", "Nhà xuất bản", "Thể loại", "Năm xuất bản"};
        cbKeyword.getItems().addAll(Arrays.asList(list));
        txtDescribe.setWrapText(true);
        try {
            loadBooks();
            loadData("", -1);
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtKeyword.textProperty().addListener(et -> {
            try {
                loadData(txtKeyword.getText(),
                        cbKeyword.getSelectionModel().getSelectedIndex());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tbBooks.setRowFactory(evt -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(et -> {
                Book b = tbBooks.getSelectionModel().getSelectedItem();
                txtName.setText(b.getName());
                txtAuthors.setText(b.getAuthor());
                txtCategory.setText(b.getCategory());
                txtPublisher.setText(b.getPublisher());
                if (b.getYear() != null) {
                    txtPublishYear.setText(b.getYear());
                } else {
                    txtPublishYear.clear();
                }
                txtLocation.setText(b.getLocation());
                txtDescribe.setText(b.getDescribe());
                if (b.getDayAdded() != null) {
                    lbDate.setText(b.getDayAdded().toString());
                } else {
                    lbDate.setText("");
                }
            });
            return row;
        });
        tbBooks.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE) {
                int index = tbBooks.selectionModelProperty().getValue().getSelectedItem().getId();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa? Nó sẽ xóa các lựa chọn liên quan!");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        try {
                            if (BookServices.deleteBook(index)) {
                                this.loadData("", -1);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        txtPublishYear.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPublishYear.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
