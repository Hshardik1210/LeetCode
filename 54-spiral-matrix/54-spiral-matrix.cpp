class Solution
{
    public:
        vector<int> spiralOrder(vector<vector < int>> &a)
        {
            int r = 0, c = 0, m, n, i, j;
            vector<int> v;
            v.clear();
            m = a.size();
            if (m == 0)
                return v;
            n = a[0].size();
            while (r < m && c < n)
            {
                for (j = r; j < n; j++)
                    v.push_back(a[r][j]);
                r++;

                for (i = r; i < m; i++)
                    v.push_back(a[i][n - 1]);
                n--;

                if (r < m)
                {
                    for (j = n - 1; j >= c; j--)
                        v.push_back(a[m - 1][j]);
                    m--;
                }
                if (c < n)
                {
                    for (i = m - 1; i >= r; i--)
                        v.push_back(a[i][c]);
                    c++;
                }
            }
            return v;
        }
};