class WordDistance
{
    unordered_map<string, vector < int>> mp;
    public:
        WordDistance(vector<string> &wordsDict)
        {
            for (int i = 0; i < wordsDict.size(); i++)
            {
                mp[wordsDict[i]].push_back(i);
            }
        }

    int shortest(string word1, string word2)
    {
        vector<int> wordIndex1 = mp[word1];
        vector<int> wordIndex2 = mp[word2];

        int index1 = 0, index2 = 0, minDistance = INT_MAX;
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