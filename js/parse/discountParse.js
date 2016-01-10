
var DiscountParse = function(){
    this.discountRxp = new RegExp('^\\w+:\\d+$');
    this.secondHalfRxp = new RegExp('^\\w+$');
};

DiscountParse.prototype.parse = function(discountInfoStrList){
    var discountMap = {};
    for (var i = 0, discountInfoStr; discountInfoStr = discountInfoStrList[i++];){
        if (!this.discountRxp.test(discountInfoStr) && !this.secondHalfRxp.test(discountInfoStr)){
            throw new Error('invalid discountInfo input');
        }

        if (this.discountRxp.test(discountInfoStr)){
            var discountInfoList = discountInfoStr.split(':');

            var barcode = discountInfoList[0];
            var discount = new DiscountPromotion(parseFloat(discountInfoList[1]));
            this.addDiscount(discountMap, barcode, discount);
        }

        if (this.secondHalfRxp.test(discountInfoStr)){
            var barcode = discountInfoStr.trim();
            var discount = new SecondHalf();
            this.addDiscount(discountMap, barcode, discount);
        }
    }
    return discountMap;
};

DiscountParse.prototype.addDiscount= function(discountMap, barcode, discount){
    var discountList = discountMap[barcode];
    if (discountList == null || discountList.size == 0){
        discountList = [];
    }
    if (discount instanceof DiscountPromotion){
        discountList.splice(0,0,discount);
    }else {
        discountList.push(discount);
    }
    discountMap[barcode] = discountList;
};

