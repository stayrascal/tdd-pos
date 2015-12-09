package com.tw.pos;

import com.tw.pos.entity.Goods;
import com.tw.pos.parse.GoodsParse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GoodsParseTest {

    private GoodsParse goodsParse;

    @Before
    public void setUp(){
        goodsParse = new GoodsParse();
    }

    @Test
    public void shouldReturnRightGoodsListWhenParseBarcode(){
        List<Goods> goodsList = goodsParse.parse(Arrays.asList("ITEM000001:40"));

        assertThat(goodsList.size(), is(1));
        assertThat(goodsList.get(0).getBarcode(), is("ITEM000001"));
        assertThat(goodsList.get(0).getPrice(), is(40d));
    }

    @Test
    public void shouldReturnRightGoodsListWhenParseBarcodeList(){
        List<Goods> goodsList = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50"));

        assertThat(goodsList.size(), is(2));
        assertThat(goodsList.get(0).getBarcode(), is("ITEM000001"));
        assertThat(goodsList.get(0).getPrice(), is(40d));
        assertThat(goodsList.get(1).getBarcode(), is("ITEM000003"));
        assertThat(goodsList.get(1).getPrice(), is(50d));
    }

    @Test
    public void shouldReturnEmptyListWhenParseEmptyBarcodeList(){
        List<Goods> goodsList = goodsParse.parse(Arrays.asList());

        assertThat(goodsList.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenParseInvalidBarcode(){
        goodsParse.parse(Arrays.asList("fdsaf"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenParseInvalidPrice(){
        goodsParse.parse(Arrays.asList("fdsaf:gsfdg"));
    }
}
