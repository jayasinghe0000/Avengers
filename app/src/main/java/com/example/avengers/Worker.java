package com.example.avengers;

public class Worker {

    int id;
    String name,email,category,price;


    public Worker(){

    }

    public Worker(int id, String name, String email, String price, String category) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.price = price;
        this.category = category;

    }

    public Worker(String name, String email, String price, String category) {
        this.name = name;
        this.email = email;
        this.price = price;
        this.category = category;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
