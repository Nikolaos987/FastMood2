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
import oracle.jdbc.internal.OracleTypes;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MakeReservationsController implements Initializable {
    @FXML
    private TableView<FindReservationByDate> reservationsTable;
    @FXML
    private TableColumn<FindReservationByDate, Integer> reservationID;
    @FXML
    private TableColumn<FindReservationByDate, String> date;
    @FXML
    private TableColumn<FindReservationByDate, Integer> tableID;
    @FXML
    private TableColumn<FindReservationByDate, Integer> customerID;

    @FXML
    private Button backButton;
    @FXML
    private TextField dateField;
    @FXML
    private Button searchButton;
    @FXML
    private Button reservationButton;
    @FXML
    private TextField tableField;
    @FXML
    private TextField rDateField;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public String callFindReservationsByDate = "{CALL FindReservationsByDate(?,?)}";
    public String callMakeReservations = "{CALL MakeReservations(?,?,?)}";
    public String callShowReservations = "{CALL SHOWRESERVATIONS(?)}";

    String searchDate;
    String placeDate;

    int r_id;
    String day;
    int t_id;
    int c_id;

    public void findReservations(ActionEvent event) throws IOException {

        FindReservationByDate findReservationByDate;

        reservationsTable.getItems().clear(); // clear all the table rows

        if (!dateField.getText().equals("")) {
            searchDate = dateField.getText();

            Connection connection = null;

            reservationID.setCellValueFactory(new PropertyValueFactory<>("Rid"));
            date.setCellValueFactory(new PropertyValueFactory<>("Day"));
            tableID.setCellValueFactory(new PropertyValueFactory<>("Tid"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("Cid"));

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(callFindReservationsByDate)) {
                    stmt.setString(1, searchDate);
                    stmt.registerOutParameter(2, OracleTypes.CURSOR);
                    stmt.executeQuery();

                    ResultSet rs = (ResultSet) stmt.getObject(2);

                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                        r_id = rs.getInt(1);

                        System.out.println(rs.getString(2));
                        day = rs.getString(2);

                        System.out.println(rs.getInt(3));
                        t_id = rs.getInt(3);

                        System.out.println(rs.getInt(4));
                        c_id = rs.getInt(4);

                        findReservationByDate = new FindReservationByDate(day, t_id);
                        reservationsTable.getItems().add(findReservationByDate);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void placeRecervation(ActionEvent event) throws IOException {
        Connection connection = null;

        if (!tableField.getText().equals("") && !rDateField.getText().equals("")) {
            t_id = Integer.parseInt(tableField.getText());
            placeDate = rDateField.getText();

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(callMakeReservations)) {
                    stmt.setDate(1, Date.valueOf(placeDate));
                    stmt.setInt(2, t_id);
                    stmt.setInt(3, User.getID());
                    stmt.executeQuery();

                    System.out.println("\nReservation created on " + placeDate + ", for table " + t_id);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FindReservationByDate findReservationByDate;

        reservationsTable.getItems().clear(); // clear all the table rows

            Connection connection = null;

//            reservationID.setCellValueFactory(new PropertyValueFactory<>("Rid"));
            date.setCellValueFactory(new PropertyValueFactory<>("Day"));
            tableID.setCellValueFactory(new PropertyValueFactory<>("Tid"));
//            customerID.setCellValueFactory(new PropertyValueFactory<>("Cid"));

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(callShowReservations)) {
                    stmt.registerOutParameter(1, OracleTypes.CURSOR);
                    stmt.executeQuery();

                    ResultSet rs = (ResultSet) stmt.getObject(1);

                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                        day = rs.getString(1);

                        System.out.println(rs.getInt(2));
                        t_id = rs.getInt(2);

                        findReservationByDate = new FindReservationByDate(day, t_id);
                        reservationsTable.getItems().add(findReservationByDate);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }


    public void goBack(ActionEvent event) throws IOException {
        changeTheScene(event, "reservationsScene.fxml");
    }

    public void changeTheScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
