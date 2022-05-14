const vector<vector < int>> directions = {
	{ -1, 0 },	//UP
    { 0, 1 },	//RIGHT
    { 1, 0 },	//DOWN
    { 0, -1 },	//LEFT
    { -1, -1 },	//LEFT UP
    { -1, 1 },	//RIGHT UP
    { 1, 1 },	//RIGHT DOWN
    { 1, -1 },	//LEFT DOWN
};

const int allowedDirections = 4;

class Solution
{
    public:

    void dfs(vector<vector < char>> &grid, int i, int j)
    {
        int r = grid.size();
        int c = grid[0].size();
        if (i >= 0 && i < r && j >= 0 && j < c && grid[i][j] == '1')
        {
            grid[i][j] = '0';
            for (int k = 0; k < allowedDirections; k++) {
                dfs(grid, i + directions[k][0], j + directions[k][1]);
            }
        }
    }

    void bfs(vector<vector < char>> &grid, int x, int y)
    {
        int r = grid.size();
        int c = grid[0].size();

        queue<pair<int, int>> q;
        q.push({ x, y });
        while (!q.empty())
        {
            int i = q.front().first;
            int j = q.front().second;
            q.pop();
            if (i >= 0 && i < r && j >= 0 && j < c && grid[i][j] == '1')
            {
                grid[i][j] = '0';
                for (int k = 0; k < allowedDirections; k++) {
                    q.push({ i + directions[k][0], j + directions[k][1] });
                }
            }
        }
    }

    int numIslands(vector<vector < char>> &grid)
    {
        int r = grid.size();
        int c = grid[0].size();
        int count = 0;
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if (grid[i][j] == '1')
                {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
};