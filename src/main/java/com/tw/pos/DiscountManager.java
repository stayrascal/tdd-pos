package com.tw.pos;


import com.tw.pos.discount.Discount;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DiscountManager {

    private final Map<String, LinkedList<Discount>> discountMap;

    public DiscountManager(Map<String, LinkedList<Discount>> discountMap) {
        this.discountMap = discountMap;
    }

    public List<Discount> getDiscountListByBarcode(String barcode){
        return discountMap.get(barcode);
    }
}
