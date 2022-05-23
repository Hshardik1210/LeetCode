class Solution
{
public:
    int maxOnes (vector <vector <int>> &matrix, int r, int c)
    {
        int i = 0;
        int j = matrix[0].size()-1;
        int result = -1;
        while (i>=0 && i<matrix.size() && j>=0 && j<matrix[0].size()) {
            int prevj = j;
            while(matrix[i][j] == 1)
                j--;
            if(j<prevj)
                result = i;
            i++;
        }
        return result;
    }
};