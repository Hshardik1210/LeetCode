class Solution
{
    public:
        vector<string> fullJustify(vector<string> &words, int maxWidth)
        {
            int n = words.size();
            vector<string> res;

            int i = 0;
            while (i < n)
            {
                int currLineLength = words[i].length();
                int j = i + 1;
                while (j < n && currLineLength + words[j].length() + 1 <= maxWidth)
                    currLineLength += (words[j++].length() + 1);

                int gaps = j - i - 1;
                int spacesToAdd = maxWidth - currLineLength;
                int evenlyDivideSpaces = gaps > 0 ? spacesToAdd / gaps : 0;
                int extraSpaces = gaps > 0 ? spacesToAdd % gaps : 0;
                if (j == n)
                {
                    evenlyDivideSpaces = 0;
                    extraSpaces = 0;
                }
                int k = i;
                string temp = "";
                while (k < n && temp.length() + words[k].length() <= maxWidth)
                {
                    temp += words[k] + ' ';
                    k++;

                    int s = evenlyDivideSpaces;
                    while (s > 0 && temp.length() <= maxWidth)
                    {
                        temp += ' ';
                        s--;
                    }

                    if (extraSpaces > 0)
                    {
                        temp += ' ';
                        extraSpaces--;
                    }
                }
                if (temp.length() > maxWidth)
                    temp.pop_back();
                while (temp.length() < maxWidth)
                    temp += ' ';
                res.push_back(temp);
                i = j;
            }

            return res;
        }
};