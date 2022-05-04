/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
const findMedianSortedArrays = function(a1, a2) {
    let n1=a1.length
    let n2=a2.length
    if(n1>n2)
        return findMedianSortedArrays(a2,a1)

    let mergedArrayLength = Math.floor((n1+n2+1)/2)
    let low=0
    let high = n1
    while(low<=high) {
        a1Cut = low + Math.floor((high-low)/2)
        a2Cut = mergedArrayLength - a1Cut

        let l1 = a1Cut==0 ? -Infinity : a1[a1Cut-1]
        let r1 = a1Cut==n1 ? Infinity : a1[a1Cut] 
        let l2 = a2Cut==0 ? -Infinity : a2[a2Cut-1]
        let r2 = a2Cut==n2 ? Infinity : a2[a2Cut]
        
        if(l1>r2) {
            high = a1Cut-1
        } else if (l2>r1) {
            low = a1Cut + 1
        } else {
            if ((n1+n2)%2==0) {
                return (Math.max(l1,l2) + Math.min(r1,r2))/2
            }
            return Math.max(l1,l2)
        }
    }
};