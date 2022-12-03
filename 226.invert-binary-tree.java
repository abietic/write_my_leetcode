import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.tree.TreeNode;

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
        // 使用树遍历即可完成
        // 并且每个节点只使用1次，做一次左右子互换
        // 这里选用层次遍历
        if (root == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst(), tmp;
            if (cur.left != null) {
                queue.addLast(cur.left);
            }
            if (cur.right != null) {
                queue.addLast(cur.right);
            }
            tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
        }
        return root;
    }
}
// @lc code=end

