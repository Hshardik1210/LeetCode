#include<iostream>
#include<bits/stdc++.h>
#include<conio.h>
using namespace std;
int main()
{
    string s;
    char ch;
    int len,start=0,end=0;

    while((ch=getch())!='\n')
    {
        if(ch==26)
        {
            len=s.length();
            start=len-1;
            int ss1,ss2;
            ss1=start;
            while(s[start]!=' ')
                start--;
            end=start;
            ss2=end;
            for(int i=ss2;i<=ss1;i++)
                printf("\b");
            for(int i=ss2;i<=ss1;i++)
                printf(" ");
            for(int i=ss2;i<=ss1;i++)
                printf("\b");
        }
        else if(ch==25)
        {
            printf(" ");
            len=s.length();
            start=end+1;
            int ss1=start;
            while(start!=len && s[start]!=' ' )
                start++;
            end=start;
            int ss2=end;

            for(int i=ss1;i<=ss2;i++)
                cout<<s[i];
        }
        else if(ch==8){
            printf("\b");
            printf(" ");
            printf("\b");}
        else
        {
            s.push_back((char)(ch));
            end++;
            printf("%c",ch);
        }
    }
}
