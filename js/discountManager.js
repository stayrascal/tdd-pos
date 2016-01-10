
var DiscountManager = function(discountMap){
    this.discountMap = discountMap;
};

DiscountManager.prototype.getDiscountListByBarcode = function(barcode){
  return this.discountMap.get(barcode);
};