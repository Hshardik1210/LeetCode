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
   int peakIndex(MountainArray &mountainArr) {
        int s = 0;
        int e = mountainArr.length() - 1;
        int mid = s + (e - s)/2;
        while(s < e)
        {
            if(mountainArr.get(mid) > mountainArr.get(mid+1)) //you are in decreasing part
                e = mid;
            else if(mountainArr.get(mid) < mountainArr.get(mid+1)) //you are in increasing part
                s = mid + 1;
            mid = s + (e - s)/2;
        }
        return s;
    }
    
    int orderAgnosticBinarysearch(MountainArray &mountainArr, int target, int s, int e)
    {
        if(mountainArr.get(e) > mountainArr.get(s))
        {
            int mid = s + (e - s)/2;
            while(s <= e)
            {
                if(mountainArr.get(mid) == target)
                    return mid;
                else if(mountainArr.get(mid) < target)
                    s = mid + 1;
                else 
                    e = mid - 1;
                mid = s + (e - s)/2;
                
            }
            return -1;
        }
        else
        {
            int mid = s + (e - s)/2;
            while(s <= e)
            {
                if(mountainArr.get(mid) == target)
                    return mid;
                else if(mountainArr.get(mid) < target)
                    e = mid - 1;
                else 
                    s = mid + 1;
                mid = s + (e - s)/2;
                
            }
            return -1;            
        }
    }


int findInMountainArray(int target, MountainArray &mountainArr) {
        int peak = peakIndex(mountainArr);
        int firstTry = orderAgnosticBinarysearch(mountainArr, target, 0, peak);
        if(firstTry != -1)
            return firstTry;
        else
            return orderAgnosticBinarysearch(mountainArr, target, peak+1, mountainArr.length() - 1);
    }
    
    
};