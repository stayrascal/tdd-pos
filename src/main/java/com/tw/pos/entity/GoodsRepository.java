package com.tw.pos.entity;

import com.tw.pos.parse.GoodsParse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsRepository {

    private static Map<String, Goods> goodsMap = new HashMap<>();
    private static Map<String, Goods> discountGoodsMap = new HashMap<>();

    static {
        GoodsParse goodsParse = new GoodsParse();
        List<Goods> goodsList = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60"));
        goodsList.forEach(goods -> goodsMap.put(goods.getBarcode(), goods));
    }

    public static Goods getGoodsByBarcode(String barcode) {
        if (discountGoodsMap.get(barcode) != null) {
            return discountGoodsMap.get(barcode);
        }
        Goods goods = goodsMap.get(barcode);
        Goods copyGoods = new Goods(goods.getBarcode(), goods.getPrice(), goods.getDiscountPromotions());
        discountGoodsMap.put(barcode, copyGoods);
        return copyGoods;
    }
}
