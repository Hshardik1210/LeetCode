/**
 *Definition for a binary tree node.
 *struct TreeNode {
 *  int val;
 *  TreeNode * left;
 *  TreeNode * right;
 *  TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *  TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 *};
 */
class Solution
{
    public:
        vector<vector < int>> levelOrder(TreeNode *root)
        {
            vector<vector < int>> v;
            if (!root)
                return v;
            queue<TreeNode*> q;
            q.push(root);
            while (!q.empty())
            {
                vector<int>levels;
                int count = q.size();
                while (count--)
                {
                    TreeNode *front = q.front();
                    q.pop();
                    levels.push_back(front->val);
                    if (front->left)
                        q.push(front->left);

                    if (front->right)
                        q.push(front->right);
                }
                v.push_back(levels);
            }
            return v;
        }
};