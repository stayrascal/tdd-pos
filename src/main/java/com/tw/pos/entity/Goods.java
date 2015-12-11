package com.tw.pos.entity;


public class Goods {
    
    private String barcode;
    private double price;

    public Goods(String barcode, Double price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }
}
