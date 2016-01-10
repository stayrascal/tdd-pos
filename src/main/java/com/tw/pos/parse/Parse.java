package com.tw.pos.parse;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Parse<T> {

    public List<T> parse(List<String> objectInfoStrList) {
        List<T> objectItems = new ArrayList<>();
        objectInfoStrList.forEach(objectInfo ->{
            validateGoodsInfo(objectInfo);
            objectItems.add(parseObject(objectInfo));
        });
        return objectItems;
    }

    private void validateGoodsInfo(String cartInfo) {
        if (!getPattern().matcher(cartInfo).matches()){
            throw new IllegalArgumentException("invalid objectInfoStrList format");
        }
    }

    protected abstract Pattern getPattern();

    protected abstract T parseObject(String objectInfo);
}
