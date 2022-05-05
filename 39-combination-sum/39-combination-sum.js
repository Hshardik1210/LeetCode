/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */

var recur =function (candidates, i, target, sum, array, result) {
    if (sum == target) {
        let temp = _.cloneDeep(array)
        result.push(temp)
        return
    }
    if (i<candidates.length && sum<target)
    {
    recur(candidates, i+1, target, sum,array, result)
    
    array.push(candidates[i])
    recur(candidates, i, target, sum+candidates[i],array,result)
    array.pop()
    }
}

var combinationSum = function(candidates, target) {
    let result = []
    recur(candidates, 0, target, 0, [], result)
    return result
};