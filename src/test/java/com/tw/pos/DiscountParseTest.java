package com.tw.pos;


import com.tw.pos.discount.Discount;
import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.discount.SecondHalf;
import com.tw.pos.parse.DiscountParse;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
        Map<String, LinkedList<Discount>> discounts = discountParse.parseDiscount(Collections.singletonList("ITEM000001:75"));

        assertThat(discounts.get("ITEM000001").size(), is(1));
        assertThat(discounts.get("ITEM000001").get(0) instanceof DiscountPromotion, is(true));
    }

    @Test
    public void shouldReturnDiscountInfoWhenParseDifferentDiscountBarcodeInfo(){
        Map<String, LinkedList<Discount>> discounts = discountParse.parseDiscount(Arrays.asList("ITEM000001:75", "ITEM000001"));

        assertThat(discounts.get("ITEM000001").size(), is(2));
        assertThat(discounts.get("ITEM000001").get(0) instanceof DiscountPromotion, is(true));
        assertThat(discounts.get("ITEM000001").get(1) instanceof SecondHalf, is(true));
    }

    @Test
    public void shouldReturnDiscountInfoWhenParseSecondHlatFirstAndTheDiscountBarcodeInfo(){
        Map<String, LinkedList<Discount>> discounts = discountParse.parseDiscount(Arrays.asList("ITEM000001", "ITEM000001:75"));

        assertThat(discounts.get("ITEM000001").size(), is(2));
        assertThat(discounts.get("ITEM000001").get(0) instanceof DiscountPromotion, is(true));
        assertThat(discounts.get("ITEM000001").get(1) instanceof SecondHalf, is(true));
    }

    @Test
    public void shouldReturnDiscountInfoWhenParseMultipleDiscountBarcodeInfo(){
        Map<String, LinkedList<Discount>> discounts = discountParse.parseDiscount(Arrays.asList("ITEM000001:75", "ITEM000005:90"));

        assertThat(discounts.get("ITEM000001").size(), is(1));
        assertThat(discounts.get("ITEM000005").size(), is(1));
        assertThat(discounts.get("ITEM000001").get(0) instanceof DiscountPromotion, is(true));
        assertThat(discounts.get("ITEM000005").get(0) instanceof DiscountPromotion, is(true));
    }

    @Test
    public void shouldReturnDiscountInfoWhenParseComplexDiscountBarcodeInfo(){
        Map<String, LinkedList<Discount>> discounts = discountParse.parseDiscount(Arrays.asList("ITEM000001:75", "ITEM000005:90", "ITEM000001", "ITEM000005"));

        assertThat(discounts.get("ITEM000001").size(), is(2));
        assertThat(discounts.get("ITEM000005").size(), is(2));
        assertThat(discounts.get("ITEM000001").get(0) instanceof DiscountPromotion, is(true));
        assertThat(discounts.get("ITEM000001").get(1) instanceof SecondHalf, is(true));
        assertThat(discounts.get("ITEM000001").get(0) instanceof DiscountPromotion, is(true));
        assertThat(discounts.get("ITEM000005").get(1) instanceof SecondHalf, is(true));
    }

}
