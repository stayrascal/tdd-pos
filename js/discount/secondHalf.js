
var SecondHalf = function(){
};

SecondHalf.prototype.apply = function(cartItem, price){
    this.price = price;
    var sum = 0;
    for (var i = 1; i <= cartItem.number;i++){
        if (i % 2 == 0){
            sum += price / 2;
        }else {
            sum += price;
        }
    }
    return sum;
};

SecondHalf.prototype.getDiscountPrice = function(originPrice){
  return this.price;
};