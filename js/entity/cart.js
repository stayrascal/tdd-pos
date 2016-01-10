
var Cart = function(barcode, number){

    this.barcode = barcode;
    this.number = number;

    this.getBarcode = function(){
        return this.barcode;
    }

    this.getNumber = function(){
        return this.number;
    }
}