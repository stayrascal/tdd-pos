package com.tw.pos.entity;

import com.tw.pos.parse.GoodsParse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsRepository {

    private static Map<String, Goods> goodsMap = new HashMap<>();

    static {
        GoodsParse goodsParse = new GoodsParse();
        List<Goods> goodsList = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60"));
        goodsList.forEach(goods -> goodsMap.put(goods.getBarcode(), goods));
    }

    public static Goods getGoodsByBarcode(String barcode){
        return goodsMap.get(barcode);
    }
}
