class comparator {
public:
    bool operator()(pair<int,int>&a, pair<int,int>&b) {
        if(a.first == b.first)
            return a.second<b.second;
        return a.first<b.first;
    }
};

class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        priority_queue<pair<int, int>, vector< pair<int, int>>, comparator> pq;
        for(int i=0;i<k;i++) {
            pq.push({abs(arr[i]-x), arr[i]});
        }
        
        for(int i=k;i<arr.size();i++) {
            if(abs(arr[i]-x)<pq.top().first) {
                pq.pop();
                 pq.push({abs(arr[i]-x),arr[i]});   
            }
        }
        
        vector<int>result;
        while(k--) {
            result.push_back(pq.top().second);
            pq.pop();
        }
        sort(result.begin(),result.end());
        return result;
    }
};