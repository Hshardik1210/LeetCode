struct Trie {
    bool isWord;
    unordered_map<char, Trie*>children;
    int count;
    
    Trie() {
        isWord = false;
        count=0;
    }
};

void Insert(string s, Trie* &dict) {
    if(s.length()==0)
        return;
    Trie* node= dict;
    for(int i=0;i<s.length();i++) {
        if(!node->children[s[i]]) {
            node->children[s[i]]= new Trie();
        }
        node->children[s[i]]->count+=1;
        node=node->children[s[i]];
    }
    node->isWord=true;
}

string search(string s, Trie* &dict, int n) {
    if(s.length()==0)
        return "";
    Trie* node = dict;
    int i=0;
    string result ="";
    while(i<s.length() && node->children[s[i]]->count==n) {
        node = node->children[s[i]];
        result+=s[i++];
    }
    return result;
}


class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if(strs.size()==0)
            return "";
        string shortestString = strs[0];
        Trie * dict = new Trie();
        for(int i=0;i<strs.size();i++) {
            if(strs[i].length()==0)
                return "";
            if(shortestString.length()>strs[i].length())
                shortestString = strs[i];
            
                Insert(strs[i], dict);
        }
        return search(shortestString, dict, strs.size());
        
    }
};