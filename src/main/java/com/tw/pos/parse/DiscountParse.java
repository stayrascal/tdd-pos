package com.tw.pos.parse;


import com.tw.pos.discount.Discount;
import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.discount.SecondHalf;

import java.util.*;
import java.util.regex.Pattern;

public class DiscountParse {

    private static final Pattern DISCOUNT_PATTERN = Pattern.compile("^\\w+:\\d+$");
    private static final Pattern SECOND_HALF_PATTERN = Pattern.compile("^\\w+$");

    public Map<String, List<Discount>> parseDiscount(List<String> discountInfoStrList) {
        Map<String, List<Discount>> discountMap = new HashMap<>();
        discountInfoStrList.forEach(discountInfo->{
            if (!DISCOUNT_PATTERN.matcher(discountInfo).matches() && !SECOND_HALF_PATTERN.matcher(discountInfo).matches()){
                throw new IllegalArgumentException("invalid discountInfo input");
            }

            if (DISCOUNT_PATTERN.matcher(discountInfo).matches()){
                String[] discountInfoStr = discountInfo.split(":");

                String barcode = discountInfoStr[0];
                Discount discount = new DiscountPromotion(Double.valueOf(discountInfoStr[1]));
                addDiscount(discountMap, barcode, discount);
            }

            if (SECOND_HALF_PATTERN.matcher(discountInfo).matches()){
                String barcode = discountInfo.trim();
                Discount discount = new SecondHalf();
                addDiscount(discountMap, barcode, discount);
            }
        });
        return discountMap;
    }

    private void addDiscount(Map<String, List<Discount>> discountMap, String barcode, Discount discount) {
        List<Discount> discountList= discountMap.get(barcode);
        if (discountList == null || discountList.size() == 0){
            discountList = new ArrayList<>();
            discountList.add(discount);
        }else{
            discountList.add(discount);
        }
        discountMap.put(barcode, discountList);
    }

}
