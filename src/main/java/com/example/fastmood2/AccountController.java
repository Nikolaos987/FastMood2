package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AccountController {
    @FXML
    private TextField fullname;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private Button saveFullnameButton;

    @FXML
    private Button savePhoneButton;

    @FXML
    private Button saveEmailButton;

    @FXML
    private Button saveUsernameButton;

    @FXML
    private Button okButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Label fullnameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label usernameLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;




    public void SaveFullnameButton_Pressed() throws IOException {
        String call = "{CALL EDITUSER(?,?)}";
        Connection connection = null;
        try {
            System.out.println("Trying to connect to database...");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");


            try (CallableStatement stmt = connection.prepareCall(call)) {
                System.out.println("old Fullname: "+ User.getFullname());
                System.out.println("CID: "+ User.getID());
                System.out.println("new Fullname: "+ fullname.getText());

                stmt.setString(1, fullname.getText());
                stmt.setInt(2, User.getID());
                stmt.execute();
                System.out.println("\nCUSTOMER: fullname changed!");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePhoneButton_Pressed(ActionEvent event) {
        String x = phone.getText();
        phoneLabel.setText(Integer.toString(User.getID()));
    }

    public void saveEmailButton_Pressed(ActionEvent event) {
        String x = email.getText();
        emailLabel.setText(x);
    }

    public void saveUsernameButton_Pressed(ActionEvent event) {
        String x = username.getText();
        usernameLabel.setText(x);
    }


    public void okayButtonPressed(ActionEvent event) throws IOException {
        changeTheScene(event, "mainScene.fxml");
    }

    public void deleteAccountButtonPressed(ActionEvent event) throws IOException {
        try {

            System.out.println("Trying to connect to database...");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            String sqlDelete = "delete from Customers where CID="+ User.getID();
            PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
            ResultSet rsDelete = psDelete.executeQuery();

        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
