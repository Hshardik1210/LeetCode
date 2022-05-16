class Solution
{
public:
    vector<int> findAnagrams(string s, string p)
    {
        vector<int> result;
        if (s.length() < p.length())
            return result;

        unordered_map<char, int> m;
        for (char c: p) {
            m[c]++;
        }

        int i = 0;
        int j = 0;
        int k = p.length();
        int count = m.size();
        while (j < s.length())
        {
            if (m.find(s[j]) != m.end())
            {
                m[s[j]]--;
                if (m[s[j]] == 0)
                    count--;
            }

            if (j - i + 1 < k)
                j++;
            else if (j - i + 1 == k)
            {
                if (count == 0)
                    result.push_back(i);
                if (m.find(s[i]) != m.end())
                {
                    m[s[i]]++;
                    if (m[s[i]] == 1)
                        count++;
                }
                i++;
                j++;
            }
        }
        return result;
    }
};