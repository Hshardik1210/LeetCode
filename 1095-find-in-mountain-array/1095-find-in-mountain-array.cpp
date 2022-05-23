/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class MountainArray {
 *   public:
 *     int get(int index);
 *     int length();
 * };
 */

class Solution {
public:
    int indexOfMaxElement(MountainArray &mountainArr) {
        int low = 1;
        int high = mountainArr.length()-2;

        while(low<=high) {
            int mid = low + (high - low) / 2;
            if(mountainArr.get(mid)>mountainArr.get(mid+1) && mountainArr.get(mid)>mountainArr.get(mid-1))
                return mid;
            else if (mountainArr.get(mid)>mountainArr.get(mid-1))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    int binarySearch(MountainArray &mountainArr, int target, int low, int high, bool increasingOrder) {
        while(low<=high) {
            int mid = low + (high -low) / 2;
            if (mountainArr.get(mid)==target)
                return mid;
            else if (mountainArr.get(mid) > target) {
                if(increasingOrder)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            else {
                if (increasingOrder)
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }


    int findInMountainArray(int target, MountainArray &mountainArr) {
        int maxElementIndex = indexOfMaxElement(mountainArr);
        if(maxElementIndex!=-1 && target == mountainArr.get(maxElementIndex))
            return maxElementIndex;

        if (maxElementIndex!=-1) {
            int leftSearch = binarySearch(mountainArr, target, 0, maxElementIndex-1, true);
            if (leftSearch!=-1)
                return leftSearch;

            return binarySearch(mountainArr, target, maxElementIndex+1, mountainArr.length() -1, false);
        }
        return -1;
    }
};