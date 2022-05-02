/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let map = {}
    let i=0
    for(let num of nums) {
        if(map[target-num]!=undefined)
            return [i,map[target-num]];
        map[num] = i++;
    }
};