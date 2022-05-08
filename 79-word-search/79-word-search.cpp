class Solution {
public:
    
    bool dfs (vector<vector<char>>& board, string word, int i, int j, int index) {
        if(index == word.length())
            return true;
        if (i>=0 && i<board.size() && j>=0 && j<board[0].size()) 
        {
            if(board[i][j]==word[index]) 
            {
                char temp = board[i][j];
                board[i][j] = ' ';
                bool right = dfs(board, word, i, j+1, index+1);
                bool down = dfs(board, word, i+1, j, index+1);
                bool left = dfs(board, word, i, j-1, index+1);
                bool up = dfs(board, word, i-1, j, index+1);
                board[i][j] = temp;
                return up || left || right || down;
            }
            
        }
        return false;
    }
    
    bool exist(vector<vector<char>>& board, string word) {
        
        for(int i=0;i<board.size();i++) 
        {
            for(int j=0;j<board[0].size();j++) 
            {
                if(board[i][j]==word[0] && dfs (board, word,i,j,0))
                    return true;
            }
        }
        return false;
    }
};