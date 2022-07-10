#include<bits/stdc++.h>
using namespace std;

struct Node
{
    Node *children[26];
    bool eof;
};
Node* getnode()
{
    Node *temp=new Node;
    for(int i=0;i<26;i++)
        temp->children[i]=NULL;
    temp->eof=false;
}
bool isLeafNode(Node* root)
{
    return root->eof != false;
}

void insert(string s,Node *root)
{
    int n=s.length();
    for(int i=0;i<n;i++)
    {
        int index=s[i]-'a';
        if(!root->children[index])
            root->children[index]=getnode();
        root=root->children[index];
    }
    root->eof=true;
}

void display(Node* root, char str[], int level,string &suggestion)
{
    if (isLeafNode(root))
    {
        str[level] = '\0';
        cout <<suggestion<< str << endl; //for autosuggestion
    }
    for (int i = 0; i < 26; i++)
    {
        if (root->children[i])
        {
            str[level] = i + 'a';
            display(root->children[i], str, level + 1,suggestion);
        }
    }
}

int search1(string s,Node *root,int index,Node* &last)
{
    if(root==NULL)
        return 0;
    int n=s.length();

    for(int i=0;i<n;i++)
    {
        if(i==index) //only used for edit distance delete
            continue;
        int indexs=s[i]-'a';
        if(!root->children[indexs])
            return 0;
        root=root->children[indexs];
    }
    last=root;//last only used for auto suggestion
    return 1;
}

int search(string s,Node *root)
{
    if(root==NULL)
        return 0;
    int n=s.length();
    for(int i=0;i<n;i++)
    {
        int index=s[i]-'a';
        if(!root->children[index])
            return 0;
        root=root->children[index];
    }
    return root->eof;
}

void Delete(string s,Node* root)
{
    int n=s.length();
    char ch;
    for(int i=0;i<n;i++)
    {
        Node *last=NULL;//useles here
        int ans=search1(s,root,i,last);
        if(ans)
        {
            for(int j=0;j<n;j++)
            {
                if(j==i)
                    continue;
                cout<<s[j];
            }
            cout<<endl;
        }
    }
}

string* canbereplacedby(Node *root)
{
    if(root==NULL)
        return NULL;
    string s;
    for(int i=0;i<26;i++)
    {
        if(root->children[i]!=NULL)
            s.push_back(i+'a');
    }
    return new string(s);
}

void replace(string s,Node* root)
{
    int n=s.length();
    Node *traverse=root;
    for(int i=0;i<n;i++)
    {
        string *s1=canbereplacedby(traverse);
        if(s1)
        {
            string s2(*s1);
        delete s1;
        int N=s2.size();
        char ch=s[i];

        for(int j=0;j<N;j++)
        {
            s[i]=s2[j];
            int ans=search(s,root);
            if(ans)
                cout<<s<<endl;
        }
        s[i]=ch;
        traverse=traverse->children[s[i]-'a'];
        }
    }
}

void insertone(string s,Node *root)
{
    Node *traverse=root;
    string::iterator it;
    for(it=s.begin();it!=s.end();it++)
    {
        string *s1=canbereplacedby(traverse);
        if(s1)
        {
            string s2(*s1);
            delete s1;
            int N=s2.size();
            for(int i=0;i<N;i++)
            {
            it = s.insert(it,s2[i]);
            int ans=search(s,root);
            if(ans)
                cout<<s<<endl;
            it = s.erase(it);
            }
            traverse=traverse->children[(*it)-'a'];
        }
    }
}

void editdistance(string s,Node* root)
{
    Delete(s,root);
    replace(s,root);
    insertone(s,root);
}

int main()
{
    Node *root=getnode();
    string strings[12]={"apple","file","edition","edit","view","search","project","build","debug","plugins","settings","help"};
    for(int i=0;i<12;i++)
        insert(strings[i],root);
   /*while(1) //auto suggestion
    {
         char s[20];
        string suggestion;
        cin>>suggestion;//input 1st char as '0' for display all content of trie
        if(suggestion[0]=='0')
        {
            suggestion.clear();
            display(root,s,0,suggestion);
        }
        else
        {
            Node *last=NULL;
            search1(suggestion,root,-10,last);
            if(k)
            display(last,s,0,suggestion);
        }
    }*/

    while(1) //autocorrection
    {
        string correction;
        cin>>correction;
        editdistance(correction,root);
    }
}
