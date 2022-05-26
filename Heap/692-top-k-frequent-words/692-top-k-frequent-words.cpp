class comparator
{
    public:
        bool operator()(pair<string, int> &a, pair<string, int> &b)
        {
            return a.second < b.second || (a.second == b.second && a.first > b.first);
        }
};

class Solution
{
    public:
        vector<string> topKFrequent(vector<string> &words, int k)
        {
            unordered_map<string, int> map;
            for (int i = 0; i < words.size(); i++)
            {
                map[words[i]]++;
            }

            priority_queue<pair<string, int>, vector< pair<string, int>>, comparator> pq;
            for (auto it = map.begin(); it != map.end(); it++)
            {
                pq.push({ it->first, it->second });
            }

            vector<string> ans;
            while (k > 0)
            {
                ans.push_back(pq.top().first);
                pq.pop();
                k--;
            }

            return ans;
        }
};