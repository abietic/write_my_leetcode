import java.util.Stack;

/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // if (p == null) {
        //     return q == null;
        // }
        // if (q == null) {
        //     return p == null;
        // }
        // if (p.val != q.val) {
        //     return false;
        // }
        // return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return p == null;
        }
        Stack<TreeNode> stackP = new Stack<>(), stackQ = new Stack<>();
        stackP.push(p);
        stackQ.push(q);
        while (!stackP.empty() && !stackQ.empty()) {
            TreeNode curP = stackP.pop(), curQ = stackQ.pop();
            if (curP.val != curQ.val) {
                return false;
            }
            if ((curP.left == null || curQ.left == null) && curP.left != curQ.left) {
                return false;
            }
            if ((curP.right == null || curQ.right == null) && curP.right != curQ.right) {
                return false;
            }
            if (curP.left != null) {
                stackP.push(curP.left);
                stackQ.push(curQ.left);
            }
            if (curP.right != null) {
                stackP.push(curP.right);
                stackQ.push(curQ.right);
            }
        }
        if (!stackP.empty() || !stackQ.empty()) {
            return false;
        }
        return true;
    }
}
// @lc code=end
