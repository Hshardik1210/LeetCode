struct Trie {
    bool isWord;
    unordered_map<char, Trie*>children;
    Trie() {
        isWord=false;
    }
};

void insert(Trie* &root, string s) {
    if(s.length()==0)
        return;
    Trie* node = root;
    for(int i=0;i<s.length();i++) {
        if(!node->children[s[i]])
            node->children[s[i]] = new Trie();
        node = node->children[s[i]];
    }
    node->isWord=true;
}

bool search(Trie* &root, string s) {
    if(s.length()==0)
        return false;
    Trie* node = root;

    for(int i=0;i<s.length();i++) {
        if(!node->children[s[i]])
            return false;
        node = node->children[s[i]];
    }
    return node && node->isWord;
}

class Solution {
public:
    bool wordBreakUtil(string s, Trie* &root) {
        if(s.length()==0)
            return true;
        for(int i=1;i<=s.length();i++) {
            if(search(root, s.substr(0,i)) && wordBreakUtil(s.substr(i, s.length()-i), root))
                return true;
        }
        return false;
    }
    bool wordBreak(string s, vector<string>& wordDict) {
        Trie* root = new Trie();
        for(int i=0;i<wordDict.size();i++) {
            insert(root, wordDict[i]);
        }
        return wordBreakUtil(s, root);
    }
};