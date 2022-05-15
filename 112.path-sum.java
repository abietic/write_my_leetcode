/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
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
    // int val;
    // TreeNode left;
    // TreeNode right;

    // TreeNode() {
    // }

    // TreeNode(int val) {
    // this.val = val;
    // }

    // TreeNode(int val, TreeNode left, TreeNode right) {
    // this.val = val;
    // this.left = left;
    // this.right = right;
    // }
    // }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        TreeNode cur = null;
        // 常数额外空间，需要改变树节点的值
        while (root != null) {
            cur = root.left;
            if (cur != null) {
                while (cur.right != null && cur.right != root) {
                    cur = cur.right;
                }
                if (cur.right == null) {
                    cur.right = root;
                    root.left.val += root.val;
                    root = root.left;
                } else {
                    // 因为这时是从下方节点返回的，在这里判断是否为叶节点和是否符合路径和
                    root.val -= cur.val;
                    cur.right = null;
                    if (cur.left == null && cur.val == targetSum) {
                        //
                        return true;
                    }
                    if (root.right != null) {
                        root.right.val += root.val;
                    }
                    root = root.right;
                }
            } else {
                // 向右子走暂时无法判断当前节点是叶节点
                if (root.right != null) {
                    // 这里可能会导致底部节点向上方节点加值
                    root.right.val += root.val;
                } else if (root.val == targetSum) {
                    // 如果进入这个，说明是最后一个叶节点
                    return true;
                }
                root = root.right;
            }
        }
        return false;
    }
}
// @lc code=end
