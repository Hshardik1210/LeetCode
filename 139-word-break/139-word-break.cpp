class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        vector<bool> dp;
        for(int i = 0; i < s.length(); i++){
            dp.push_back(true);
        }
        return dfs(s, wordDict, 0, dp);
    }
    
    bool dfs(string s, vector<string> wordDict, int index, vector<bool> &dp){
        if(index >= s.length()){
            return true;
        }
        if(!dp[index]){
            return false;
        }
        for(int i = 0; i < s.length() && index+i < s.length(); i++){
            if(find(wordDict.begin(), wordDict.end(), s.substr(index, i+1)) != wordDict.end()){
                if(dfs(s, wordDict, index+i+1, dp)){
                    return true;
                }
            }
        }
        dp[index] = false;
        return false;
    }
};