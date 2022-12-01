package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class MainController {
    @FXML
    private Button reservationsButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button accountButton;
    @FXML
    private Button aboutusButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button logfileButton;
//    @FXML
//    private TableView<LogData> table1;
//    @FXML
//    private TableColumn<LogData> colStaffID;
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

    public void reservationsScene(ActionEvent event) throws IOException {
        changeTheScene(event, "reservationsScene.fxml");
    }
    public void menuScene(ActionEvent event) throws IOException {
        changeTheScene(event, "menuScene.fxml");
    }
    public void accountScene(ActionEvent event) throws IOException {
        changeTheScene(event, "accountScene.fxml");
    }
    public void aboutusScene(ActionEvent event) throws IOException {
        changeTheScene(event, "aboutusScene.fxml");
    }
    public void adminScene(ActionEvent event) throws IOException {
        changeTheScene(event, "adminScene.fxml");
    }

    public void logout(ActionEvent event) throws IOException {
        changeTheScene(event, "hello-view.fxml");
    }

//    public void logfile(ActionEvent event) throws IOException {
//        changeTheScene(event, "logfileScene.fxml");
//    }

    public void logfileButtonPressed(ActionEvent event) throws IOException {
//        if (User.getIsStaff()) {
//            LogData lc;
//            try {
//                //Class.forName("Oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
//                Statement stmt = connection.createStatement();
//                ResultSet rs = stmt.executeQuery("select * from LOGFILE");
//                while (rs.next()) {
//                    String c_id = String.valueOf(rs.getInt("CUSTOMERID"));
//                    String fullname = rs.getString("FULLNAME");
//                    String tablename = rs.getString("tablename");
//                    String action = rs.getString("action");
//                    String moment = rs.getString("moment");
//                    String staffid = String.valueOf(rs.getInt("staffid"));
//                    System.out.println(c_id + "\t\t" + staffid +"\t\t" + fullname + "\t\t\t\t" + tablename + "\t\t\t\t" + action + "\t\t\t\t" + moment);
//
//                    lc = new LogData(staffid, c_id, fullname, action, moment, tablename);
//                    //String tbData[] = {c_id, fullname, tablename, action, moment, staffid};
//                    //DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
//                    //Arrays.stream(tbData).toArray();
//                }
        changeTheScene(event, "logfileScene.fxml");
//                connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }/* catch (IOException e) {
//                throw new RuntimeException(e);
//            }*/
//        }
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
