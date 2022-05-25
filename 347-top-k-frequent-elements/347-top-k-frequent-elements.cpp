class comparator {
    public:
    bool operator()(pair<int,int>&a, pair<int,int>&b) {
        return b.second<a.second;
    }
};

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        
        unordered_map<int,int>m;
        
        for(int i=0;i<nums.size();i++) {
            m[nums[i]]++;
        }
        
        priority_queue<pair<int,int>,vector<pair<int,int>>, comparator>pq;
        
        for(auto itr = m.begin();itr!=m.end();itr++) {
            pq.push({itr->first, itr->second});
            if(pq.size()>k)
                pq.pop();
        }
        
        vector<int>result;
        while(k--) {
            result.push_back(pq.top().first);
            pq.pop();
        }
        return result;
    }
};