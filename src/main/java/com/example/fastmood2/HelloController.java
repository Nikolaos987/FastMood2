package com.example.fastmood2;

import com.sun.tools.javac.Main;
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
import oracle.net.ns.SessionAtts;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField key;

    @FXML
    private Label warning;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public final String adminKey = "admin";
    public boolean found = false;
    public boolean staffFlag = false;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
        if (found) {
            Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void signUpUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void checkLogin() throws IOException {

        try {
            System.out.println("Trying to connect to database...");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            String sql = "select * from customers where username='" + username.getText() +"' and password='" + password.getText() +"'";
            String sqlID = "select CID from Customers c where c.username = 'andreas'";
            String sqlStaff = "select * from staff where username='" + username.getText() +"' and password='" + password.getText() +"'";

            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psID = conn.prepareStatement(sqlID);
            PreparedStatement psStaff = conn.prepareStatement(sqlStaff);

            ResultSet rs = ps.executeQuery();
            ResultSet rsID = psID.executeQuery();
            ResultSet rsStaff = psStaff.executeQuery();

            if (rs.next()) {
                warning.setText("Success!");
                found = true;
                staffFlag = false;  //itan true prin (last modified: 26/11/ 9:10 pm)
                User user1 = new User(staffFlag, rs.getInt("CID"), rs.getString("FULLNAME"), rs.getString("PHONE"), rs.getString("EMAIL"), rs.getString("USERNAME"));
//                (rsID.getInt("CID"));
            }else if (rsStaff.next()){
                warning.setText("Success!");
                found = true;
                staffFlag = true;
                User userStaff = new User(staffFlag, rsStaff.getInt("SID"), rsStaff.getString("FULLNAME"), rsStaff.getString("PHONE"), rsStaff.getString("EMAIL"), rsStaff.getString("USERNAME"));
            }else
                warning.setText("wrong username or password!");
        }catch (Exception e) {
            System.out.println(e);
        }

//        if(username.getText().toString().equals("nikos") && password.getText().toString().equals("123")) {
//            warning.setText("Successful!");
//            flag = true;
//        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
//            warning.setText("Please enter your credentials!");
//        } else warning.setText("Wrong Username or Password!");
    }
}