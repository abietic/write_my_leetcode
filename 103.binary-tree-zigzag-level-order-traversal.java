import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>(), flipStack = new Stack<>(), tmp;
        stack.push(root);
        boolean flip = false;
        List<Integer> curLevel = new ArrayList<>();
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            curLevel.add(cur.val);
            if (flip) {
                if (cur.right != null) {
                    flipStack.push(cur.right);
                }
                if (cur.left != null) {
                    flipStack.push(cur.left);
                }
            } else {
                if (cur.left != null) {
                    flipStack.push(cur.left);
                }
                if (cur.right != null) {
                    flipStack.push(cur.right);
                }
            }
            if (stack.empty()) {
                res.add(curLevel);
                curLevel = new ArrayList<>();
                tmp = stack;
                stack = flipStack;
                flipStack = tmp;
                flip = !flip;
            }
        }
        return res;
    }
}
// @lc code=end
