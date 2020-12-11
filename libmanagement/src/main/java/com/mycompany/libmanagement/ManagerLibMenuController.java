/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.libmanagement;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class ManagerLibMenuController {
    public void switchSceneBookListHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("primary.fxml"));
        Parent bookListParent = loader.load();
        Scene scene = new Scene(bookListParent);
        stage.setScene(scene);
    }
    public void switchSceneUserListHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userlist.fxml"));
        Parent userListParent = loader.load();
        Scene scene = new Scene(userListParent);
        stage.setScene(scene);
    }
     public void switchSceneBorrowListHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("finetable.fxml"));
        Parent borrowListParent = loader.load();
        Scene scene = new Scene(borrowListParent);
        stage.setScene(scene);
    }
    public void logoutSceneHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("usermenu.fxml"));
        Parent backParent = loader.load();
        Scene scene = new Scene(backParent);
        stage.setScene(scene);
    }
}
