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

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<PriceFilter> menuTable;
    @FXML
    private TableColumn<PriceFilter, String> name;
    @FXML
    private TableColumn<PriceFilter, String> description;
    @FXML
    private TableColumn<PriceFilter, Float> price;

    @FXML
    private Button backButton;
    @FXML
    private Button priceButton;
    @FXML
    private Button dishButton;
    @FXML
    private Button createDishButton;

    @FXML
    private TextField priceField;
    @FXML
    private TextField dishField;

    String xname;
    String filterName;
    String xdescription;
    float xprice;
    float filterPrice;

    public String callShowMenu = "{CALL ShowMenu(?)}";
    public String callPRICEFILTER = "{CALL PRICEFILTER(?,?)}";
    public String callDishDetails = "{CALL DishDetails(?,?)}";




    public void searchPrice(ActionEvent event) throws IOException {
        if (priceField.getText().equals("")) {
            resetTable(new ActionEvent());
        } else {

            PriceFilter priceFilter;

            menuTable.getItems().clear(); // clear all the rows from the TableView

            Connection connection = null;

            if (!priceField.getText().equals("")) {
                filterPrice = Float.parseFloat(priceField.getText());

                name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                price.setCellValueFactory(new PropertyValueFactory<>("Cost"));

                try {
                    System.out.println("Trying to connect to database...");
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                    System.out.println("Connected to database");

                    try (CallableStatement stmt = connection.prepareCall(callPRICEFILTER)) {
                        stmt.setFloat(1, filterPrice);
                        stmt.registerOutParameter(2, OracleTypes.CURSOR);
                        stmt.executeQuery();

                        ResultSet rs = (ResultSet) stmt.getObject(2);

                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                            xname = rs.getString(1);

                            System.out.println(rs.getString(2));
                            xdescription = rs.getString(2);

                            System.out.println(rs.getFloat(3));
                            xprice = rs.getFloat(3);
//                    if (table_vip.equals("Y")) {
//                        table_vip = "VIP";
//                    } else {
//                        table_vip = "REGULAR";
//                    }

                            priceFilter = new PriceFilter(xname, xdescription, xprice);
                            menuTable.getItems().add(priceFilter);
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





    public void searchDish(ActionEvent event) throws IOException {
        if (dishField.getText().equals("")) {
            resetTable(new ActionEvent());
        } else {

            PriceFilter priceFilter;

            menuTable.getItems().clear(); // clear all the rows from the TableView

            Connection connection = null;

            if (!dishField.getText().equals("")) {
                filterName = dishField.getText();

                name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                price.setCellValueFactory(new PropertyValueFactory<>("Cost"));

                try {
                    System.out.println("Trying to connect to database...");
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
                    System.out.println("Connected to database");

                    try (CallableStatement stmt = connection.prepareCall(callDishDetails)) {
                        stmt.setString(1, filterName);
                        stmt.registerOutParameter(2, OracleTypes.CURSOR);
                        stmt.executeQuery();

                        ResultSet rs = (ResultSet) stmt.getObject(2);

                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                            xname = rs.getString(1);

                            System.out.println(rs.getString(2));
                            xdescription = rs.getString(2);

                            System.out.println(rs.getFloat(3));
                            xprice = rs.getFloat(3);
//                    if (table_vip.equals("Y")) {
//                        table_vip = "VIP";
//                    } else {
//                        table_vip = "REGULAR";
//                    }

                            priceFilter = new PriceFilter(xname, xdescription, xprice);
                            menuTable.getItems().add(priceFilter);
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





    public void createDish(ActionEvent event) throws IOException {
        changeTheScene(event, "dishCreation.fxml");
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

    public void resetTable(ActionEvent event) {
        PriceFilter priceFilter;

        menuTable.getItems().clear(); // clear all the rows from the TableView

        Connection connection = null;

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        try {
            System.out.println("Trying to connect to database...");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            try (CallableStatement stmt = connection.prepareCall(callShowMenu)) {
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.executeQuery();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    xname = rs.getString(1);

                    System.out.println(rs.getString(2));
                    xdescription = rs.getString(2);

                    System.out.println(rs.getFloat(3));
                    xprice = rs.getFloat(3);
//                    if (table_vip.equals("Y")) {
//                        table_vip = "VIP";
//                    } else {
//                        table_vip = "REGULAR";
//                    }

                    priceFilter = new PriceFilter(xname, xdescription, xprice);
                    menuTable.getItems().add(priceFilter);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!User.getIsStaff()) {
            createDishButton.setDisable(true);
            createDishButton.setVisible(false);
        }



        PriceFilter priceFilter;

        menuTable.getItems().clear(); // clear all the rows from the TableView

        Connection connection = null;

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        try {
            System.out.println("Trying to connect to database...");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.21:1521:dblabs", "it185351", "Oreoskodikos_33");
            System.out.println("Connected to database");

            try (CallableStatement stmt = connection.prepareCall(callShowMenu)) {
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.executeQuery();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    xname = rs.getString(1);

                    System.out.println(rs.getString(2));
                    xdescription = rs.getString(2);

                    System.out.println(rs.getFloat(3));
                    xprice = rs.getFloat(3);
//                    if (table_vip.equals("Y")) {
//                        table_vip = "VIP";
//                    } else {
//                        table_vip = "REGULAR";
//                    }

                    priceFilter = new PriceFilter(xname, xdescription, xprice);
                    menuTable.getItems().add(priceFilter);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
