package com.tw.pos;


import com.tw.pos.entity.CartItem;
import com.tw.pos.parse.CartParse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CartParseTest {

    private CartParse cartParse;

    @Before
    public void setUp(){
        cartParse = new CartParse();
    }

    @Test
    public void shouldReturnCartItemWhenParseCartInfo(){
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2"));

        assertThat(cartItems.size(), is(1));
        assertThat(cartItems.get(0).getBarcode(), is("ITEM000001"));
        assertThat(cartItems.get(0).getNumber(), is(2));
    }

    @Test
    public void shouldReturnCartItemListWhenParseCartListInfo(){
        List<CartItem> cartItems = cartParse.parse(Arrays.asList("ITEM000001-2", "ITEM000003-5"));

        assertThat(cartItems.size(), is(2));
        assertThat(cartItems.get(0).getBarcode(), is("ITEM000001"));
        assertThat(cartItems.get(0).getNumber(), is(2));
        assertThat(cartItems.get(0).getBarcode(), is("ITEM000001"));
        assertThat(cartItems.get(0).getNumber(), is(2));
    }

    @Test
    public void shouldReturnEmptyListWhenParseEmptyCartInfo(){
        List<CartItem> cartItems = cartParse.parse(Arrays.asList());

        assertThat(cartItems.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenParseInvalidBarcode(){
        cartParse.parse(Arrays.asList("fdasf"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenParseInvalidNumber(){
        cartParse.parse(Arrays.asList("fdsafd-fds"));
    }
}
