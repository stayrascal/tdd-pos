
var DiscountPromotion = function(discounRate){
    this.discountRate = discounRate / 100;
};

DiscountPromotion.prototype.apply = function(cartItem, price){
    return cartItem.number * price * this.discountRate;
};

DiscountPromotion.prototype.getDiscountPrice = function(originPrice){
  return originPrice * this.discountRate;
};