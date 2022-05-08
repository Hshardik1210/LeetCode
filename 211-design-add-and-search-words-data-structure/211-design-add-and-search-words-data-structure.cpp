struct Trie
{
    bool isWord;
    unordered_map<char, Trie*> Dictionary;
    Trie()
    {
        isWord = false;
    }
};

class WordDictionary
{
    Trie * dict;

public:
    WordDictionary()
    {
        dict = new Trie();
    }

    void addWord(string word)
    {
        Trie *itr = dict;
        for (auto c: word)
        {
            if (!itr->Dictionary[c])
                itr->Dictionary[c] = new Trie();
            itr = itr->Dictionary[c];
        }
        itr->isWord = true;
    }
    bool searchUtil(string word, Trie* &root, int index)
    {
        if(!root)
            return false;
        Trie *node = root;
        for (int i = index; i < word.length(); i++)
        {
            char c = word[i];
            if (c == '.')
            {
                for (auto itr = node->Dictionary.begin(); itr != node->Dictionary.end(); itr++)
                {
                    if (searchUtil(word, itr->second, i + 1))
                        return true;
                }
                return false;
            }
            else
            {
                if(!node->Dictionary[c])
                    return false;
                node = node->Dictionary[c];
            }
        }
        return node && node->isWord;
    }
    bool search(string word)
    {
        return searchUtil(word, dict, 0);
    }
};
