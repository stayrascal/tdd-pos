package com.tw.pos;


import com.tw.pos.discount.DiscountPromotion;
import com.tw.pos.entity.CartItem;
import com.tw.pos.entity.GoodsRepository;
import com.tw.pos.parse.CartParse;
import com.tw.pos.parse.DiscountParse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PosMachineTest {

    private PosMachine posMachine;
    private List<DiscountPromotion> discountPromotions;

    @Before
    public void setUp(){
        /*GoodsParse goodsParse = new GoodsParse();
        List<Goods> goodses = goodsParse.parse(Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60"));*/
        posMachine = new PosMachine();
        DiscountParse discountParse = new DiscountParse();
        discountPromotions = discountParse.parseDiscount(Arrays.asList("ITEM000001:70"/*, "ITEM000005:90"*/));
    }

    /*@After
    public void tearDown(){
        discountPromotions.forEach(DiscountPromotion::cancle);
    }*/

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe80WhenParseItem000001(){

        CartItem cartItem = new CartItem("ITEM000001", 2);

        double price = posMachine.calculate(cartItem);

        assertThat(price, is(80d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe56WhenDiscountItem000001(){
        CartItem cartItem = new CartItem("ITEM000001", 2);
        discountPromotions.forEach(DiscountPromotion::prepare);
        double price = posMachine.calculate(cartItem);
        discountPromotions.forEach(DiscountPromotion::cancle);

        assertThat(price, is(56d));
    }

    @Test
    public void theTotalPriceOfSimpleTypeGoodsShouldBe180WhenParseItem000005(){

        CartItem cartItem = new CartItem("ITEM000005", 3);
        assertThat(GoodsRepository.getGoodsByBarcode(cartItem.getBarcode()).getDiscountPromotions().size(), is(0));
        double price = posMachine.calculate(cartItem);
        assertThat(GoodsRepository.getGoodsByBarcode(cartItem.getBarcode()).getPrice(), is(60d));
        assertThat(price, is(180d));
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
}
