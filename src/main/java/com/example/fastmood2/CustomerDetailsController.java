package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.*;

public class CustomerDetailsController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<CustomerDetails> customerDetailsTable;
    @FXML
    private TableColumn<CustomerDetails, String> customer;
    @FXML
    private TableColumn<CustomerDetails, String> phone;
    @FXML
    private TableColumn<CustomerDetails, String> email;
    @FXML
    private TableColumn<CustomerDetails, String> day;
    @FXML
    private TableColumn<CustomerDetails, Integer> tableNumber;


    @FXML
    private Button searchButton;
    @FXML
    private TextField fullnameField;

    public String callFINDCUSTOMERDETAILSP = "{CALL FINDCUSTOMERDETAILSP(?,?)}";

    String nameInput;

    String c_name;
    String c_phone;
    String c_email;
    String c_day;
    int c_tid;

    public void searchCustomers(ActionEvent event) {
        Connection connection = null;

        if (!fullnameField.getText().equals("")) {
            nameInput = fullnameField.getText();

            CustomerDetails customerDetails;

            customerDetailsTable.getItems().clear(); // clear all the rows from the TableView


            customer.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
            phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            email.setCellValueFactory(new PropertyValueFactory<>("Email"));
            day.setCellValueFactory(new PropertyValueFactory<>("Day"));
            tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(callFINDCUSTOMERDETAILSP)) {
                    stmt.setString(1, nameInput);
                    stmt.registerOutParameter(2, OracleTypes.CURSOR);
                    stmt.executeQuery();

                    ResultSet rs = (ResultSet) stmt.getObject(2);

                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                        c_name = rs.getString(1);

                        System.out.println(rs.getString(2));
                        c_phone = rs.getString(2);

                        System.out.println(rs.getString(3));
                        c_email = rs.getString(3);

                        System.out.println(rs.getString(4));
                        c_day = rs.getString(4);

                        System.out.println(rs.getInt(5));
                        c_tid = rs.getInt(5);

                        customerDetails = new CustomerDetails(c_name, c_phone, c_email, c_day, c_tid);
                        customerDetailsTable.getItems().add(customerDetails);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        changeTheScene(event, "logfileScene.fxml");
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
