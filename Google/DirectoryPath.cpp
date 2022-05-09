#include<iostream>
#include<map>
#include<vector>

using namespace std;
struct Trie
{
    bool isPath;
    map<string, Trie*> Directory;
    Trie()
    {
        isPath = false;
    }
};

vector<string> split (string path, char delimiter) {
    vector<string> v;
    if(path.length()==0)
        return {};
    for(int i=0;i<path.length();i++) {
        string temp = "";
        while(i<path.length() && path[i]!=delimiter) {
            temp+=path[i++];
        }
        if(temp.length()>0)
            v.push_back(temp);
    }
    return v;
}

class WordDictionary
{
    Trie * dict;

public:
    WordDictionary()
    {
        dict = new Trie();
    }

    void addPath(string path)
    {
        vector<string> strings = split(path, '/');
        Trie *itr = dict;
        for (auto s: strings)
        {
            if (!itr->Directory[s])
                itr->Directory[s] = new Trie();
            itr = itr->Directory[s];
        }
        itr->isPath = true;
    }
    void displayUtil(Trie* &root, int level) {
        if(!root || root->isPath)
            return;
        Trie* node = root;
        for(auto itr=node->Directory.begin();itr!=node->Directory.end();itr++) {
            int tab = level;
            while(tab--) {
                cout<<"\t";
            }
            cout << itr->first << "\n";
            displayUtil(itr->second, level + 1);
        }
    }

    void display() {
        return displayUtil(dict,0);
    }
};

int main () {
    WordDictionary* obj = new WordDictionary();
    obj->addPath("/Users/Dir1/hardik.txt");
    obj->addPath("/Users/Dir2/hardik.txt");
    obj->addPath("/Users/Dir2/insideDir2/hardik.txt");
    obj->addPath("/Users/Dir3/hardik.txt");
    obj->addPath("/Users/Dir3/insideDir3");
    obj->display();
    return 0;
}