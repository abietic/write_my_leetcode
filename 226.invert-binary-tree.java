/*
 * @lc app=leetcode id=226 lang=java
 *
 * [226] Invert Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // // 递归实现十分简单直观，那迭代的话怎么做呢
    // public TreeNode invertTree(TreeNode root) {
    //     if (root == null) {
    //         return null;
    //     }
    //     TreeNode left = invertTree(root.right), right = invertTree(root.left);
    //     root.left = left;
    //     root.right = right;
    //     return root;
    // }

    public TreeNode invertTree(TreeNode root) {
    }
}
// @lc code=end

