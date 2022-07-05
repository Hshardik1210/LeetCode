class Solution
{
    public:
        int minCostII(vector<vector < int>> &costs)
        {
            if (costs.size() == 0) return 0;
            int k = costs[0].size();
            int n = costs.size();

            for (int house = 1; house < n; house++)
            {

               	// Find the minimum and second minimum color in the PREVIOUS row.
                int minColorIndex = -1;
                int secondMinColorIndex = -1;
                for (int color = 0; color < k; color++)
                {
                    int cost = costs[house - 1][color];
                    if (minColorIndex == -1 || cost < costs[house - 1][minColorIndex])
                    {
                        secondMinColorIndex = minColorIndex;
                        minColorIndex = color;
                    }
                    else if (secondMinColorIndex == -1 || cost < costs[house - 1][secondMinColorIndex])
                    {
                        secondMinColorIndex = color;
                    }
                }

               	// And now calculate the new costs for the current row.
                for (int color = 0; color < k; color++)
                {
                    if (color == minColorIndex)
                    {
                        costs[house][color] += costs[house - 1][secondMinColorIndex];
                    }
                    else
                    {
                        costs[house][color] += costs[house - 1][minColorIndex];
                    }
                }
            }

            int minimum = INT_MAX;
            for (int c: costs[n - 1])
            {
                minimum = min(minimum, c);
            }
            return minimum;
        }
};