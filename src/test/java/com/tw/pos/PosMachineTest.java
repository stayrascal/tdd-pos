package com.tw.pos;


import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.discount.SecondHalf;
import com.tw.pos.entity.CartItem;
import com.tw.pos.entity.Goods;
import com.tw.pos.parse.CartParse;
import com.tw.pos.parse.GoodsParse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PosMachineTest {

    private PosMachine posMachine;
    private DiscountManager discountManager;

    @Before
    public void setUp(){
        GoodsParse goodsParse = new GoodsParse();
        List<Goods> goodses = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60"));
        discountManager = mock(DiscountManager.class);

        posMachine = new PosMachine(goodses, discountManager);
    }

    @Test
    public void theTotalPriceOfEmptyCartItemShouldBe0(){
        List<CartItem> emptyCart = Collections.emptyList();

        double total = posMachine.calculate(emptyCart);

        assertEquals(total, 0d, 1E-5);
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe80WhenParseItem000001(){

        CartItem cartItem = new CartItem("ITEM000001", 2);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Collections.emptyList());
        double price = posMachine.calculate(cartItem);

        assertEquals(price, 80d, 1E-5);
        //assertThat(price, is(80d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe56WhenDiscountItem000001(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Collections.singletonList(new DiscountPromotion(70)));
        double price = posMachine.calculate(cartItem);

        assertEquals(price, 56d, 1E-5);
        //assertThat(price, is(56d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe56WhenDiscountItem000001TwoTimes(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Arrays.asList(new DiscountPromotion(70), new DiscountPromotion(70)));
        double price = posMachine.calculate(cartItem);

        assertEquals(price, 39.2d, 1E-5);
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe60WhenSecondHalfPrice(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Collections.singletonList(new SecondHalf()));
        double price = posMachine.calculate(cartItem);

        System.out.println(price);
        assertThat(price, is(60d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe40WhenSecondHalfPriceButOnlyBuyOne(){
        CartItem cartItem = new CartItem("ITEM000001", 1);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Collections.singletonList(new SecondHalf()));
        double price = posMachine.calculate(cartItem);

        System.out.println(price);
        assertThat(price, is(40d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe100WhenSecondHalfPriceAndBuyThree(){
        CartItem cartItem = new CartItem("ITEM000001", 3);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Collections.singletonList(new SecondHalf()));
        double price = posMachine.calculate(cartItem);

        System.out.println(price);
        assertThat(price, is(100d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe42WhenDiscountItem000001AndSecondHalfPrice(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Arrays.asList(new DiscountPromotion(70), new SecondHalf()));
        double price = posMachine.calculate(cartItem);

        assertThat(price, is(42d));
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
        when(discountManager.getDiscountListByBarcode(anyString())).thenReturn(Collections.emptyList());
        double totalPrice = posMachine.calculate(cartItems);

        assertThat(totalPrice, is(510d));
    }

    @Test
    public void shouldReturnTheTotalPriceOfCartGoodsWhenParseCartInfoAndTheITEM000001IsDiscount(){
        CartParse cartParse = new CartParse();
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2", "ITEM000003-5", "ITEM000005-3"));
        when(discountManager.getDiscountListByBarcode("ITEM000001")).thenReturn(Collections.singletonList(new DiscountPromotion(70)));
        when(discountManager.getDiscountListByBarcode("ITEM000005")).thenReturn(Collections.singletonList(new DiscountPromotion(90)));
        double totalPrice = posMachine.calculate(cartItems);

        assertThat(totalPrice, is(468d));
    }


}
