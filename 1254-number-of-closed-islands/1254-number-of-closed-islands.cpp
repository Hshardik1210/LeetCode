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

            for(int k=0;k<allowedDirections;k++) {
                result = isSurrounded(grid, i+directions[k][0], j+directions[k][1]) && result;
            }
            return result;
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