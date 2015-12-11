package com.tw.pos.discount;


import com.tw.pos.entity.CartItem;

public interface Discount {

    double apply(CartItem cartItem, double price);

    double getDiscountPrice(double originPrice);
}
