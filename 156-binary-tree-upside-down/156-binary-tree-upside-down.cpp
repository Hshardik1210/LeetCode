/**
 *Definition for a binary tree node.
 *struct TreeNode {
 *    int val;
 *    TreeNode * left;
 *    TreeNode * right;
 *    TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 *};
 */
class Solution
{
    public:
        TreeNode* upsideDownBinaryTree(TreeNode *root)
        {

            stack<TreeNode*> s;
            while (root)
            {
                s.push(root);
                root = root->left;
            }

            TreeNode *result = NULL;
            while (!s.empty())
            {
                TreeNode *top = s.top();
                s.pop();
                if (!result)
                {
                    result = top;
                }

                if (!s.empty())
                {
                    top->right = s.top();
                    top->left = s.top()->right;
                    s.top()->left = NULL;
                    s.top()->right = NULL;
                }
            }
            return result;
        }
};