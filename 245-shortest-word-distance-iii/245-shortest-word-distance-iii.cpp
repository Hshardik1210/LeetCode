class Solution
{
    unordered_map<string, vector < int>> mp;
    public:
        void WordDistance(vector<string> &wordsDict)
        {
            for (int i = 0; i < wordsDict.size(); i++)
            {
                mp[wordsDict[i]].push_back(i);
            }
        }

    int shortestWordDistance(vector<string> &wordsDict, string word1, string word2)
    {
        int minDistance = INT_MAX;
        WordDistance(wordsDict);
        if(word1==word2) {
            for(int i=0;i<mp[word1].size()-1;i++)
                minDistance = min (minDistance, mp[word1][i+1]-mp[word1][i]);
            return minDistance;
        }
        vector<int> wordIndex1 = mp[word1];
        vector<int> wordIndex2 = mp[word2];

        int index1 = 0, index2 = 0;
        while (index1 < wordIndex1.size() && index2 < wordIndex2.size())
        {
            minDistance = min(minDistance, abs(wordIndex1[index1] - wordIndex2[index2]));

            if (wordIndex1[index1] < wordIndex2[index2])
                index1++;
            else
                index2++;
        }

        return minDistance;
    }
};