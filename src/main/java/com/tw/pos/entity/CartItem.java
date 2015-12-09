package com.tw.pos.entity;


public class CartItem {

    private String barcode;
    private int number;

    public CartItem(String barcode, Integer number) {
        this.barcode = barcode;
        this.number = number;
    }

    public String getBarcode() {
        return barcode;
    }

    public Integer getNumber() {
        return number;
    }
}
