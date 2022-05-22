class Solution {
public:
    char nextGreatestLetter(vector<char>& a, char x) {
        
        int  n=a.size(),l=0,h=n-1;
        char r=a[0];
        while(l<=h) {
            
            int m = l + (h - l) / 2;
            
            if(x==a[m])
                r= a[(m+1)%n];
            
            if(a[m]>x) {
                r=a[m];
                h=m-1;
            }
            else
                l=m+1;
        }
        return r;
    }
};