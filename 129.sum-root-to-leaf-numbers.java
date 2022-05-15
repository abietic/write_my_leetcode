import java.sql.RowId;

/*
 * @lc app=leetcode id=129 lang=java
 *
 * [129] Sum Root to Leaf Numbers
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

    public int sumNumbers(TreeNode root) {
        int sum = 0;
        while (root != null) {
            TreeNode cur = root.left;
            if (cur != null) {
                while (cur.right != null && cur.right != root) {
                    cur = cur.right;
                }
                if (cur.right == null) {
                    cur.right = root;
                    root.left.val += root.val * 10;
                    root = root.left;
                } else {
                    root.val -= cur.val * 10;
                    cur.right = null;
                    if (cur.left == null) {
                        // cur is a leaf
                        sum += cur.val;
                    }
                    if (root.right != null) {
                        root.right.val += root.val * 10;
                    }
                    root = root.right;
                }
            } else {
                if (root.right == null) {
                    sum += root.val;
                } else {
                    root.right.val += root.val * 10;
                }
                root = root.right;
            }
        }
        return sum;
    }
}
// @lc code=end
