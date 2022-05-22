class Solution {
public:
    int findFloor(vector<int> &v, int target) {
        int l=0;
        int r = v.size() - 1;
        int floor = -1;
        while( l <= r)
        {
            int m = l + (r - l) / 2;

            if(v[m] == target)
                return m;

            if(v[m] < target) {
                floor = m;
                l = m + 1;
            }
            else
                r = m - 1;
        }
        return floor;
    }
};
