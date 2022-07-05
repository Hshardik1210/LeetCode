class Solution {
public:
    bool isValid(string str) {
        if(str[0]==')' || str[0]==']' ||str[0]=='}')
        return false;

    stack<char>s;
    s.push(str[0]);

    for(int i=1;i<str.length();i++)
    {
        if(str[i]=='(' || str[i]=='[' ||str[i]=='{')
            s.push(str[i]);
        else if(!s.empty() && ((s.top()=='(' && str[i]!=')')||(s.top()=='[' && str[i]!=']')||(s.top()=='{' && str[i]!='}')))
            return false;
        else if(!s.empty())
                s.pop();
        else
                return false;
    }
    if(!s.empty())
        return false;

    return true;
    }
};