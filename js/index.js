
requirejs([
    'js/entity/cart.js',
    'js/entity/goods.js',
    'js/discount/discountPromotion.js',
    'js/discount/secondHalf.js',
    'js/parse/cartItemParse.js',
    'js/parse/goodsParse.js',
    'js/parse/discountParse.js',
    'js/discountManager.js',
    'js/posMachine.js'],
    function(){
        var goodsParse = new GoodsParse();
        var goodses = goodsParse.parse(['ITEM000001:40', 'ITEM000003:50', 'ITEM000005:60']);

        var cartItemParse = new CartItemParse();
        var cartItems = cartItemParse.parse(['ITEM000001-2', 'ITEM000003-5', 'ITEM000005-3']);

        var discountParse = new DiscountParse();
        //var discountMap = discountParse.parse(['ITEM000001:75', 'ITEM000005:90']);
        var discountMap = discountParse.parse(['ITEM000001:75', 'ITEM000001', 'ITEM000005','ITEM000005:90']);

        var discountManager = new DiscountManager(discountMap);
        var posMachine = new PosMachine(goodses, discountManager);
        var totalPrice = posMachine.calculateCartItems(cartItems);
        console.log("totalPrice: " + totalPrice);
});
