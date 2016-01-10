
var Goods = function (barcode, price) {

    this.barcode = barcode;
    this.price = price;

    this.getBarCode = function () {
        return this.barcode;
    };

    this.getPrice = function () {
        return this.price;
    }
};

/*Goods.prototype.getBarCode = function(){
    this.barcode;
};*/
