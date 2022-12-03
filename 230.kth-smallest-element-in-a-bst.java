/*
 * @lc app=leetcode id=230 lang=java
 *
 * [230] Kth Smallest Element in a BST
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

    // 直接用中根序遍历应该是最快的
    // 如果树的结构要经常修改,那么可以再建一个记录子树节点量的数一起维护
    public int kthSmallest(TreeNode root, int k) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode left = cur.left;
            if (left != null) {
                while (left.right != null && left.right != cur) {
                    left = left.right;
                }
                if (left.right == cur) {
                    // morris遍历
                    if (k == 1) {
                        return cur.val;
                    }
                    k--;
                    left.right = null;
                    cur = cur.right;
                } else {
                    left.right = cur;
                    cur = cur.left;
                }
            } else {
                // morris遍历
                if (k == 1) {
                    return cur.val;
                }
                k--;
                cur = cur.right;
            }
        }
        return -1;
    }
}
// @lc code=end

