package com.example.fastmood2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import oracle.jdbc.OracleTypes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;



public class ReservationsController {

    @FXML
    private TableView<PriceFilter> orderTable;
    @FXML
    private TableColumn<PriceFilter, String> name;
    @FXML
    private TableColumn<PriceFilter, Float> cost;
    @FXML
    private TextField costField;


    public String call = "{CALL OrderDetails(?,?,?,?,?,?)}";
    public String call2 = "{CALL PRICEFILTER(?,?)}";

//    JFrame jframe;
//    JTable jtable1 = new JTable();

    float insertCost;
    String xname;
    float xcost;

    ObservableList<PriceFilter> observableList = FXCollections.observableArrayList(
            new PriceFilter("keftedakia", 5.5F)
    );

    public void showOrders(ActionEvent event) throws IOException, SQLException {


//################################################################
//        Connection connection = null;
//        DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
//        model.setRowCount(0);
//        try {
//            System.out.println("Trying to connect to database...");
//            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
//            System.out.println("Connected to database");
//            CallableStatement csl = connection.prepareCall(call2);
//
//            csl.setFloat(1,8);
//            csl.registerOutParameter(2,OracleTypes.CURSOR);
//            csl.executeQuery();
//            ResultSet rsl = (ResultSet) csl.getObject(2);
//
//            while (rsl.next()) {
//                String showname = rsl.getString(1);
//                float price = rsl.getFloat(2);
//                Object[] row = {showname,price};
//                model.addRow(row);
//            }
//
//            jframe = new JFrame();
//            JScrollPane sp = new JScrollPane(jtable1);
//            jframe.add(sp);
//            jframe.add(jtable1);
//            jframe.setVisible(true);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        //#############################################################

        PriceFilter priceFilter;

        orderTable.getItems().clear(); // clear all the table rows

        if (!costField.getText().equals("")) {
            insertCost = Float.parseFloat(costField.getText());


            Connection connection = null;

            name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));

            //orderTable.setItems(observableList);

            try {
                System.out.println("Trying to connect to database...");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                System.out.println("Connected to database");

                try (CallableStatement stmt = connection.prepareCall(call2)) {

//                PriceFilter priceFilter = new PriceFilter("yes", 5.5F);


                    stmt.setFloat(1, insertCost);
                    stmt.registerOutParameter(2, OracleTypes.CURSOR);

                    stmt.executeQuery();

                    ResultSet rs = (ResultSet) stmt.getObject(2);

                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                        xname = rs.getString(1);
                        System.out.println(rs.getFloat(2));
                        xcost = rs.getFloat(2);

                        priceFilter = new PriceFilter(xname, xcost);
                        orderTable.getItems().add(priceFilter);
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

//##########################################################################


//        PriceFilter priceFilter = new PriceFilter("yes", 5.5F);
//        orderTable.getItems().add(priceFilter);


//            PreparedStatement ps = connection.prepareStatement(call);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                y = rs.getInt("t.id");
//                System.out.println(y);
//            }
//
//            try (CallableStatement stmt = connection.prepareCall(call)) {
//
//                stmt.registerOutParameter(1, OracleTypes.CURSOR);
//                stmt.setInt(2, x);
//                stmt.registerOutParameter(3, OracleTypes.INTEGER);
//                stmt.registerOutParameter(4, OracleTypes.VARCHAR);
//                stmt.registerOutParameter(5, OracleTypes.FLOAT);
//                stmt.registerOutParameter(6, OracleTypes.INTEGER);
//                stmt.executeQuery();
//
//                ResultSet rs = (ResultSet) stmt.getObject(1);
//
//                while (rs.next()) {
//                    System.out.println(rs.getInt(1));
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//                System.out.println(stmt.getInt("Leptomeries"));
//                System.out.println(y);


//            changeUser(c_name, c_phone, c_email, c_username, callCustomer, connection);


    }
}