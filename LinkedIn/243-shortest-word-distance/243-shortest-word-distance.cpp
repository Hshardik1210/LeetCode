class Solution
{
    public:
        int shortestDistance(vector<string> &wordsDict, string word1, string word2)
        {
            int first = -1;
            int second = -1;
            int minimum = INT_MAX;
            for (int i = 0; i < wordsDict.size(); i++)
            {
                if (wordsDict[i] == word1)
                    first = i;
                else if (wordsDict[i] == word2)
                    second = i;
                if (first != -1 && second != -1)
                    minimum = min(minimum, abs(first - second));
            }
            return minimum;
        }
};