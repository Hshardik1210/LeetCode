class Solution {
public:
    int binarySearch(vector<int>& a, int ele, bool leftbias)
    {
        int n=a.size();
        int low=0,high=n-1;
        int index=-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            
            if(a[mid]<ele)
                low = mid + 1;
            else if(a[mid]>ele)
                high = mid - 1;
            else
            {
                index=mid;
                if(leftbias)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
        }
       return index; 
    }
    
    vector<int> searchRange(vector<int>& a, int target) {
        
        int firstoccurence=binarySearch(a,target,true);
        int lastoccurence=binarySearch(a,target,false);
        
        return {firstoccurence,lastoccurence};
        
    }
};