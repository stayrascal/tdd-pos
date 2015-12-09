package com.tw.pos;

import com.tw.pos.entity.CartItem;
import com.tw.pos.entity.Goods;
import com.tw.pos.entity.GoodsRepository;

import java.util.List;


public class PosMachine {

    public double calculate(CartItem cartItem) {
        return cartItem.getNumber() * getGoodsPrice(cartItem.getBarcode());
    }

    public double calculate(List<CartItem> cartItems) {
        double sum = 0;
        for (CartItem cartItem : cartItems){
            sum += calculate(cartItem);
        }
        return sum;
    }

    public double getGoodsPrice(String barcode){
        try {
            Goods goods = GoodsRepository.getGoodsByBarcode(barcode);
            goods.useDiscountPromotion();
            return goods.getPrice();
        }catch (Exception e){
            throw new IllegalArgumentException("invalid goods");
        }
    }
}
