package com.tw.pos.entity;


import com.tw.pos.discount.DiscountPromotion;

import java.util.LinkedList;

public class Goods implements DiscountObservable  {
    
    private String barcode;
    private double price;
    private LinkedList<DiscountPromotion> discountPromotions;

    public Goods(String barcode, Double price) {
        this.barcode = barcode;
        this.price = price;
        discountPromotions = new LinkedList<>();
    }

    public Goods(String barcode, double price, LinkedList<DiscountPromotion> discountPromotions) {
        this.barcode = barcode;
        this.price = price;
        this.discountPromotions = discountPromotions;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LinkedList<DiscountPromotion> getDiscountPromotions() {
        return discountPromotions;
    }

    @Override
    public void addDiscountPromotion(DiscountPromotion discountPromotion){
        discountPromotions.add(discountPromotion);
    }

    @Override
    public void addDiscountPromotionInFirst(DiscountPromotion discountPromotion){
        discountPromotions.addFirst(discountPromotion);
    }

    @Override
    public void removeDiscountPromotion(DiscountPromotion discountPromotion){
        discountPromotions.remove(discountPromotion);
    }

    @Override
    public void useDiscountPromotion(){
        discountPromotions.forEach(DiscountPromotion::apply);
    }
}
