package com.tw.pos;

import com.tw.pos.entity.CartItem;
import com.tw.pos.entity.Goods;
import com.tw.pos.entity.GoodsRepository;

import java.util.List;


public class PosMachine {

    /*public double calculate(CartItem cartItem) {
        double sum = 0;
        for (int i = 1; i <= cartItem.getNumber(); i++) {
            if (i % 2 == 0) {
                sum += getGoodsPrice(cartItem.getBarcode());
            } else {
                Goods goods = getGoods(cartItem.getBarcode());
                if (goods.getDiscountPromotions().size() == 0){
                    sum += goods.getPrice();
                } else {
                    for (DiscountPromotion discountPromotion : goods.getDiscountPromotions()){
                        if (discountPromotion instanceof SecondHalf) {
                            sum += goods.getPrice();
                        } else {
                            discountPromotion.apply();
                            sum += goods.getPrice();
                            discountPromotion.cancle();
                            discountPromotion.prepare();
                        }
                    }
                }
            }
        }
        return sum;
        //return cartItem.getNumber() * getGoodsPrice(cartItem.getBarcode());
    }*/
    public double calculate(CartItem cartItem){
        return cartItem.getNumber() * getGoodsPrice(cartItem.getBarcode());
    }

    public double calculate(List<CartItem> cartItems) {
        double sum = 0;
        for (CartItem cartItem : cartItems){
            sum += calculate(cartItem);
        }
        return sum;
    }

    public Goods getGoods(String barcode){
        try {
            return GoodsRepository.getGoodsByBarcode(barcode);
        }catch (Exception e){
            throw new IllegalArgumentException("invalid goods");
        }
    }

    public double getGoodsPrice(String barcode){
        Goods goods = getGoods(barcode);
        goods.useDiscountPromotion();
        return goods.getPrice();

    }
}
