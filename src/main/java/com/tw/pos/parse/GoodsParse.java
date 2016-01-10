package com.tw.pos.parse;


import com.tw.pos.entity.Goods;

import java.util.regex.Pattern;

public class GoodsParse extends Parse<Goods> {

    private static final Pattern PATTERN = Pattern.compile("^\\w+:\\d+$");

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    protected Goods parseObject(String objectInfo) {
        String[] goodsStr = objectInfo.split(":");
        return new Goods(goodsStr[0], Double.valueOf(goodsStr[1]));
    }
}
