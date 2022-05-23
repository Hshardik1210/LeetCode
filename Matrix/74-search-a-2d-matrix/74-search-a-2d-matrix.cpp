class Solution
{
    public:
        bool searchMatrix(vector<vector < int>> &a, int ele)
        {
            int r = a.size();
            int c = a[0].size();

            int i = 0;
            int j = c - 1;
            while (i >= 0 && i < r && j >= 0 && j < c)
            {
                if (a[i][j] == ele)
                    return true;

                if (a[i][j] > ele)
                    j--;
                else
                    i++;
            }
            return false;
        }
};