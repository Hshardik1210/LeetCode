/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */

var merge2Lists = function (list1,list2) {
    if (_.isEmpty(list1))
        return list2
    if (_.isEmpty(list2))
        return list1
    let i = list1
    let j =list2
    let head = {}
    if(i.val<j.val) {
        head = i
        i=i.next
    } else {
        head = j
        j=j.next
    }
    let previous = head
    while(!_.isEmpty(i) && !_.isEmpty(j)) {

        if(i.val<j.val) { 
            previous.next = i
            i=i.next
            previous = previous.next
        } else {
            previous.next = j
            j=j.next
            previous = previous.next
        }
    }

    if(!_.isEmpty(i)) {
        previous.next = i
    }

    if(!_.isEmpty(j)) {
        previous.next = j
    }
    return head
}
var mergeKLists = function(lists) {
    if(lists.length == 0)
        return null
    while (lists.length!=1) {
        let l=0
        let r=lists.length-1
        while(l<r) {
            lists[l] = merge2Lists(lists[l],lists[r])
            lists.pop()
            l++
            r--
        }
    }
    return lists[0]
}