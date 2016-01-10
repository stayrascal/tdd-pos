
var CartItemParse = function(){
    this.regExp = new RegExp('^\\w+-\\d+$');
};

CartItemParse.prototype.parseObject = function(goodsInfoStr){
    var goodsInfoStr = goodsInfoStr.split('-');
    return new Cart(goodsInfoStr[0], parseInt(goodsInfoStr[1]));
};

CartItemParse.prototype.parse = function(cartItemsInfoList){
    var cartItems = [];
    for (var i = 0,cartItemInfoStr; cartItemInfoStr = cartItemsInfoList[i++];){
        this.validateCartItemInfo(cartItemInfoStr);
        cartItems.push(this.parseObject(cartItemInfoStr));
    }
    return cartItems;
};

CartItemParse.prototype.validateCartItemInfo = function(cartItemInfo){
    if (!this.regExp.test(cartItemInfo)){
        throw new Error('invalid objectInfoStrList format');
    }
};