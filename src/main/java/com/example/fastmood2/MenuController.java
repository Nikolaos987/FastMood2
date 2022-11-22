package com.example.fastmood2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import javax.swing.text.html.ListView;
import java.io.IOException;
import java.sql.*;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView list;

    @FXML
    private TableView table;

    @FXML
    private TextField text;

    @FXML
    private Button menuButton;

    @FXML
    private Button backButton;
    public String name;
    public void showMenu(ActionEvent event) throws IOException {
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185276", "Soul2310");
//            // CallableStatement cs = conn.prepareCall("{call show_menu()}");
//            Statement stmt = conn.createStatement();
//            String sql = "SELECT FULLNAME FROM CUSTOMERS WHERE CID = 1";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            name = rs.getString("FULLNAME");
//            System.out.println(rs);
//        }catch (Exception e) {
//            System.out.println(e);
//        }
//        text.setText(name);

        try {
            System.out.println("Trying to connect to database...");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            String sql = "SELECT FULLNAME FROM CUSTOMERS WHERE CID = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //text.setText();
                String name = rs.getString("FULLNAME");
                System.out.println(name);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
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
