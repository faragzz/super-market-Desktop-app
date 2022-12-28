package com.example.software_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<items, String> iteam_name;

    @FXML
    private TableColumn<items, String> item_code;

    @FXML
    private TableColumn<items, Integer> quantity;
    @FXML
    private TableColumn<items, Double> price;

    @FXML

    private TableColumn<items, Integer> Rating;

    @FXML
    TextField Trating;
    @FXML
    public Button add,back,load;
    @FXML
    public TextField search;
    @FXML
    public Button toCartBtn;
    @FXML
    private TableView<items> table;

    public ObservableList<items> data = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from items");

            data = FXCollections.observableArrayList();
            int getrating = 0;
            while (rs.next()) {
                int addrating = 0;
                if (rs.getString(5) != null) {
                    addrating = Integer.parseInt(rs.getString(5));
                } else {
                    addrating = 0;
                }
                data.add(new items(rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)), addrating,Double.parseDouble(rs.getString(6))));
            }
        }
     catch(Exception e){
            System.out.println(e);
        }
        iteam_name.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
        item_code.setCellValueFactory(new PropertyValueFactory<items, String>("code"));
        quantity.setCellValueFactory(new PropertyValueFactory<items, Integer>("quantity"));
        Rating.setCellValueFactory(new PropertyValueFactory<items, Integer>("rating"));
        price.setCellValueFactory(new PropertyValueFactory<items, Double>("price"));

        table.setItems(data);
    }
    public void updaterating() throws SQLException {
        int getRating  = Integer.parseInt(Trating.getText());
        data = table.getSelectionModel().getSelectedItems();
        String code = data.get(0).getCode();

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
        PreparedStatement posted = connection.prepareStatement("UPDATE items SET itemRating = '"+getRating+"' WHERE itemCode like '"+code+"';");
        posted.executeUpdate();
        load();

    }
    public void load() {
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from items");

        data = FXCollections.observableArrayList();
        int addrating = 0;
        while (rs.next()) {
            if(rs.getString(5) != null){
                addrating =Integer.parseInt(rs.getString(5));
            }
            else {
                addrating = 0;
            }
            data.add(new items(rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),addrating,Double.parseDouble(rs.getString(6))));
        }
    }
     catch(Exception e){
        System.out.println(e);
    }
        iteam_name.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
        item_code.setCellValueFactory(new PropertyValueFactory<items, String>("code"));
        quantity.setCellValueFactory(new PropertyValueFactory<items, Integer>("quantity"));
        Rating.setCellValueFactory(new PropertyValueFactory<items, Integer>("rating"));
        price.setCellValueFactory(new PropertyValueFactory<items, Double>("price"));

        table.setItems(data);
    }
    public void search(){
        try{
            String value = search.getText().trim();
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from items where itemCode like '"+value+"' or itemRating like '"+value+"' or itemName like '"+value+"' ;");
            data = FXCollections.observableArrayList();
            int addrating = 0;

            while (rs.next()) {
                if(rs.getString(5) != null){
                    addrating =Integer.parseInt(rs.getString(5));
                }
                else {
                    addrating = 0;
                }
                data.add(new items(rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),addrating,Double.parseDouble(rs.getString(6))));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        iteam_name.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
        item_code.setCellValueFactory(new PropertyValueFactory<items, String>("code"));
        quantity.setCellValueFactory(new PropertyValueFactory<items, Integer>("quantity"));
        Rating.setCellValueFactory(new PropertyValueFactory<items, Integer>("rating"));
        price.setCellValueFactory(new PropertyValueFactory<items, Double>("price"));

        table.setItems(data);
    }
    public void addToCat() throws SQLException {
        data = table.getSelectionModel().getSelectedItems();
        String name  = data.get(0).getName();
        Double price  = data.get(0).getPrice();
        int rating = data.get(0).getRating();
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_table_se", "root", "");
        Statement statement = connection.createStatement();
        PreparedStatement posted = connection.prepareStatement("insert into cart(CName,Cprice,CRating) values ('"+name+"' ,'"+price+"','"+rating+"');");
        posted.executeUpdate();
    }
    public void goToCart() throws IOException {
        Stage s = (Stage) toCartBtn.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("cart.fxml"));
        s.setScene(new Scene(root2));
        s.show();
    }
    public void home() throws IOException {
        Stage s = (Stage) back.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("enter_iteam.fxml"));
        s.setScene(new Scene(root2));
        s.show();
    }
}
