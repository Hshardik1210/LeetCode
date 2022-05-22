class Solution {
public:
    int findCeil(vector<int> &v, int target) {
        int l=0;
        int r = v.size() - 1;
        int ceil = -1;
        while( l <= r)
        {
            int m = l + (r - l) / 2;

            if(v[m] == target)
                return m;

            if(v[m] > target) {
                ceil = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ceil;
    }
};
