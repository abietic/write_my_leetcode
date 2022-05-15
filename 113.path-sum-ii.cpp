/*
 * @lc app=leetcode id=113 lang=cpp
 *
 * [113] Path Sum II
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

using namespace std;
#include <vector>

// struct TreeNode
// {
//     int val;
//     TreeNode *left;
//     TreeNode *right;
//     TreeNode() : val(0), left(nullptr), right(nullptr) {}
//     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
//     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
// };

class Solution
{
public:
    vector<vector<int>> pathSum(TreeNode *root, int targetSum)
    {
        auto res = vector<vector<int>>();
        if (root)
        {
            is_target(root, 0, targetSum, {}, res);
        }
        return res;
    }

private:
    void is_target(TreeNode *sub_rt, int prev_sum, int target, vector<int> prev_path_memo, vector<vector<int>> &ress)
    {
        int cur_sum = prev_sum + sub_rt->val;                    // current path sum
        prev_path_memo.push_back(sub_rt->val);                   // current path
        if (sub_rt->left == nullptr && sub_rt->right == nullptr) // is leaf
        {
            if (cur_sum == target)
            {
                ress.push_back(prev_path_memo);
            }
            return;
        }
        if (sub_rt->left)
        {
            is_target(sub_rt->left, cur_sum, target, prev_path_memo, ress);
        }
        if (sub_rt->right)
        {
            is_target(sub_rt->right, cur_sum, target, prev_path_memo, ress);
        }
    }
};
// @lc code=end
