const vector<vector<int>> directions = {
        {-1,0},
        {1,0},
        {0,-1},
        {0,1},
        {-1,1},
        {1,-1},
        {1,1},
        {-1,-1},
};

const int allowedDirections = 4;

class Solution {
public:
    void dfs(vector<vector<int>>&grid, int x, int y, int i, int j, vector<pair<int,int>>&v) {
        int r = grid.size();
        int c = grid[0].size();
        if(i>=0 && i<r && j>=0 && j<c && grid[i][j]) {
            v.push_back({i-x,j-y});
            grid[i][j]=0;
            for(int k=0;k<allowedDirections;k++) {
                dfs(grid, x, y, i+directions[k][0], j+directions[k][1], v);
            }
        }
    }

    int numberofDistinctIslands(vector<vector<int>> &grid) {
        int r = grid.size();
        int c = grid[0].size();
        set<vector<pair<int,int>>>result;
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(grid[i][j]) {
                    int x = i;
                    int y = j;
                    vector<pair<int,int>>v;
                    dfs(grid,x,y,i,j,v);
                    result.insert(v);
                }
            }
        }
        return result.size();
    }
};