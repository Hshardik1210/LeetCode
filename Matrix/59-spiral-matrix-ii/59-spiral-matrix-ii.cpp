class Solution
{
    public:
        vector<vector < int>> generateMatrix(int n)
        {
            vector<vector < int>> v(n, vector<int> (n, 0));
            if (n == 0)
                return v;
            int m = n;
            int r = 0, c = 0, i, j;

            int count = 1;
            while (r < m && c < n)
            {
                for (j = r; j < n; j++)
                    v[r][j] = count++;
                r++;

                for (i = r; i < m; i++)
                    v[i][n - 1] = count++;
                n--;

                if (r < m)
                {
                    for (j = n - 1; j >= c; j--)
                        v[m - 1][j] = count++;
                    m--;
                }
                if (c < n)
                {
                    for (i = m - 1; i >= r; i--)
                        v[i][c] = count++;
                    c++;
                }
            }
            return v;
        }
};