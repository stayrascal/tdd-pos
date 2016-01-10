package com.tw.pos;


import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.parse.DiscountParse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountParseTest {


    private DiscountParse discountParse;

    @Before
    public void setUp(){
        discountParse = new DiscountParse();
    }

    @Test
    public void shouldReturnDiscountInfoWhenParseDiscountBarcodeInfo(){
        List<DiscountPromotion> discounts = discountParse.parseDiscount(Arrays.asList("ITEM000001:75"));

        assertThat(discounts.size(), is(1));
        /*assertThat(discounts.get(0).getBarcode(), is("ITEM000001:75"));
        assertThat(discounts.get(0).getDiscount(), is("ITEM000001:75"));*/
    }

}
