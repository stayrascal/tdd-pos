package com.tw.pos.entity;


import com.tw.pos.discount.DiscountPromotion;

public interface DiscountObservable {

    void addDiscountPromotion(DiscountPromotion discountPromotion);

    void addDiscountPromotionInFirst(DiscountPromotion discountPromotion);

    void removeDiscountPromotion(DiscountPromotion discountPromotion);

    void useDiscountPromotion();
}
