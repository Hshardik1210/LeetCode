class Solution
{
    public:
        int findPeakElement(vector<int>&a) {
        
        int n=a.size();
        if(n==1)
            return 0;
        int l=0;
        int h=n-1;
        while(l<=h)
        {
            int m=l+(h-l)/2;
            if(m>0 && m<n-1)
            {
                if(a[m]>a[m-1] && a[m]>a[m+1])
                    return m;
                else if(a[m]<a[m+1])
                    l=m+1;
                else
                    h=m-1;
            }
            else
            {
                if(m==0 && a[m]<a[m+1])
                    return m+1;
                else if(m==n-1 && a[m]<a[m-1])
                    return m-1;
                else
                    return m;
            }
        }
        return 0;
    }
};