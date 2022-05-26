class comparator {
    public:
    bool operator() (pair<int,int>&a, pair<int,int>&b) {
        return b.first>a.first;
    }
};

class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        priority_queue<pair<int, int>, vector<pair<int,int>>, comparator> maxPQ;
        for (int i = 0 ; i < points.size(); i++) {
            maxPQ.push({squaredDistance(points[i]), i});
            if (maxPQ.size() > k)
                maxPQ.pop();
        }
        
        vector<vector<int>> answer;
        while (k--) {
            int entryIndex = maxPQ.top().second;
            answer.push_back(points[entryIndex]);
            maxPQ.pop();
        }
        return answer;
    }

private:
    int squaredDistance(vector<int>& point) {
        return point[0] * point[0] + point[1] * point[1];
    }
};