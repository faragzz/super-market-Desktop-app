package com.example.software_project;

public class cartitems {
    private  String itemsName;
    private double price;
    private int rating;
    cartitems(String itemsName,double price , int rating){
        this.itemsName = itemsName;
        this.price = price;
        this.rating = rating;
    }

    public String getItemsName() {
        return itemsName;
    }
    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
