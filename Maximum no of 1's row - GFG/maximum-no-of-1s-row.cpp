// { Driver Code Starts
#include <bits/stdc++.h>
using namespace std;


 // } Driver Code Ends
class Solution
{
    public:
        int maxOnes (vector <vector <int>> &matrix, int r, int c)
        {
            int i = 0;
            int j = matrix[0].size()-1;
            int result = -1;
            while (i>=0 && i<matrix.size() && j>=0 && j<matrix[0].size()) {
                int J = j;
                while(matrix[i][j] == 1)
                j--;
                if(j<J)
                    result = i;
                i++;
            }
            return result;
        }
};

// { Driver Code Starts.

int main(){
    int t; cin >> t;
    while (t--){
        int n, m; cin >> n >> m;
        vector <vector <int>> arr (n, vector <int> (m));
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                cin >> arr[i][j];
        Solution ob;        
        cout << ob.maxOnes(arr, n, m) << endl;
    }
}  // } Driver Code Ends