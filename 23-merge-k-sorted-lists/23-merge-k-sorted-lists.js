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
var mergeKLists = function(lists) {

    if(lists.length==0)
    return null

    const mpq = new MinPriorityQueue ({ compare: (a,b) => { return a.val-b.val } })

    for (let list of lists) {
        if (!_.isEmpty(list))
            mpq.enqueue(list)
    }
    let head = mpq.front()
    while(!mpq.isEmpty()) {
        let top = mpq.dequeue()
        if(top.next!=null)
            mpq.enqueue(top.next)
        top.next = mpq.front()
    }
    return head
}