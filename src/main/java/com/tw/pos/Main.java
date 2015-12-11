package com.tw.pos;


import com.tw.pos.discount.Discount;
import com.tw.pos.entity.CartItem;
import com.tw.pos.entity.Goods;
import com.tw.pos.parse.CartParse;
import com.tw.pos.parse.DiscountParse;
import com.tw.pos.parse.GoodsParse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){

        GoodsParse goodsParse = new GoodsParse();
        List<Goods> goodses = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60"));

        CartParse cartParse = new CartParse();
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2", "ITEM000003-5", "ITEM000005-3"));

        DiscountParse discountParse = new DiscountParse();

        //Map<String, List<Discount>> discountMap = discountParse.parseDiscount(Arrays.asList("ITEM000001:75", "ITEM000005:90"));
        Map<String, LinkedList<Discount>> discountMap = discountParse.parseDiscount(Arrays.asList("ITEM000001:75", "ITEM000005:90", "ITEM000001", "ITEM000005"));

        DiscountManager discountManager = new DiscountManager(discountMap);
        PosMachine posMachine = new PosMachine(goodses, discountManager);

        double total = posMachine.calculate(cartItems);

        System.out.println("总价" +total);
    }
}
