const vector<vector<int>> directions = {
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

class Solution {
public:
    bool isSurrounded(vector<vector<int>> &grid, int i, int j) {
        if(i>=0 && i<grid.size() && j>=0 && j<grid[0].size()) {
            if(grid[i][j]==1)
                return true;

            grid[i][j]=1;
            bool result = true;

            bool top   = isSurrounded(grid, i - 1, j);
            bool down  = isSurrounded(grid, i + 1, j);
            bool left  = isSurrounded(grid, i, j - 1);
            bool right = isSurrounded(grid, i, j + 1);
        
            return top && down && left && right;
        }
        return false;
    }

    int closedIsland(vector<vector<int>>& grid) {
        int count = 0;
        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid[0].size(); j++) {
                if(grid[i][j] == 0 && isSurrounded(grid, i, j))
                    count++;
            }
        }


        return count;
    }
};