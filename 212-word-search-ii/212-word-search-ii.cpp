struct Trie
{
    bool isWord;
    unordered_map<char, Trie*> children;
    string word;
    Trie()
    {
        isWord = false;
        word = "";
    }
};

void insert(string word, Trie* &root)
{
    if (word.length() == 0)
        return;
    Trie *node = root;
    for (int i = 0; i < word.size(); i++)
    {
        if (!node->children[word[i]])
            node->children[word[i]] = new Trie();
        node = node->children[word[i]];
    }
    node->isWord = true;
    node->word = word;
}

void findWordsUtil(vector<vector < char>> &board, int i, int j, Trie* &root, unordered_set< string > &result)
{
    int r = board.size();
    int c = board[0].size();
    if (i >= 0 && i < r && j >= 0 && j < c && board[i][j] != '#')
    {
        Trie *node = root->children[board[i][j]];
        if (node)
        {
            if (node->isWord)
                result.insert(node->word);
            
            char temp = board[i][j];
            board[i][j] = '#';
            findWordsUtil(board, i + 1, j, node, result);
            findWordsUtil(board, i, j + 1, node, result);
            findWordsUtil(board, i - 1, j, node, result);
            findWordsUtil(board, i, j - 1, node, result);
            board[i][j] = temp;
        }
    }
}

class Solution
{
    Trie * dict;
    public:
        vector<string> findWords(vector<vector < char>> &board, vector< string > &words)
        {
            dict = new Trie();
            for (int i = 0; i < words.size(); i++)
            {
                insert(words[i], dict);
            }
            unordered_set<string> result;
            for (int i = 0; i < board.size(); i++)
            {
                for (int j = 0; j < board[0].size(); j++)
                {
                    findWordsUtil(board, i, j, dict, result);
                }
            }
            vector<string>v(result.begin(),result.end());
            return v;
        }
};