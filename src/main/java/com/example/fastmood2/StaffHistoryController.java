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

public class StaffHistoryController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<StaffHistory> staffHistoryTable;
    @FXML
    private TableColumn<StaffHistory, String> name;
    @FXML
    private TableColumn<StaffHistory, Integer> tableNumber;
    @FXML
    private TableColumn<StaffHistory, Integer> times;

    @FXML
    private Button filterButton;
    @FXML
    private TextField staffIDText;


    public String callSHOWSTAFFHISTORY = "{CALL SHOWSTAFFHISTORY(?)}";
    public String callStaffHistory = "{CALL StaffHistory(?,?)}";


    String fullname;
    int s_id;
    int tid;
    int count;



    public void showHistory(ActionEvent event) throws IOException {
        if (staffIDText.getText().equals("")) {
            resetTable(new ActionEvent());
        } else {

            StaffHistory staffHistory;

            staffHistoryTable.getItems().clear(); // clear all the rows from the TableView

            if (!staffIDText.getText().equals("")) {
                s_id = Integer.parseInt(staffIDText.getText());

                Connection connection = null;

                name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));
                times.setCellValueFactory(new PropertyValueFactory<>("Count"));

                try {
                    System.out.println("Trying to connect to database...");
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                    System.out.println("Connected to database");

                    try (CallableStatement stmt = connection.prepareCall(callStaffHistory)) {
                        stmt.setInt(1, s_id);
                        stmt.registerOutParameter(2, OracleTypes.CURSOR);
                        stmt.executeQuery();

                        ResultSet rs = (ResultSet) stmt.getObject(2);

                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                            fullname = rs.getString(1);

                            System.out.println(rs.getInt(2));
                            tid = rs.getInt(2);

                            System.out.println(rs.getInt(3));
                            count = rs.getInt(3);

                            staffHistory = new StaffHistory(fullname, tid, count);
                            staffHistoryTable.getItems().add(staffHistory);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetTable(new ActionEvent());

//        StaffHistory staffHistory;
//
//        staffHistoryTable.getItems().clear(); // clear all the rows from the TableView
//
//        Connection connection = null;
//
//        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));
//        times.setCellValueFactory(new PropertyValueFactory<>("Count"));
//
//        try {
//            System.out.println("Trying to connect to database...");
//            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
//            System.out.println("Connected to database");
//
//            try (CallableStatement stmt = connection.prepareCall(callSHOWSTAFFHISTORY)) {
//                stmt.registerOutParameter(1, OracleTypes.CURSOR);
//                stmt.executeQuery();
//
//                ResultSet rs = (ResultSet) stmt.getObject(1);
//
//                while (rs.next()) {
//                    System.out.println(rs.getString(1));
//                    fullname = rs.getString(1);
//
//                    System.out.println(rs.getInt(2));
//                    tid = rs.getInt(2);
//
//                    System.out.println(rs.getInt(3));
//                    count = rs.getInt(3);
//
//                    staffHistory = new StaffHistory(fullname, tid, count);
//                    staffHistoryTable.getItems().add(staffHistory);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void resetTable(ActionEvent event) {
        StaffHistory staffHistory;

        staffHistoryTable.getItems().clear(); // clear all the rows from the TableView

        Connection connection = null;

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        times.setCellValueFactory(new PropertyValueFactory<>("Count"));

        try {
            System.out.println("Trying to connect to database...");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            try (CallableStatement stmt = connection.prepareCall(callSHOWSTAFFHISTORY)) {
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.executeQuery();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    fullname = rs.getString(1);

                    System.out.println(rs.getInt(2));
                    tid = rs.getInt(2);

                    System.out.println(rs.getInt(3));
                    count = rs.getInt(3);

                    staffHistory = new StaffHistory(fullname, tid, count);
                    staffHistoryTable.getItems().add(staffHistory);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
