class Solution {
    unordered_set<string>dict;
public:
    bool wordBreakUtil(string s) {
        if(s.length()==0)
            return true;
        for(int i=1;i<=s.length();i++) {
            if(dict.find(s.substr(0,i))!=dict.end() && wordBreakUtil(s.substr(i, s.length()-i)))
                return true;
        }
        return false;
    }
    bool wordBreak(string s, vector<string>& wordDict) {
        for(int i=0;i<wordDict.size();i++) {
            dict.insert(wordDict[i]);
        }
        return wordBreakUtil(s);
    }
};