class Solution {
    unordered_set<string>S;
public:
    bool wordBreakUtil(string s) {
        if(s.length()==0)
            return true;
        for(int i=1;i<=s.length();i++) {
            string temp1 = s.substr(0,i);
            string temp2 = s.substr(i, s.length()-i);
            if(S.find(s.substr(0,i))!=S.end() && wordBreakUtil(s.substr(i, s.length()-i)))
                return true;
        }
        return false;
    }
    bool wordBreak(string s, vector<string>& wordDict) {
        for(int i=0;i<wordDict.size();i++) {
            S.insert(wordDict[i]);
        }
        return wordBreakUtil(s);
    }
};