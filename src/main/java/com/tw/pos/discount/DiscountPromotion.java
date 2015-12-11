package com.tw.pos.discount;


import com.tw.pos.entity.CartItem;

public class DiscountPromotion implements Discount {

    private double discountRate;

    public DiscountPromotion(double discountRate) {
        this.discountRate = discountRate / 100d;
    }

    @Override
    public double apply(CartItem cartItem, double price) {
        return cartItem.getNumber() * price * this.discountRate;
    }

    @Override
    public double getDiscountPrice(double originPrice) {
        return originPrice * discountRate;
    }
}
