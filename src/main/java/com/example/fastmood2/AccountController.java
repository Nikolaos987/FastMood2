package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
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

    //##########################

    private Stage stage;
    private Scene scene;
    private Parent root;


    String nullField;
    String sameField;
    boolean sameFlag = false;


    public String c_name;
    public String c_phone;
    public String c_email;
    public String c_username;

    public String callCustomer = "{CALL EDITUSER(?,?,?,?,?)}";
    public String callStaff = "{CALL EDITSTAFF(?,?,?,?,?)}";

    public void changeUser(String xName, String xPhone, String xEmail, String xUsername, String callMethod, Connection conn) throws IOException {
        try (CallableStatement stmt = conn.prepareCall(callMethod)) {
            stmt.setInt(1, User.getID());
            stmt.setString(2, xName);
            stmt.setString(3, xPhone);
            stmt.setString(4, xEmail);
            stmt.setString(5, xUsername);
            stmt.execute();
            System.out.println("\nFULLNAME changed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SaveFullnameButton_Pressed(ActionEvent event) throws IOException {
        fullnameLabel.setTextFill(Color.rgb(255, 49, 49));
        sameFlag = false;
        fullnameLabel.setText("");

        if (fullname.getText().equals(User.getFullname())) {
            sameFlag = true;
            sameField = "fullname can't be the same with current one!";
        }

        if (!fullname.getText().equals("") && !fullname.getText().equals(User.getFullname())) {
            c_name = fullname.getText();
            c_phone = User.getPhone();
            c_email = User.getEmail();
            c_username = User.getUsername();



            Connection connection = null;
            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                if (!User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callCustomer, connection);
//                try (CallableStatement stmt = connection.prepareCall(callCustomer)) {
//                    stmt.setString(1, fullname.getText());
//                    stmt.setInt(2, User.getID());
//                    stmt.execute();
//                    System.out.println("\nCUSTOMER: fullname changed!");
//                }
                } else if (User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callStaff, connection);
//                    try (CallableStatement stmt = connection.prepareCall(callStaff)) {
//                        stmt.setString(1, fullname.getText());
//                        stmt.setInt(2, User.getID());
//                        stmt.execute();
//                        System.out.println("\nCUSTOMER: fullname changed!");
//                    }
                }
                User.setFullname(c_name);
                fullnameLabel.setTextFill(Color.rgb(3, 125, 80));
                fullnameLabel.setText("fullname changed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (sameFlag) {
                fullnameLabel.setText(sameField);
            } else
                fullnameLabel.setText("Field can't be null!");
        }
    }


    public void savePhoneButton_Pressed(ActionEvent event) {
        phoneLabel.setTextFill(Color.rgb(255, 49, 49));
        sameFlag = false;
        phoneLabel.setText("");

        if (phone.getText().equals(User.getPhone())) {
            sameFlag = true;
            sameField = "phone number can't be the same with current one!";
        }

        if (!phone.getText().equals("") && !phone.getText().equals(User.getPhone())) {
            c_name = User.getFullname();
            c_phone = phone.getText();
            c_email = User.getEmail();
            c_username = User.getUsername();

            Connection connection = null;
            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                if (!User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callCustomer, connection);
                } else if (User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callStaff, connection);
                }
                User.setPhone(c_phone);
                phoneLabel.setTextFill(Color.rgb(3, 125, 80));
                phoneLabel.setText("phone changed!");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (sameFlag) {
                phoneLabel.setText(sameField);
            } else
                phoneLabel.setText("Field can't be null!");
        }
    }

    public void saveEmailButton_Pressed(ActionEvent event) {
        emailLabel.setTextFill(Color.rgb(255, 49, 49));
        sameFlag = false;
        emailLabel.setText("");

        if (email.getText().equals(User.getEmail())) {
            sameFlag = true;
            sameField = "email can't be the same with current one!";
        }

        if (!email.getText().equals("") && !email.getText().equals(User.getEmail())) {
            c_name = User.getFullname();
            c_phone = User.getPhone();
            c_email = email.getText();
            c_username = User.getUsername();

            Connection connection = null;
            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                if (!User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callCustomer, connection);
                } else if (User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callStaff, connection);
                }
                User.setEmail(c_email);
                emailLabel.setTextFill(Color.rgb(3, 125, 80));
                emailLabel.setText("email changed!");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (sameFlag) {
                emailLabel.setText(sameField);
            } else
                emailLabel.setText("Field can't be null!");
        }
    }

    public void saveUsernameButton_Pressed(ActionEvent event) {
        usernameLabel.setTextFill(Color.rgb(255, 49, 49));
        sameFlag = false;
        usernameLabel.setText("");

        if (username.getText().equals(User.getUsername())) {
            sameFlag = true;
            sameField = "username can't be the same with current one!";
        }

        if (!username.getText().equals("") && !username.getText().equals(User.getUsername())) {
            c_name = User.getFullname();
            c_phone = User.getPhone();
            c_email = User.getEmail();
            c_username = username.getText();

            Connection connection = null;
            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                if (!User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callCustomer, connection);
                } else if (User.getIsStaff()) {
                    changeUser(c_name, c_phone, c_email, c_username, callStaff, connection);
                }
                User.setUsername(c_username);
                usernameLabel.setTextFill(Color.rgb(3, 125, 80));
                usernameLabel.setText("username changed!");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (sameFlag) {
                usernameLabel.setText(sameField);
            } else
                usernameLabel.setText("Field can't be null!");
        }
    }


    public void okayButtonPressed(ActionEvent event) throws IOException {
        System.out.println(User.getFullname());
        changeTheScene(event, "mainScene.fxml");
    }

    public void deleteAccountButtonPressed(ActionEvent event) throws IOException {
        try {
            System.out.println("Trying to connect to database...");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");


            if (!User.getIsStaff()) {
                String sqlDelete = "delete from Customers where CID=" + User.getID();
                PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
                ResultSet rsDelete = psDelete.executeQuery();

            } else if (User.getIsStaff()) {
                String sqlDelete = "delete from Staff where SID=" + User.getID();
                PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
                ResultSet rsDelete = psDelete.executeQuery();
            }

        }catch (Exception e) {
            System.out.println(e);
        }
        changeTheScene(event, "hello-view.fxml");

    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullname.setText(User.getFullname());
        phone.setText(User.getPhone());
        email.setText(User.getEmail());
        username.setText(User.getUsername());
    }
}
