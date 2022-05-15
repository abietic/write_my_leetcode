import java.util.Stack;

/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
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

    private boolean _isMirror(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return left == null;
        }
        if (left.val != right.val) {
            return false;
        }
        return _isMirror(left.right, right.left) && _isMirror(left.left, right.right);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if ((root.left == null || root.right == null) && root.left != root.right) {
            return false;
        }
        return _isMirror(root.left, root.right);
        // Stack<TreeNode> stackLeft = new Stack<>(), stackRight = new Stack<>();
        // stackLeft.push(root.left);
        // stackRight.push(root.right);
        // while (!stackLeft.empty() && !stackRight.empty()) {
        //     TreeNode left = stackLeft.pop(), right = stackRight.pop();
        //     if (left.val != right.val) {
        //         return false;
        //     }
        //     if ((left.right == null || right.left == null) && left.right != right.left) {
        //         return false;
        //     }
        //     if ((left.left == null || right.right == null) && left.left != right.right) {
        //         return false;
        //     }
        //     if (left.right != null) {
        //         stackLeft.push(left.right);
        //         stackRight.push(right.left);
        //     }
        //     if (left.left != null) {
        //         stackLeft.push(left.left);
        //         stackRight.push(right.right);
        //     }
        // }
        // return true;
    }
}
// @lc code=end
