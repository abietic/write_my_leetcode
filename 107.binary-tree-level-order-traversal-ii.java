import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=107 lang=java
 *
 * [107] Binary Tree Level Order Traversal II
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
    //  private static class TreeNode {
    //          int val;
    //          TreeNode left;
    //          TreeNode right;
    //          TreeNode() {}
    //          TreeNode(int val) { this.val = val; }
    //          TreeNode(int val, TreeNode left, TreeNode right) {
    //              this.val = val;
    //              this.left = left;
    //              this.right = right;
    //          }
    //      }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>(), flipQueue = new ArrayDeque<>(), tmp;
        queue.addLast(root);
        List<Integer> curLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            curLevel.add(cur.val);
            if (cur.left != null) {
                flipQueue.addLast(cur.left);
            }
            if (cur.right != null) {
                flipQueue.addLast(cur.right);
            }
            if (queue.isEmpty()) {
                res.add(0, curLevel);
                curLevel = new ArrayList<>();
                tmp = flipQueue;
                flipQueue = queue;
                queue = tmp;
            }
        }
        return res;
    }
}
// @lc code=end

