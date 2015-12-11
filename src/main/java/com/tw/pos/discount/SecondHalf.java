package com.tw.pos.discount;

import com.tw.pos.entity.CartItem;


public class SecondHalf implements Discount {

    private double price;

    @Override
    public double apply(CartItem cartItem, double price) {
        this.price = price;
        double sum = 0;
        for (int i = 1; i <= cartItem.getNumber(); i++){
            if (i % 2 == 0){
                sum += price / 2;
            } else {
                sum += price;
            }
        }
        return sum;
    }

    @Override
    public double getDiscountPrice(double originPrice) {
        return price;
    }
}
