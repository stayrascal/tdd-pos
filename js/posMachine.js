var PosMachine = function (allGoods, discountManager) {
    this.allGoods = allGoods;
    this.discountManager = discountManager;
};

PosMachine.prototype.calculate = function (cartItem) {
    var goods = this.getGoods(cartItem.barcode);

    var discounts = this.discountManager.discountMap[goods.barcode];
    if (discounts == null || discounts.size == 0){
        return goods.price * cartItem.number;
    }

    var originPrice = goods.price;
    var totalPrice = 0;
    for (var i = 0, discount; discount = discounts[i++];){
        totalPrice = discount.apply(cartItem, originPrice);
        originPrice = discount.getDiscountPrice(originPrice);
    }
    return totalPrice;
};

PosMachine.prototype.calculateCartItems = function (cartItems) {
    var sum = 0;
    for (var i = 0, cartItem; cartItem = cartItems[i++];) {
        sum += this.calculate(cartItem);
    }
    return sum;
};

PosMachine.prototype.getGoods = function (barcode) {
    for (var i = 0, goods; goods = this.allGoods[i++];) {
        if (goods.barcode == barcode) {
            return goods;
        }
    }
    throw new Error("invalid goods");
};