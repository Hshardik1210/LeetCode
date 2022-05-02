/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */

var addTwoNumbers = function(l1, l2) {
    
    let itr1= l1
    let itr2= l2
    let size1=0
    let size2=0
    let carry = 0
    while(!_.isEmpty(itr1) && !_.isEmpty(itr2)) {
        let sum = parseInt(itr1.val + itr2.val + carry)
        let val = parseInt(sum%10)
        carry = parseInt(sum/10)
        itr1.val=val
        itr2.val=val
        last=itr2
        itr1=itr1.next
        itr2=itr2.next
        size1++
        size2++
    }
    
    while(!_.isEmpty(itr1)) {
        let sum = parseInt(itr1.val + carry)
        let val = parseInt(sum%10)
        carry = parseInt(sum/10)
        itr1.val=val
        last=itr1
        itr1=itr1.next
        size1++
    }
    
    while(!_.isEmpty(itr2)) {
        let sum = parseInt(itr2.val + carry)
        let val = parseInt(sum%10)
        carry = parseInt(sum/10)
        last=itr2
        itr2.val=val
        itr2=itr2.next
        size2++
    }
    
    if(carry>0) {
        last.next = new ListNode(1,null)
    }
    
    if(size1>size2) 
        return l1
    
    return l2
};