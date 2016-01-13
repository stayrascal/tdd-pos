//var assert = require("assert")
//require("../../discount/secondHalf")

describe('Second Half Discount', function(){
    describe('apply()', function(){
        it('should charge 40 when apply second half discount for 5 items which price is 10', function(){
            var secondHalf = new SecondHalf();
            var cartItem = { number: 5 };
            var sum = secondHalf.apply(cartItem ,10);
            assert.equal(sum, 40);
        })
    })
})

