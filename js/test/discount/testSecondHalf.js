//var assert = require("assert")
//require("../../discount/secondHalf")

describe('Second Half Discount', function(){
    describe('apply()', function(){
        it('should return -1 when the value is not present', function(){
            var secondHalf = new SecondHalf();
            var cartItem = { number: 5 };
            var sum = secondHalf.apply(cartItem ,10);
            assert.equal(sum, 40);
        })
    })
})

