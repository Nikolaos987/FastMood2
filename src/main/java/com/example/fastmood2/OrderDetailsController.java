package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OrderDetailsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<OrderDetails> ordersTable;
    @FXML
    private TableColumn<OrderDetails, Integer> tableNumber;
    @FXML
    private TableColumn<OrderDetails, String> dishName;
    @FXML
    private TableColumn<OrderDetails, Float> dishPrice;
    @FXML
    private TableColumn<OrderDetails, String> servedBy;


    @FXML
    private Button backButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField o_idField;

    int tid;
    int tidField;
    String dname;
    float dprice;
    String osid;


    public String callSHOWORDERS = "{CALL SHOWORDERS(?)}";
    public String callOrderDetails = "{CALL OrderDetails(?,?)}";




    public void logfileScene(ActionEvent event) throws IOException {
        changeTheScene(event, "logfileScene.fxml");
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void resetTable(ActionEvent event) throws IOException {
        OrderDetails orderDetails;

        ordersTable.getItems().clear(); // clear all the rows from the TableView

        Connection connection = null;

        tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        dishName.setCellValueFactory(new PropertyValueFactory<>("Dname"));
        dishPrice.setCellValueFactory(new PropertyValueFactory<>("Dprice"));
        servedBy.setCellValueFactory(new PropertyValueFactory<>("Osid"));

        try {
            System.out.println("Trying to connect to database...");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            try (CallableStatement stmt = connection.prepareCall(callSHOWORDERS)) {
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.executeQuery();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    tid = rs.getInt(1);

                    System.out.println(rs.getString(2));
                    dname = rs.getString(2);

                    System.out.println(rs.getFloat(3));
                    dprice = rs.getFloat(3);

                    System.out.println(rs.getString(4));
                    osid = rs.getString(4);

                    orderDetails = new OrderDetails(tid, dname, dprice, osid);
                    ordersTable.getItems().add(orderDetails);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void filterOrders(ActionEvent event) throws IOException {
        if (o_idField.getText().equals("")) {
            resetTable(new ActionEvent());
        } else {

            Connection connection = null;

            if (!o_idField.getText().equals("")) {
                tidField = Integer.parseInt(o_idField.getText());

                OrderDetails orderDetails;

                ordersTable.getItems().clear(); // clear all the rows from the TableView


                tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));
                dishName.setCellValueFactory(new PropertyValueFactory<>("Dname"));
                dishPrice.setCellValueFactory(new PropertyValueFactory<>("Dprice"));
                servedBy.setCellValueFactory(new PropertyValueFactory<>("Osid"));

                try {
                    System.out.println("Trying to connect to database...");
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                    System.out.println("Connected to database");

                    try (CallableStatement stmt = connection.prepareCall(callOrderDetails)) {
                        stmt.setInt(1, tidField);
                        stmt.registerOutParameter(2, OracleTypes.CURSOR);
                        stmt.executeQuery();

                        ResultSet rs = (ResultSet) stmt.getObject(2);

                        while (rs.next()) {
                            System.out.println(rs.getInt(1));
                            tid = rs.getInt(1);

                            System.out.println(rs.getString(2));
                            dname = rs.getString(2);

                            System.out.println(rs.getFloat(3));
                            dprice = rs.getFloat(3);

                            System.out.println(rs.getString(4));
                            osid = rs.getString(4);

                            orderDetails = new OrderDetails(tid, dname, dprice, osid);
                            ordersTable.getItems().add(orderDetails);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            resetTable(new ActionEvent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
