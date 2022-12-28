package com.example.software_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Cart implements Initializable {

    @FXML
    private TableColumn<items , String> cartName;

    @FXML
    private TableColumn<items , Double> cartPrice;

    @FXML
    private TableColumn<items , Integer> cartRating;

    @FXML
    private TableView<cartitems> table;
    @FXML
    private Label totalCost;
    public Button back;
    public Button goHome;

    public ObservableList<cartitems> data = null;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
            Statement statement = connection.createStatement();
            ResultSet cart = statement.executeQuery("select * from cart");
            data = FXCollections.observableArrayList();
            while (cart.next()) {
                data.add(new cartitems(cart.getString(2),Double.parseDouble(cart.getString(3)),Integer.parseInt(cart.getString(4))));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        cartName.setCellValueFactory(new PropertyValueFactory<items, String>("itemsName"));
        cartRating.setCellValueFactory(new PropertyValueFactory<items, Integer>("rating"));
        cartPrice.setCellValueFactory(new PropertyValueFactory<items, Double>("price"));
        table.setItems(data);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
            Statement statement = connection.createStatement();
            ResultSet sum = statement.executeQuery("select sum(Cprice) as sum from cart limit 1 ;");
            Double x = null;
            while (sum.next()) {
                x = sum.getDouble("sum");
            }

            totalCost.setText(String.valueOf(x));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void goSelectItems() throws IOException {
        Stage s = (Stage) back.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("view_items.fxml"));
        s.setScene(new Scene(root2));
        s.show();
    }
    public void backHome() throws IOException {
        Stage s = (Stage) goHome.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("enter_iteam.fxml"));
        s.setScene(new Scene(root2));
        s.show();
    }

}
