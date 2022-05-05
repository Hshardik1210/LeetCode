/**
 * @param {number} n
 * @return {string[]}
 */
var backtrack = function (string, openCount, closedCount, n, result) {
    if(openCount==n && closedCount==n) {
        result.push(string)
        return
    }
    
    if(openCount<n) {
        backtrack(string+'(',openCount+1,closedCount,n,result)
    }
    
    
    if(closedCount<openCount) {
        backtrack(string+')',openCount,closedCount+1,n,result)
    }
}
var generateParenthesis = function(n) {
    let result =[]
    backtrack("",0,0,n, result)
    return result
};