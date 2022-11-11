package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button reservationsButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button accountButton;
    @FXML
    private Button aboutusButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button logoutButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void reservationsScene(ActionEvent event) throws IOException {
        changeTheScene(event, "reservationsScene.fxml");
    }
    public void menuScene(ActionEvent event) throws IOException {
        changeTheScene(event, "menuScene.fxml");
    }
    public void accountScene(ActionEvent event) throws IOException {
        changeTheScene(event, "accountScene.fxml");
    }
    public void aboutusScene(ActionEvent event) throws IOException {
        changeTheScene(event, "aboutusScene.fxml");
    }
    public void adminScene(ActionEvent event) throws IOException {
        changeTheScene(event, "adminScene.fxml");
    }

    public void logout(ActionEvent event) throws IOException {
        changeTheScene(event, "hello-view.fxml");
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
