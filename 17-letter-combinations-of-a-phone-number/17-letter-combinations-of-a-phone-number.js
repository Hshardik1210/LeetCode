/**
 * @param {string} digits
 * @return {string[]}
 */
let map = {
    "2":"abc",
    "3":"def",
    "4":"ghi",
    "5":"jkl",
    "6":"mno",
    "7":"pqrs",
    "8":"tuv",
    "9": "wxyz"
}

var backtrack = function(string, digits, i,  result) {
    if(string.length==digits.length) {
        result.push(string)
        return
    }
    
    for(let char of map[digits[i]]) {
        backtrack(string+char,digits, i+1 , result)
    }
    
}
var letterCombinations = function(digits) {
    let result = []
    if(digits.length==0)
        return result
    backtrack("", digits, 0, result)
    return result
};