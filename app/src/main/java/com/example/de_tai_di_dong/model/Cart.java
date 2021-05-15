package com.example.de_tai_di_dong.model;

public class Cart {
    private int id;
    private int quantity;
    private int productID;
    private int Carid;

    public Cart(int id, int quantity, int productID, int carid) {
        this.id = id;
        this.quantity = quantity;
        this.productID = productID;
        Carid = carid;
    }
    public Cart(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCarid() {
        return Carid;
    }

    public void setCarid(int carid) {
        Carid = carid;
    }
}
