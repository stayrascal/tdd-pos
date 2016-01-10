
var GoodsParse = function(){
    this.regExp = new RegExp('^\\w+:\\d+$');
};

GoodsParse.prototype.parseObject = function(goodsInfoStr){
    var goodsInfoStr = goodsInfoStr.split(':');
    return new Goods(goodsInfoStr[0], parseFloat(goodsInfoStr[1]));
};

GoodsParse.prototype.parse = function(goodsInfoList){
    var goodsList = [];
    for (var i = 0, goodsInfoStr; goodsInfoStr =  goodsInfoList[i++];){
        this.validateCartItemInfo(goodsInfoStr);
        goodsList.push(this.parseObject(goodsInfoStr));
    }
    return goodsList;
};

GoodsParse.prototype.validateCartItemInfo = function(goodsInfo){
    if (!this.regExp.test(goodsInfo)){
        throw new Error('invalid objectInfoStrList format');
    }
};