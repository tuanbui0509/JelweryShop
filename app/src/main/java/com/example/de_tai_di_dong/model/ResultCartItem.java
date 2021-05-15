package com.example.de_tai_di_dong.model;

public class ResultCartItem {
private  String message;
private int result;

    public ResultCartItem(String message, int result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
