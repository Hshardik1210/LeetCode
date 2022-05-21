class Solution {
public:
    int findMin(vector<int>& a) {
        int n=a.size();
        int l=0,r=n-1,m=0;
        while(l<=r)
        {
            if(a[l]<=a[r]) //if array is not rotated, take case [2,1]
                return a[l];
            
            m=l+(r-l)/2;
            
            if(a[(m-1+n)%n]>a[m] && a[m]<a[(m+1)%n] )
                break;
            
            if(a[m]>=a[l]) //find if left array is sorted because min value will be founded in unsorted array so move to right array
                l=m+1;
            else if(a[r]>=a[m])
                r=m-1;
        }
        return a[m];
        
    }
};