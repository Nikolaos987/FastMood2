package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    @FXML
    private Button logfileButton;
//    @FXML
//    private TableView<LogData> table1;
//    @FXML
//    private TableColumn<LogData> colStaffID;
//    @FXML
//    private TableColumn colCustomerID;
//    @FXML
//    private TableColumn colFullName;
//    @FXML
//    private TableColumn colAction;
//    @FXML
//    private TableColumn colMoment;
//    @FXML
//    private TableColumn colTableName;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void reservationsScene(ActionEvent event) throws IOException {
        if (!User.getIsStaff()) {
            changeTheScene(event, "makeReservationsScene.fxml");
        } else {
            changeTheScene(event, "reservationsScene.fxml");
        }
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
        changeTheScene(event, "logfileScene.fxml");
    }

    public void logout(ActionEvent event) throws IOException {
        changeTheScene(event, "hello-view.fxml");
    }

//    public void logfile(ActionEvent event) throws IOException {
//        changeTheScene(event, "logfileScene.fxml");
//    }



    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!User.getIsStaff()) {
            adminButton.setDisable(true);
            adminButton.setVisible(false);
        }
    }
}
