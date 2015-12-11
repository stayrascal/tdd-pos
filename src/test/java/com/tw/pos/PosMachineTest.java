package com.tw.pos;


import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.entity.CartItem;
import com.tw.pos.parse.CartParse;
import com.tw.pos.parse.DiscountParse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PosMachineTest {

    private PosMachine posMachine;
    private DiscountParse discountParse;

    @Before
    public void setUp(){
        /*GoodsParse goodsParse = new GoodsParse();
        List<Goods> goodses = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60"));*/
        posMachine = new PosMachine();
        discountParse = new DiscountParse();
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe80WhenParseItem000001(){

        CartItem cartItem = new CartItem("ITEM000001", 2);

        double price = posMachine.calculate(cartItem);

        assertEquals(price, 80d, 1E-5);
        //assertThat(price, is(80d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe56WhenDiscountItem000001(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Collections.singletonList("ITEM000001:70"));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double price = posMachine.calculate(cartItem);
        discountPromotions.forEach(DiscountPromotion::cancle);

        assertEquals(price, 56d, 1E-5);
        //assertThat(price, is(56d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe56WhenDiscountItem000001TwoTimes(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Arrays.asList("ITEM000001:70", "ITEM000001:70"));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double price = posMachine.calculate(cartItem);
        discountPromotions.forEach(DiscountPromotion::cancle);

        assertEquals(price, 39.2d, 1E-5);
    }

    /*@Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe60WhenSecondHalfPrice(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Arrays.asList("ITEM000001"));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double price = posMachine.calculate(cartItem);
        discountPromotions.forEach(DiscountPromotion::cancle);

        System.out.println(price);
        assertThat(price, is(60d));
    }*/

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe56WhenDiscountItem000001AndSecondHalfPrice(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Arrays.asList("ITEM000001:70", "ITEM000001"));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double price = posMachine.calculate(cartItem);
        discountPromotions.forEach(DiscountPromotion::cancle);

        assertThat(price, is(28d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe180WhenParseItem000005(){

        CartItem cartItem = new CartItem("ITEM000005", 3);
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Collections.singletonList("ITEM000005:90"));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double price = posMachine.calculate(cartItem);
        discountPromotions.forEach(DiscountPromotion::cancle);
        assertThat(price, is(162d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShouldThrowExceptionWhenParseGoodsThatNotExist(){

        CartItem cartItem = new CartItem("ITEM0001210", 2);

        posMachine.calculate(cartItem);
    }

    @Test
    public void shouldReturnTheTotalPriceOfCartGoodsWhenParseCartInfo(){
        CartParse cartParse = new CartParse();
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2", "ITEM000003-5", "ITEM000005-3"));

        double totalPrice = posMachine.calculate(cartItems);

        assertThat(totalPrice, is(510d));
    }

    @Test
    public void shouldReturnTheTotalPriceOfCartGoodsWhenParseCartInfoAndTheITEM000001IsDiscount(){
        CartParse cartParse = new CartParse();
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2", "ITEM000003-5", "ITEM000005-3"));
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Collections.singletonList("ITEM000001:70"/*, "ITEM000005:90"*/));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double totalPrice = posMachine.calculate(cartItems);
        discountPromotions.forEach(DiscountPromotion::cancle);

        assertThat(totalPrice, is(486d));
    }

    @Test
    public void shouldReturnTheTotalPriceOfCartGoodsWhenITEM000001AndITEM000005AreDiscount(){
        CartParse cartParse = new CartParse();
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2", "ITEM000003-5", "ITEM000005-3"));
        List<DiscountPromotion> discountPromotions = discountParse.parseDiscount(Arrays.asList("ITEM000001:70", "ITEM000005:90"));

        discountPromotions.forEach(DiscountPromotion::prepare);
        double totalPrice = posMachine.calculate(cartItems);
        discountPromotions.forEach(DiscountPromotion::cancle);

        assertThat(totalPrice, is(468d));
    }


}
