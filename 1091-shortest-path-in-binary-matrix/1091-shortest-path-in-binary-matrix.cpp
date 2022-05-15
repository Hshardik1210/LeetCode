const vector<vector<int>>dir = {
                        {0,1},
                        {1,1},
                        {1,0},
                        {1,-1},
                        {0,-1},
                        {-1,-1},
                        {-1,0},
                        {-1,1}
                    };
const int directionsAllowed = 8;

class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        int r=grid.size();
        int c=grid[0].size();
        if(grid[0][0] || grid[r-1][c-1])
                return -1;
        
        queue<pair<int,int>>q;
        q.push({0,0});
        grid[0][0]=1;
        
        int count=1;
        while(!q.empty())
        {
            int size=q.size();
            while(size--)
            {
                int i=q.front().first;
                int j=q.front().second;
                q.pop();
                
                if(i==r-1 && j==c-1)
                    return count;
                
                for(int k=0;k<directionsAllowed;k++)
                {
                   int I=i+dir[k][0];
                   int J=j+dir[k][1];
                    
                   if(I>=0 && I<r && J>=0 && J<c && !grid[I][J]) {
                        q.push({I,J});
                        grid[I][J]=1;
                   }
                }
            }
            count++;
        }
        return -1;
    }
};