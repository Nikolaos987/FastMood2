package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DishCreationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private Button createButton;
    @FXML
    private Button backButton;
    @FXML
    private Label createLabel;


    public String callINSERTDISH = "{call INSERTDISH(?,?,?)}";

    String dish_name;
    String dish_description;
    float dish_price;

    public void createDish(ActionEvent event) throws IOException {
        Connection connection = null;

        if (!nameField.getText().equals("") && !descriptionField.getText().equals("") && !priceField.getText().equals("")) {

            dish_name = nameField.getText();
            dish_description = descriptionField.getText();
            dish_price = Float.parseFloat(priceField.getText());

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(callINSERTDISH)) {
                    stmt.setString(1, dish_name);
                    stmt.setString(2, dish_description);
                    stmt.setFloat(3, dish_price);
                    stmt.execute();
                    System.out.println("Dish inserted!");
                    createLabel.setTextFill(Color.rgb(3, 125, 80));
                    createLabel.setText("Dish inserted!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void menuScene(ActionEvent event) throws IOException {
        changeTheScene(event, "menuScene.fxml");
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
