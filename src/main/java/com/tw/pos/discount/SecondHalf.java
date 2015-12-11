package com.tw.pos.discount;

import com.tw.pos.entity.Goods;


public class SecondHalf implements DiscountPromotion {

    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public SecondHalf(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void apply() {
        goods.setPrice(goods.getPrice() / 2);
    }

    @Override
    public void prepare() {
        goods.addDiscountPromotion(this);
    }

    @Override
    public void cancle() {
        goods.setPrice(goods.getPrice() * 2);
        goods.removeDiscountPromotion(this);
    }
}
