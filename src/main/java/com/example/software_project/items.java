package com.example.software_project;

public class items {
    private String name;
    private String code;
    private int quantity;
    private int rating;
    private double price;
    items(String name,String code,int quantity,int rating,double price){
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
