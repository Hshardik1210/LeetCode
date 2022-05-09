class Solution {
    unordered_set<string>dict;
    vector<string>result;
public:
    void wordBreakUtil(string s, string current) {
        if(s.length()==0) {
            current.pop_back(); // removing the last space
            result.push_back(current);
            return;
        }
        
        for(int i=1;i<=s.length();i++) {
            string subString = s.substr(0,i);
            if(dict.find(subString)!=dict.end()) {
                string temp = current + subString + " ";
                wordBreakUtil(s.substr(i,s.length()-i), temp);
            }
        }
    }
    vector<string> wordBreak(string s, vector<string>& wordDict) {
        for(int i=0;i<wordDict.size();i++) {
            dict.insert(wordDict[i]);
        }
        wordBreakUtil(s, "");
        return result;
    }
};