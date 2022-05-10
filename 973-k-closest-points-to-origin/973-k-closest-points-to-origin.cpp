class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        priority_queue<pair<int, int>> maxPQ;
        for (int i = 0 ; i < points.size(); i++) {
            pair<int, int> entry = {squaredDistance(points[i]), i};
            if (maxPQ.size() < k) {
                maxPQ.push(entry);
            } else if (entry.first < maxPQ.top().first) {
                maxPQ.pop();
                maxPQ.push(entry);
            }
        }
        
        vector<vector<int>> answer;
        while (!maxPQ.empty()) {
            int entryIndex = maxPQ.top().second;
            maxPQ.pop();
            answer.push_back(points[entryIndex]);
        }
        return answer;
    }

private:
    int squaredDistance(vector<int>& point) {
        return point[0] * point[0] + point[1] * point[1];
    }
};