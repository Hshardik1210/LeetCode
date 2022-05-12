struct Trie
{
    bool isWord;
    map<char, Trie*> children;

    Trie()
    {
        isWord = false;
    }
};

void insert(string s, Trie* &dict)
{
    if (s.length() == 0)
        return;
    Trie *node = dict;
    for (int i = 0; i < s.length(); i++)
    {
        if (!node->children[s[i]])
            node->children[s[i]] = new Trie();
        node = node->children[s[i]];
    }
    node->isWord = true;
}

class Solution
{
    public:
        void getAllChilds(Trie* &root, vector<string> &words, string word)
        {
            if (words.size() == 3 || !root)
                return;
            Trie *node = root;
            if (node && node->isWord)
            {
                words.push_back(word);
            }

            for (auto itr = node->children.begin(); itr != node->children.end(); itr++)
            {
                getAllChilds(itr->second, words, (word + itr->first));
            }
        }

    vector<string> searchPrefix(string s, Trie* &dict)
    {
        Trie *node = dict;
        vector<string> words;
        for (int i = 0; i < s.length(); i++)
        {
            if (!node->children[s[i]])
                return words;
            node = node->children[s[i]];
        }
        if (node->isWord)
            words.push_back(s);
        for (auto itr = node->children.begin(); itr != node->children.end(); itr++)
        {
            getAllChilds(itr->second, words, s + itr->first);
        }
        return words;
    }

    vector<vector < string>> suggestedProducts(vector<string> &products, string searchWord)
    {
        vector<vector < string>> result;
        if (products.size() == 0)
            return result;
        Trie *dict = new Trie();
        for (int i = 0; i < products.size(); i++)
        {
            if (products[i].length() > 0)
                insert(products[i], dict);
        }

        for (int i = 0; i < searchWord.length(); i++)
        {
            string subString = searchWord.substr(0, i + 1);
            vector<string> words = searchPrefix(subString, dict);
            result.push_back(words);
        }
        return result;
    }
};