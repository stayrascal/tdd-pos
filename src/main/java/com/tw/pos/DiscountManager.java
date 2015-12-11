package com.tw.pos;


import com.tw.pos.discount.Discount;

import java.util.List;
import java.util.Map;

public class DiscountManager {

    private final Map<String, List<Discount>> discountMap;

    public DiscountManager(Map<String, List<Discount>> discountMap) {
        this.discountMap = discountMap;
    }

    public List<Discount> getDiscountListByBarcode(String barcode){
        return discountMap.get(barcode);
    }
}
