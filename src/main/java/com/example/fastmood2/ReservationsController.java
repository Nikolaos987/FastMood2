package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class ReservationsController implements Initializable {

    @FXML
    private TableView<AvailableTables> tables;
    @FXML
    private TableColumn<AvailableTables, Integer> tableNumber;
    @FXML
    private TableColumn<AvailableTables, String> tableClass;
    @FXML
    private TableColumn<AvailableTables, Integer> capacity;
//    @FXML
//    private TextField costField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public String call = "{CALL AvailableTables(?)}";

    int table_id;
    String table_vip;
    int table_capacity;

//    float insertCost;
//    String xname;
//    float xcost;

    public void showOrders(ActionEvent event) throws IOException, SQLException {



//
//        PriceFilter priceFilter;
//
//        orderTable.getItems().clear(); // clear all the table rows
//
//        if (!costField.getText().equals("")) {
//            insertCost = Float.parseFloat(costField.getText());
//
//            Connection connection = null;
//
//            name.setCellValueFactory(new PropertyValueFactory<>("Name"));
//            cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
//
//            try {
//                System.out.println("Trying to connect to database...");
//                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
//                System.out.println("Connected to database");
//
//                try (CallableStatement stmt = connection.prepareCall(call2)) {
//                    stmt.setFloat(1, insertCost);
//                    stmt.registerOutParameter(2, OracleTypes.CURSOR);
//                    stmt.executeQuery();
//
//                    ResultSet rs = (ResultSet) stmt.getObject(2);
//
//                    while (rs.next()) {
//                        System.out.println(rs.getString(1));
//                        xname = rs.getString(1);
//                        System.out.println(rs.getFloat(2));
//                        xcost = rs.getFloat(2);
//
//                        priceFilter = new PriceFilter(xname, xcost);
//                        orderTable.getItems().add(priceFilter);
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AvailableTables availableTables;

        tables.getItems().clear(); // clear all the rows from the TableView

        Connection connection = null;

        tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        tableClass.setCellValueFactory(new PropertyValueFactory<>("Vip"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(call)) {
                    stmt.registerOutParameter(1, OracleTypes.CURSOR);
                    stmt.executeQuery();

                    ResultSet rs = (ResultSet) stmt.getObject(1);

                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                        table_id = rs.getInt(1);

                        System.out.println(rs.getString(2));
                        table_vip = rs.getString(2);
                        if (table_vip.equals("Y")) {
                            table_vip = "VIP";
                        } else {
                            table_vip = "REGULAR";
                        }

                        System.out.println(rs.getFloat(3));
                        table_capacity = rs.getInt(3);

                        availableTables = new AvailableTables(table_id, table_vip, table_capacity);
                        tables.getItems().add(availableTables);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public void makeReservationsScene(ActionEvent event) throws IOException {
        changeTheScene(event, "makeReservationsScene.fxml");
    }

    public void goBack(ActionEvent event) throws IOException {
        changeTheScene(event, "mainScene.fxml");
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}