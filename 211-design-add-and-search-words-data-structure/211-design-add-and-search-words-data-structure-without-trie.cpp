class WordDictionary {
      vector<vector<string>>v;
public:   
    
    WordDictionary() : v(100002) {}

    void addWord(string word) {
        v[word.size()].push_back(word);
    }

    bool search(string word) {
        int n = word.size();
        for(int i=0;i<v[n].size();i++){
            int c=0;
            for(int j=0;j<n;j++){
                if(v[n][i][j]==word[j] || word[j]=='.')
                    c++;
                else
                    break;
            }
            if(c==n)
            {
                return 1;
            }
        }
        return 0;
    }
};

