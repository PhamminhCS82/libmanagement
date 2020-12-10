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
public class UserMenuController {
    public void switchSceneSearchHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchbook.fxml"));
        Parent searchBookParent = loader.load();
        Scene scene = new Scene(searchBookParent);
        stage.setScene(scene);
    }
    public void switchSceneLoginHandler(ActionEvent evt) throws IOException{
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent searchBookParent = loader.load();
        Scene scene = new Scene(searchBookParent);
        stage.setScene(scene);
    }
}
