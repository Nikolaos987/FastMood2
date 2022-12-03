package com.example.fastmood2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.logging.annotations.Log;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogfileController implements Initializable {
//    @FXML
//    private TableView<LogData> table1;
//    @FXML
//    private TableColumn colStaffID;
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

    @FXML
    private TableView<LogData> table1;
    @FXML
    private TableColumn<LogData,String> colStaffID;
    @FXML
    private TableColumn<LogData,String> colCustomerID;
    @FXML
    private TableColumn<LogData,String> colFullName;
    @FXML
    private TableColumn<LogData,String> colAction;
    @FXML
    private TableColumn<LogData,String> colMoment;
    @FXML
    private TableColumn<LogData,String> colTableName;


    @FXML
    private Button backButton;



    public String c_id;
    public String u_name;
    public String tablename;
    public String action;
    public String moment;
    public String s_id;
    public String id;

    public String call = "{CALL ShowLogFile(?)}";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogData logData;
        Connection connection = null;

        colStaffID.setCellValueFactory(new PropertyValueFactory<>("Staff_id"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
        colMoment.setCellValueFactory(new PropertyValueFactory<>("Moment"));
        colTableName.setCellValueFactory(new PropertyValueFactory<>("Table_name"));

        try {
            System.out.println("Trying to connect to database...");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            try (CallableStatement stmt = connection.prepareCall(call)) {
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.executeQuery();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    s_id = rs.getString(1);
                    System.out.println(rs.getFloat(2));
                    c_id = rs.getString(2);
                    System.out.println(rs.getString(3));
                    u_name = rs.getString(3);
                    System.out.println(rs.getString(4));
                    action = rs.getString(4);
                    System.out.println(rs.getString(5));
                    moment = rs.getString(5);
                    System.out.println(rs.getString(6));
                    tablename = rs.getString(6);

                    logData = new LogData(s_id, c_id, u_name, action, moment, tablename);
                    table1.getItems().add(logData);
                    //orderTable.getColumns().add(name);
                    //orderTable.getItems().add(name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void orderDetails(ActionEvent event) throws IOException {
        changeTheScene(event, "orderDetailsScene.fxml");
    }

    public void staffHistory(ActionEvent event) throws IOException {
        changeTheScene(event, "staffHistoryScene.fxml");
    }

    public void mainScene(ActionEvent event) throws IOException {
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
