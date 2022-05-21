class Solution {
public:
    
    int findMinElementIndex(vector<int>& a) {
        int n=a.size();
        int l=0,r=n-1,m=0;
        while(l<=r)
        {
            if(a[l]<=a[r]) //if array is not rotated, take case [2,1]
                return l;
            
            m=l+(r-l)/2;
            
            if(a[(m-1+n)%n]>a[m] && a[m]<a[(m+1)%n] )
                break;
            
            if(a[m]>=a[l])
                l=m+1;
            else if(a[r]>=a[m])
                r=m-1;
        }
        return m;
    }
    
    int binarySearch(vector<int>&nums, int low, int high, int target) {
        while(low<=high) {
            int mid = low + (high - low)/2;
            if(nums[mid] == target) {
                return mid;
            }
            
            if(target>nums[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
    
    int search(vector<int>& nums, int target) {
        int minElementIndex = findMinElementIndex(nums);
        int leftSearch = binarySearch(nums, 0, minElementIndex-1, target);
        int rightSearch = binarySearch(nums, minElementIndex, nums.size()-1, target);
        
        if (leftSearch==-1 && rightSearch==-1)
            return -1;
        else if (leftSearch!=-1)
            return leftSearch;
        else 
            return rightSearch;
    }
};