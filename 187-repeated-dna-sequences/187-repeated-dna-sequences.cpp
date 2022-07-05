class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        vector<string>res;
        if(s.length()<10)
            return res;
        unordered_map<string,int>m;
        string temp = s.substr(0,10);
        m[temp]++;
        for(int i=10;i<s.length();i++) {
            temp = temp.substr(1,9) + s[i];
            m[temp]++;
        }
        
        for(auto it=m.begin();it!=m.end();it++) {
            if(it->second>1)
                res.push_back(it->first);
        }
        return res;
    }
};