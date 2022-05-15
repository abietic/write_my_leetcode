/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // private static class TreeNode {
    //     int val;
    //     TreeNode left;
    //     TreeNode right;

    //     TreeNode() {
    //     }

    //     TreeNode(int val) {
    //         this.val = val;
    //     }

    //     TreeNode(int val, TreeNode left, TreeNode right) {
    //         this.val = val;
    //         this.left = left;
    //         this.right = right;
    //     }
    // }

    private static int curMax = 0;

    private static int _maxPathSum(TreeNode root) {
        curMax = Math.max(curMax, root.val);
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int leftMax = (root.left == null ? 0 : _maxPathSum(root.left)) + root.val;
        int rightMax = (root.right == null ? 0 : _maxPathSum(root.right)) + root.val;
        // 像二分法求最大子序列和一样，求左最大，右最大和跨界最大中哪个大
        curMax = Math.max(curMax, Math.max(leftMax + rightMax - root.val, Math.max(leftMax, rightMax)));
        // 截断不增长的路径
        return Math.max(root.val, Math.max(leftMax, rightMax));
    }

    public int maxPathSum(TreeNode root) {
        curMax = root.val;
        _maxPathSum(root);
        return curMax;
    }
}
// @lc code=end
