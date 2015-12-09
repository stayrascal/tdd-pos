package com.tw.pos.parse;


import com.tw.pos.discount.Discount;
import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.entity.Goods;
import com.tw.pos.entity.GoodsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DiscountParse {

    private static final Pattern PATTERN = Pattern.compile("^\\w+:\\d+$");

    public List<DiscountPromotion> parseDiscount(List<String> discountInfoStrList) {
        List<DiscountPromotion> discountPromotions = new ArrayList<>();
        discountInfoStrList.forEach(discountInfo->{
            if (!PATTERN.matcher(discountInfo).matches()){
                throw new IllegalArgumentException("invalid discountInfo input");
            }
            String[] discountInfoStr = discountInfo.split(":");
            Goods goods = GoodsRepository.getGoodsByBarcode(discountInfoStr[0]);
            DiscountPromotion discount = new Discount(goods, Double.valueOf(discountInfoStr[1]));
            discountPromotions.add(discount);
        });
        return discountPromotions;
    }
}
