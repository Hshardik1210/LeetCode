class Solution
{
    public:
        int minCostII(vector<vector < int>> &houses)
        {
            int n = houses.size();
            int k = houses[0].size();
            for (int i = 1; i < n; i++)
            {
                            int prevMin1Index = -1;
            int prevMin2Index = -1;
                for (int j = 0; j < k; j++)
                {
                    if (prevMin1Index == -1 || houses[i - 1][j] < houses[i - 1][prevMin1Index])
                    {
                        prevMin2Index = prevMin1Index;
                        prevMin1Index = j;
                    }
                    else if (prevMin2Index == -1 || houses[i - 1][j] < houses[i - 1][prevMin2Index])
                        prevMin2Index = j;
                }

                for (int j = 0; j < houses[0].size(); j++)
                {
                    if (j == prevMin1Index)
                        houses[i][j] = houses[i][j] + houses[i - 1][prevMin2Index];
                    else
                        houses[i][j] = houses[i][j] + houses[i - 1][prevMin1Index];
                }
            }
            
        int minimum = INT_MAX;
        for (int c : houses[n - 1]) {
            minimum = min(minimum, c);
        }
            return minimum;
        }
};