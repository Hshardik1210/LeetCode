class Solution
{
    public:
        bool searchMatrix(vector<vector < int>> &a, int ele)
        {
            int r = a.size();
            int c = a[0].size();

            int low = 0;
            int high = (r *c) - 1;
            while (low <= high)
            {
                int mid = low + (high - low) / 2;

                if (a[mid / c][mid % c] == ele)
                    return true;
                if (a[mid / c][mid % c] > ele)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return false;
        }
};

// TC : O (log(R+C))