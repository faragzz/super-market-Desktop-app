package com.example.software_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class main_screen {
    @FXML
    private Label text;
    @FXML
    private TextField code;

    @FXML
    private TextField name;
    @FXML

    private TextField price;
    @FXML
    private TextField quantity;

    public Button next;

    public void addInItemDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root","");
        String Getname = name.getText();
        int GetCode = Integer.parseInt(code.getText());
        int Getquantity = Integer.parseInt(quantity.getText());
        double Getprice = Double.parseDouble(price.getText());
        PreparedStatement posted = connection.prepareStatement("INSERT INTO items (itemName,itemCode,itemQuantity,itemPrice) VALUES ('" +Getname+"','"+GetCode+"','"+Getquantity+"','"+Getprice+"')");
        posted.executeUpdate();
    }
    public void nextPage() throws IOException {
        Stage s = (Stage) next.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("view_items.fxml"));
        s.setScene(new Scene(root2));
        s.show();
    }

}
