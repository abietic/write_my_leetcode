import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=102 lang=java
 *
 * [102] Binary Tree Level Order Traversal
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>(), flipDeque = new ArrayDeque<>(), tmp;
        List<Integer> curLevel = new ArrayList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode cur = deque.pollFirst();
            if (cur.left != null) {
                flipDeque.addLast(cur.left);
            }
            if (cur.right != null) {
                flipDeque.addLast(cur.right);
            }
            curLevel.add(cur.val);
            if (deque.isEmpty()) {
                tmp = deque;
                deque = flipDeque;
                flipDeque = tmp;
                res.add(curLevel);
                curLevel = new ArrayList<>();
            }
        }
        return res;
    }
}
// @lc code=end
