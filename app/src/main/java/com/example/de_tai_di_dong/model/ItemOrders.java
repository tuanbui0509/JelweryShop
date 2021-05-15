package com.example.de_tai_di_dong.model;

public class ItemOrders {
    private  int Id;
    private int Quantity;
    private float  UnitPrice;
    private int OrderId;
    private int ProId;

    public ItemOrders(int id, int quantity, float unitPrice, int orderId, int proId) {
        Id = id;
        Quantity = quantity;
        UnitPrice = unitPrice;
        OrderId = orderId;
        ProId = proId;
    }
    public ItemOrders(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProId() {
        return ProId;
    }

    public void setProId(int proId) {
        ProId = proId;
    }
}
