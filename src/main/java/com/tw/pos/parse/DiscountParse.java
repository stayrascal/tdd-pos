package com.tw.pos.parse;


import com.tw.pos.discount.Discount;
import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.discount.SecondHalf;
import com.tw.pos.entity.Goods;
import com.tw.pos.entity.GoodsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DiscountParse {

    private static final Pattern DISCOUNT_PATTERN = Pattern.compile("^\\w+:\\d+$");
    private static final Pattern SECOND_HALF_PATTERN = Pattern.compile("^\\w+$");

    public List<DiscountPromotion> parseDiscount(List<String> discountInfoStrList) {
        List<DiscountPromotion> discountPromotions = new ArrayList<>();
        discountInfoStrList.forEach(discountInfo->{
            if (!DISCOUNT_PATTERN.matcher(discountInfo).matches() && !SECOND_HALF_PATTERN.matcher(discountInfo).matches()){
                throw new IllegalArgumentException("invalid discountInfo input");
            }

            if (DISCOUNT_PATTERN.matcher(discountInfo).matches()){
                String[] discountInfoStr = discountInfo.split(":");
                Goods goods = GoodsRepository.getGoodsByBarcode(discountInfoStr[0]);
                DiscountPromotion discount = new Discount(goods, Double.valueOf(discountInfoStr[1]));
                discountPromotions.add(discount);
            }

            if (SECOND_HALF_PATTERN.matcher(discountInfo).matches()){
                Goods goods = GoodsRepository.getGoodsByBarcode(discountInfo.trim());
                DiscountPromotion secondHalf = new SecondHalf(goods);
                discountPromotions.add(secondHalf);
            }
        });
        return discountPromotions;
    }
}
