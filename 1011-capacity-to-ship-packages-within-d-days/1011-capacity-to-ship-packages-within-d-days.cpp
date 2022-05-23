class Solution {
public:
    
    bool isValid(vector<int>&a, int n,int pages, int k)
    {
        int student=1;
        int sum=0;
        for(int i=0;i<n;i++)
        {
            sum+=a[i];
            if(sum>pages)
            {
                student++;
                sum=a[i];
            }
            
            if(student>k)
                return false;
        }
        return true;
    }
    
    int shipWithinDays(vector<int>&a, int k) {
        int n=a.size();    
        int maximum=INT_MIN;
        int sum=0;
        for(int i=0;i<n;i++)
        {
            maximum=max(maximum,a[i]);
            sum+=a[i];
        }
        
        int l=maximum;
        int h=sum;
        int m;
        int result=-1;
        while(l<=h)
        {
            m=l+(h-l)/2;
            if(isValid(a,n,m,k))
            {
                result=m;
                h=m-1;
            }
            else
                l=m+1;
        }
        return result;
    }
};