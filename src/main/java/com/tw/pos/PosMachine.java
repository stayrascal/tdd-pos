package com.tw.pos;

import com.tw.pos.discount.Discount;
import com.tw.pos.entity.CartItem;
import com.tw.pos.entity.Goods;

import java.util.List;


public class PosMachine {

    private List<Goods> allGoods;
    private DiscountManager discountManager;

    public PosMachine(List<Goods> allGoods, DiscountManager promotionManager) {
        this.allGoods = allGoods;
        this.discountManager = promotionManager;
    }

    public double calculate(CartItem cartItem) {
        Goods goods = getGoods(cartItem.getBarcode());
        List<Discount> discounts = discountManager.getDiscountListByBarcode(goods.getBarcode());
        if (discounts == null || discounts.size() == 0){
            return cartItem.getNumber() * goods.getPrice();
        }

        double originPrice = goods.getPrice();
        double totalPrice = 0;
        for (Discount discount : discounts) {

            totalPrice = discount.apply(cartItem, originPrice);
            originPrice = discount.getDiscountPrice(originPrice);
        }
        return totalPrice;
    }

    public double calculate(List<CartItem> cartItems) {
        double sum = 0;
        for (CartItem cartItem : cartItems) {
            sum += calculate(cartItem);
        }
        return sum;
    }

    public Goods getGoods(String barcode) {
        for (Goods goods : allGoods) {
            if (goods.getBarcode().equals(barcode)) {
                return goods;
            }
        }
        throw new IllegalArgumentException("invalid goods");
    }

    public double getGoodsPrice(String barcode) {
        return getGoods(barcode).getPrice();

    }
}
