const vector<vector<int>>directions = {
    {-1,0},
    {0,1},
    {1,0},
    {0,-1},
    {-1,1},
    {1,1},
    {1,-1},
    {-1,-1}
};
const int dirAllowed = 4;

class Solution {
public:
    
    int BFS(vector<vector<int>>&v,queue<pair<int,int> >&q)
    {
        int r=v.size();
        int c=v[0].size();
        int count=0; 
        while(!q.empty())
        {
            int size=q.size();
            while(size--) {
                int i=q.front().first;
                int j=q.front().second;
                q.pop();
                
                if(v[i][j]==2)
                {
                    for(int k=0;k<dirAllowed;k++)
                    {
                        int I = i+directions[k][0];
                        int J = j+directions[k][1];
                        if(I>=0 && I<r && J>=0 && J<c)
                        {
                            if(v[I][J]==1)
                            {
                                v[I][J]=2;
                                q.push({I,J});
                            }
                        }
                    }
                }
            }
            if(!q.empty())
            count++;
        }
        return count;
    }
    int orangesRotting(vector<vector<int>>& v) {
        int r=v.size();
        int c=v[0].size();
        queue<pair<int,int> >q;
        
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(v[i][j]==2)
                    q.push({i,j});
            }
        }
        int count = BFS(v,q);
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                if(v[i][j]==1)
                    return -1;
        
        return count;   
    }
};