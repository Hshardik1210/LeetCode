class Solution {
public:
    int minCost(vector<vector<int>>& costs) {
        vector<int>res(3);
        res[0]  =costs[0][0];
        res[1]  =costs[0][1];
        res[2]  =costs[0][2];
        for(int i=1;i<costs.size();i++) {
            int temp1=costs[i][0]+ min(res[1],res[2]);
            int temp2 = costs[i][1]+ min(res[0],res[2]);
            int temp3 = costs[i][2]+ min(res[0],res[1]);
            res[0] = temp1;
            res[1] = temp2;
            res[2] = temp3;
        }
        int minimum = min(res[0],res[1]);
        minimum = min(minimum,res[2]);
        return minimum;
    }
};