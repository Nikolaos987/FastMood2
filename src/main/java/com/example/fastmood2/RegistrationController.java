package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationController {

    @FXML
    private TextField fullName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private PasswordField adminKey;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;


    private Stage stage;
    private Scene scene;
    private Parent root;

    int idCounter = 100;

    public void activateAdminKey(ActionEvent event) throws IOException {

    }

    public void registerUser(ActionEvent event) throws IOException {

        int id = idCounter + 1;
        String name = username.getText();
        String phone = phoneNumber.getText();
        String mail = email.getText();
        String user = username.getText();
        String pass = password.getText();


        String sql = "insert into customers values("+ id +",'"+ name +"','"+ phone +"','"+ mail +"','"+ user +"','"+ pass +"')";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185276", " ");
            statement = connection.createStatement();
            boolean result = statement.execute(sql);
            System.out.println("inserted "+ result);
            idCounter += 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void helloScene(ActionEvent event) throws IOException {
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
