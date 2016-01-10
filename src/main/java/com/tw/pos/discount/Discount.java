package com.tw.pos.discount;


import com.tw.pos.entity.Goods;

public class Discount implements DiscountPromotion {

    private Goods goods;
    private double discountRate;

    public Discount(Goods goods, double discountRate) {
        this.goods = goods;
        this.discountRate = discountRate / 100;
    }

    @Override
    public void apply() {
        goods.setPrice(goods.getPrice() * discountRate);
    }

    @Override
    public void prepare() {
        goods.addDiscountPromotionInFirst(this);
    }

    @Override
    public void cancle() {
        goods.removeDiscountPromotion(this);
        goods.setPrice(goods.getPrice() / discountRate);
    }
}
