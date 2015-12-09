package com.tw.pos.parse;


import com.tw.pos.entity.CartItem;

import java.util.regex.Pattern;

public class CartParse extends Parse<CartItem> {

    private static final Pattern PATTERN = Pattern.compile("^\\w+-\\d+$");


    @Override
    protected CartItem parseObject(String cartInfo) {
        String[] cartInfoStr = cartInfo.split("-");
        return new CartItem(cartInfoStr[0], Integer.valueOf(cartInfoStr[1]));
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}
