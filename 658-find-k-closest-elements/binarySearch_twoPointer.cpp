class Solution
{
    public:
    
    int FloorIndex(vector<int>&arr, int target) {
        int low = 0;
        int high = arr.size()-1;
        int floorIndex = -1;
        while(low<=high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid]<target) {
                floorIndex = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floorIndex;
    }
        vector<int> findClosestElements(vector<int> &arr, int k, int x)
        {
            int n = arr.size();
            int floorIndex = FloorIndex(arr, x);
            int left = floorIndex;
            int right = floorIndex+1;
            vector<int>result;
            while (left>=0 && right<n && result.size()<k) {
                if (abs(arr[left]-x) > abs(arr[right]-x)) {
                    result.push_back(arr[right++]);
                } else {
                    result.push_back(arr[left--]);
                }
            }
            
            while (left>=0 && result.size()<k) {
                result.push_back(arr[left--]);
            }
            
            while (right<n && result.size()<k) {
                result.push_back(arr[right++]);
            }
            
            sort(result.begin(), result.end());
            return result;
        }
};

// O (log(n) + k)