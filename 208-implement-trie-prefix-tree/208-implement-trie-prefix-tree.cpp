struct Node
{
    bool isWord;
    unordered_map<char, Node*> children;
    Node()
    {
        isWord = false;
    }
};

class Trie
{
    Node * root;
    public:
        Trie()
        {
            root = new Node();
        }

    void insert(string word)
    {
        Node *itr = root;
        for (int i = 0; i < word.length(); i++)
        {
            if (!itr->children[word[i]])
                itr->children[word[i]] = new Node();
            itr = itr->children[word[i]];
        }
        itr->isWord = true;
    }
    
    Node* searchUtil(string word) {
        Node *itr = root;
        for (int i = 0; i < word.length(); i++)
        {
            if (!itr->children[word[i]])
                return NULL;
            itr = itr->children[word[i]];
        }
        return itr;
    }

    bool search(string word)
    {
        Node *itr = searchUtil(word);
        return itr && itr->isWord;
    }

    bool startsWith(string prefix)
    {
        Node *itr = searchUtil(prefix);
        return itr ? true: false;
    }
};

/**
 *Your Trie object will be instantiated and called as such:
 *Trie* obj = new Trie();
 *obj->insert(word);
 *bool param_2 = obj->search(word);
 *bool param_3 = obj->startsWith(prefix);
 */